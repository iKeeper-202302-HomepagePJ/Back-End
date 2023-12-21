package iKeeper.iKeeper.Homepage.repository;

import iKeeper.iKeeper.Homepage.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    Optional<Calendar> findByDay(LocalDate day);
}