package by.hes.ralavets.security;

import by.hes.ralavets.entity.UserAccount;
import by.hes.ralavets.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("accountUserDetailsService")
public class AccountUserDetailsService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;

    @Autowired
    public AccountUserDetailsService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUsername(username);
        if (userAccount != null ) {
            return userAccount;
        }
        throw new UsernameNotFoundException("User '" + username + "' not found");
    }
}