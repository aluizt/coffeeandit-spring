package br.com.transactionbff.api;

import br.com.transactionbff.api.annotation.swagger.BuscarTransacaoSwagger;
import br.com.transactionbff.api.annotation.swagger.ConfirmarTransacaoSwagger;
import br.com.transactionbff.api.annotation.swagger.EnviarTransacaoSwagger;
import br.com.transactionbff.api.annotation.swagger.RemoverTransacaoSwagger;
import br.com.transactionbff.domain.TransactionService;
import br.com.transactionbff.dto.RequestTransaciontDto;
import br.com.transactionbff.dto.TransactionDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/transaction")
@Tag(name = "/transaction", description = "Grupo de API's para manipulação de transações financeiras")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @EnviarTransacaoSwagger
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TransactionDto> enviarTransacao(@RequestBody final RequestTransaciontDto requestTransactionDto) {
        final Optional<TransactionDto> transactionDto = transactionService.save(requestTransactionDto);
        if (transactionDto.isPresent()) {
            return Mono.just(transactionDto.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "unable to find resource");
    }

    @BuscarTransacaoSwagger
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TransactionDto> buscarTransacao(@PathVariable("id") final String uuid) {
        final Optional<TransactionDto> transactionDto = transactionService.findById(uuid);
        if (transactionDto.isPresent()) {
            return Mono.just(transactionDto.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "unable to find resource");
    }

    @RemoverTransacaoSwagger
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TransactionDto> removerTransacao(@PathVariable("id") final String uuid) {
        return Mono.empty();
    }

    @ConfirmarTransacaoSwagger
    @PatchMapping(value = "/{id}/confirm")
    public Mono<TransactionDto> confirmarTransacao(@PathVariable("id") final String uuid) {
        return Mono.empty();
    }

}
