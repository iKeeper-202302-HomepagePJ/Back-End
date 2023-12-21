package iKeeper.iKeeper.Homepage.repository;

import iKeeper.iKeeper.Homepage.model.Calendar;
import iKeeper.iKeeper.Homepage.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface FieldRepository extends JpaRepository<Field, Long> {

    Optional<Field> findById(Long id);
}
