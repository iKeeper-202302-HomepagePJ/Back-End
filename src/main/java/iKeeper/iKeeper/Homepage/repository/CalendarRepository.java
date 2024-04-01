package iKeeper.iKeeper.Homepage.repository;

import iKeeper.iKeeper.Homepage.model.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
}