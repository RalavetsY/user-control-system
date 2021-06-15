package by.hes.ralavets.repository;

import by.hes.ralavets.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    boolean existsByUsername(String username);

    UserAccount findByUsername(String username);
}
