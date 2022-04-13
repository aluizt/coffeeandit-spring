package br.com.limitessvc.service;

import br.com.limitessvc.entity.LimiteDiario;
import br.com.limitessvc.repository.LimiteDiarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LimiteDiarioService {
    private final LimiteDiarioRepository limiteDiarioRepository;

    public LimiteDiarioService(LimiteDiarioRepository limiteDiarioRepository) {
        this.limiteDiarioRepository = limiteDiarioRepository;
    }

    public Optional<LimiteDiario> findById(Long id){
        return limiteDiarioRepository.findById(id);
    }
}
