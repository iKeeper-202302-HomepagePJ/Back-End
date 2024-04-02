package iKeeper.iKeeper.Homepage.service;

import iKeeper.iKeeper.Homepage.model.entity.User;
import iKeeper.iKeeper.Homepage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private  final UserRepository userRepository;

    public User saveUser(User user) {
        validateDuplicateUser(user);

        return userRepository.save(user);
    }

    private void validateDuplicateUser(User user) {
        User findUser = userRepository.findById(user.getId());
        if (findUser != null) {
            throw new IllegalStateException("이미 가입된 학번입니다.");
        }
    }
}
