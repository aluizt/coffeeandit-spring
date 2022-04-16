package br.com.limitessvc.service;

import br.com.limitessvc.entity.LimiteDiario;
import br.com.limitessvc.repository.LimiteDiarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class LimiteDiarioService {
    private final LimiteDiarioRepository limiteDiarioRepository;

    @Value("${limite.valor:0}")
    private BigDecimal valorDiario;

    public LimiteDiarioService(LimiteDiarioRepository limiteDiarioRepository) {
        this.limiteDiarioRepository = limiteDiarioRepository;
    }

    public Optional<LimiteDiario> buscarLimiteDiario(final Long agencia, final Long conta) {

        var limiteDiario = limiteDiarioRepository.findByAgenciaAndConta(agencia, conta);
        if (limiteDiario.isEmpty()) {
            var limite = new LimiteDiario();
            //TODO
            limite.setConta(conta);
            limite.setAgencia(agencia);
            limite.setValor(valorDiario);
            return Optional.of(limiteDiarioRepository.save(limite));
        }
        return limiteDiario;
    }

    public Optional<LimiteDiario> findById(Long id) {
        return limiteDiarioRepository.findById(id);
    }
}
