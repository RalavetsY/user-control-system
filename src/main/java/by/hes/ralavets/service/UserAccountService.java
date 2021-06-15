package by.hes.ralavets.service;

import by.hes.ralavets.entity.UserAccount;
import by.hes.ralavets.exception.UserAccountNotFoundException;
import by.hes.ralavets.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private PasswordEncoder encoder;

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository, PasswordEncoder encoder) {
        this.userAccountRepository = userAccountRepository;
        this.encoder = encoder;
    }

    public List<UserAccount> getAll() {
        return userAccountRepository.findAll();
    }

    public UserAccount getById(Long id) {
        return userAccountRepository.findById(id)
                .orElseThrow(() -> new UserAccountNotFoundException("Cannot find user account with id: " + id));
    }

    public UserAccount create(UserAccount userAccount) {
        userAccount.setPassword(encoder.encode(userAccount.getPassword()));
        return userAccountRepository.save(userAccount);
    }

    public boolean isNotUniqueUsername(String username) {
        return  userAccountRepository.existsByUsername(username);
    }

    public UserAccount update(UserAccount userAccount) {
        UserAccount dbUserAccount = this.getById(userAccount.getId());
        if (userAccount.getPassword() == null) {
            userAccount.setPassword(dbUserAccount.getPassword());
        } else {
            userAccount.setPassword(encoder.encode(userAccount.getPassword()));
        }
        userAccount.setCreatedAt(dbUserAccount.getCreatedAt());
        return userAccountRepository.save(userAccount);
    }
}