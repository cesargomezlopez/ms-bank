package ms.bank.service;

import ms.bank.model.Bank;
import ms.bank.repository.IBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BankServiceImpl implements IBankService {

  @Autowired
  private IBankRepository bankRepository;

  @Override
  public Flux<Bank> findAll() {
    try {
      return bankRepository.findAll();
    } catch (Exception e) {
      return Flux.error(e);
    }
  }

  @Override
  public Mono<Bank> findById(String id) {
    try {
      return bankRepository.findById(id)
          .switchIfEmpty(Mono.empty());
    } catch (Exception e) {
      return Mono.error(e);
    }
  }

  @Override
  public Mono<Bank> create(Bank entity) {
    try {
      return bankRepository.save(entity)
          .switchIfEmpty(Mono.empty());
    } catch (Exception e) {
      return Mono.error(e);
    }
  }

  @Override
  public Mono<Bank> update(Bank entity) {
    try {
      return bankRepository.findById(entity.getId()).flatMap(bk -> {
        return bankRepository.save(entity);
      }).switchIfEmpty(Mono.error(new Exception("Bank not found")));
    } catch (Exception e) {
      return Mono.error(e);
    }
  }

  @Override
  public Mono<Void> deleteById(String id) {
    try {
      return bankRepository.findById(id).flatMap(bk -> {
        return bankRepository.delete(bk);
      }).switchIfEmpty(Mono.empty());
    } catch (Exception e) {
      return Mono.error(e);
    }
  }

  @Override
  public Mono<Boolean> existsById(String bankId) {
    try {
      return bankRepository.existsById(bankId);
    } catch (Exception e) {
      return Mono.error(e);
    }
  }

}
