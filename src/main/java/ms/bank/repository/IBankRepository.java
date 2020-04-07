package ms.bank.repository;

import ms.bank.model.Bank;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IBankRepository extends ReactiveMongoRepository<Bank, String> {

}
