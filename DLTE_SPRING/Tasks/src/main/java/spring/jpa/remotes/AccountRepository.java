package spring.jpa.remotes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.CrudRepositoryExtensionsKt;
import org.springframework.stereotype.Repository;
import spring.jpa.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

}
