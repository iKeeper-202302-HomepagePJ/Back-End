package iKeeper.iKeeper.Homepage.repository;

import iKeeper.iKeeper.Homepage.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.LongSummaryStatistics;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByStudent(String student);
}