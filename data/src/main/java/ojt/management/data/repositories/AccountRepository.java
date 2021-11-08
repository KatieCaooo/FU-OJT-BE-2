package ojt.management.data.repositories;

import ojt.management.data.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

    Account findByEmail(String email);

    Boolean existsByStudent_StudentCode(String studentCode);

    Boolean existsByEmail(String email);

    boolean existsById(Long id);

}
