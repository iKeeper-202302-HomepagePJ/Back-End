package iKeeper.iKeeper.Homepage.service;

import iKeeper.iKeeper.Homepage.model.entity.User;
import iKeeper.iKeeper.Homepage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public User saveUser(User user) {
        validateDuplicateUser(user);

        return userRepository.save(user);
    }

    private void validateDuplicateUser(User user) {
        User findUser = userRepository.findByStudent(user.getStudent());
        if (findUser != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String student) throws UsernameNotFoundException {
        User user = userRepository.findByStudent(student);

        if (user == null) {
           throw new UsernameNotFoundException(student);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getStudent())
                .password(user.getPassword())
                .roles(user.getRole().toString())
                .build();
    }
}
