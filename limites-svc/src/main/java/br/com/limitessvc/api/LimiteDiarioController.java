package br.com.limitessvc.api;

import br.com.limitessvc.entity.LimiteDiario;
import br.com.limitessvc.service.LimiteDiarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class LimiteDiarioController {
    private final LimiteDiarioService limiteDiarioService;

    public LimiteDiarioController(LimiteDiarioService limiteDiarioService) {
        this.limiteDiarioService = limiteDiarioService;
    }

    @GetMapping("/limite-diario/{agencia}/{conta}")
    public LimiteDiario buscarLimiteDiario(
            @PathVariable("agencia") final Long agencia,
            @PathVariable("conta") final Long conta) {
        final Optional<LimiteDiario> limiteDiario = limiteDiarioService.buscarLimiteDiario(agencia, conta);
        if (limiteDiario.isPresent()) {
            return limiteDiario.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado");
    }

    @GetMapping("/limite-diario/{id}")
    public LimiteDiario findById(@PathVariable("id") Long id) {
        final Optional<LimiteDiario> limiteDiario = limiteDiarioService.findById(id);
        if (limiteDiario.isPresent()) {
            return limiteDiario.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado");
    }
}
