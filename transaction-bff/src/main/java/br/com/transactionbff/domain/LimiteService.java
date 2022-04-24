package br.com.transactionbff.domain;

import br.com.transactionbff.dto.LimiteDiario;
import br.com.transactionbff.feign.LimiteClient;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.decorators.Decorators;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.Supplier;

@Service
public class LimiteService {

    private final LimiteClient limiteClient;

    private final CircuitBreaker countCircuitBreaker;
    private final CircuitBreaker timeCircuitBreaker;

    public LimiteService(LimiteClient limiteClient, CircuitBreaker countCircuitBreaker, CircuitBreaker timeCircuitBreaker) {
        this.limiteClient = limiteClient;
        this.countCircuitBreaker = countCircuitBreaker;
        this.timeCircuitBreaker = timeCircuitBreaker;
    }

    public LimiteDiario buscarLimiteDiario(final Long agencia, final Long conta) {
        final Supplier<LimiteDiario> limiteDiarioSupplier = fallback(agencia, conta);
        return limiteDiarioSupplier.get();
    }

    private Supplier<LimiteDiario> fallback(final Long agencia, final Long conta) {
        final Supplier<LimiteDiario> limiteDiarioSupplier = timeCircuitBreaker.decorateSupplier(() -> limiteClient.buscarLimiteDiario(agencia, conta));

        return Decorators.ofSupplier(limiteDiarioSupplier)
                .withCircuitBreaker(timeCircuitBreaker)
                .withFallback(Arrays.asList(CallNotPermittedException.class), e -> this.getStaticLimit())
                .decorate();
    }

    private LimiteDiario getStaticLimit() {
        LimiteDiario limiteDiario = new LimiteDiario();
        limiteDiario.setValor(BigDecimal.ZERO);
        return limiteDiario;
    }
}