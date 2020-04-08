package ms.bank.repository;

import ms.bank.model.Bank;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBankRepository extends ReactiveMongoRepository<Bank, String> {

}
