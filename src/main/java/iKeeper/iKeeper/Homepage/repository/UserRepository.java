package iKeeper.iKeeper.Homepage.repository;

import iKeeper.iKeeper.Homepage.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findById(String id);
}