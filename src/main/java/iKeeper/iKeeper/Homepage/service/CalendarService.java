package iKeeper.iKeeper.Homepage.service;

import iKeeper.iKeeper.Homepage.model.Calendar;
import iKeeper.iKeeper.Homepage.model.request.CalendarCreationRequest;
import iKeeper.iKeeper.Homepage.repository.CalendarRepository;
import iKeeper.iKeeper.Homepage.repository.FieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;
    private final FieldRepository fieldRepository;

    public Calendar readCalendar(LocalDate day) {
        Optional<Calendar> calendar = calendarRepository.findByDay(day);
        if (calendar.isPresent()) {
            return calendar.get();
        }

        throw new EntityNotFoundException(
                "해당 날짜의 일정이 존재하지 않습니다."
        );
    }

    public Calendar readCalendar(Long id) {
        Optional<Calendar> calendar = calendarRepository.findById(id);
        if (calendar.isPresent()) {
            return calendar.get();
        }

        throw new EntityNotFoundException(
                "해당 번호의 일정이 존재하지 않습니다."
        );
    }

    public List<Calendar> readCalendars() {
        return calendarRepository.findAll();
    }

    public Calendar createCalendar(CalendarCreationRequest calendar) {
        Calendar calendarToCreate = new Calendar();
        BeanUtils.copyProperties(calendar, calendarToCreate);
        return calendarRepository.save(calendarToCreate);
    }

    public void deleteCalendar(Long id) {
        calendarRepository.deleteById(id);
    }

    public Calendar updateCalendar(Long id, CalendarCreationRequest request) {
        Optional<Calendar> optionalCalendar = calendarRepository.findById(id);

        Calendar calendar = optionalCalendar.get();
        calendar.setTitle(request.getTitle());
        calendar.setDay(request.getDay());
        calendar.setTime(request.getTime());
        calendar.setPlace(request.getPlace());
        calendar.setCheck(request.getCheck());
        return calendarRepository.save(calendar);
    }
}
