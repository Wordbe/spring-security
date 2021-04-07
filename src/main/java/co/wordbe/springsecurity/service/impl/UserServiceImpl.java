package co.wordbe.springsecurity.service.impl;

import co.wordbe.springsecurity.domain.Account;
import co.wordbe.springsecurity.repository.UserRepository;
import co.wordbe.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public void createUser(Account account) {
        userRepository.save(account);
    }
}
