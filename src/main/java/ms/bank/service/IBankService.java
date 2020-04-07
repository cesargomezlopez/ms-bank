package ms.bank.service;

import ms.bank.model.Bank;
import ms.bank.util.ICrud;
import reactor.core.publisher.Mono;

public interface IBankService extends ICrud<Bank, String> {

  Mono<Boolean> existsById(String bankId);

}
