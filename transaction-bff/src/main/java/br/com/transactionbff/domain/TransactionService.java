package br.com.transactionbff.domain;

import br.com.transactionbff.dto.RequestTransaciontDto;
import br.com.transactionbff.dto.TransactionDto;
import br.com.transactionbff.dto.redis.TransactionRedisRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionService {
    private TransactionRedisRepository transactionRedisRepository;

    public TransactionService(TransactionRedisRepository transactionRedisRepository) {
        this.transactionRedisRepository = transactionRedisRepository;
    }

    @Transactional
    public Optional<TransactionDto> save(final RequestTransaciontDto requestTransaciontDto) {
        requestTransaciontDto.setData(LocalDateTime.now());
       return Optional.of(transactionRedisRepository.save(requestTransaciontDto));
    }

    public Optional<TransactionDto> findById(final String id) {
        return transactionRedisRepository.findById(id);
    }
}
