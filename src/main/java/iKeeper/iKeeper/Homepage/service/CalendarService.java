package iKeeper.iKeeper.Homepage.service;

import iKeeper.iKeeper.Homepage.model.entity.Calendar;
import iKeeper.iKeeper.Homepage.model.request.CalendarCreationRequest;
import iKeeper.iKeeper.Homepage.repository.CalendarRepository;
import iKeeper.iKeeper.Homepage.repository.FieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;
    private FieldRepository fieldRepository;

    public Calendar readCalendar(Long id) {
        return calendarRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 번호의 일정이 존재하지 않습니다."));
    } // id 번호를 통해 일정 리스트 검색

    public List<Calendar> readCalendars() {
        return calendarRepository.findAll();
    } // 모든 일정 리스트 목록 확인 함수

    public Calendar createCalendar(CalendarCreationRequest calendar) {
        Calendar calendarToCreate = new Calendar();
        BeanUtils.copyProperties(calendar, calendarToCreate);
        return calendarRepository.save(calendarToCreate);
    } // 일정 생성 함수

    public void deleteCalendar(Long id) {
        calendarRepository.deleteById(id);
    } // id 정보를 통해 해당 id의 일정 삭제

    public Calendar updateCalendar(Long id, CalendarCreationRequest request) {
        Optional<Calendar> optionalCalendar = calendarRepository.findById(id);

        Calendar calendar = optionalCalendar.get();
        calendar.setTitle(request.getTitle());
        calendar.setField(request.getField());
        calendar.setDay(request.getDay());
        calendar.setTime(request.getTime());
        calendar.setPlace(request.getPlace());
        calendar.setCheck(request.getCheck());
        return calendarRepository.save(calendar);
    } // id 정보를 통해 해당 id의 일정 정보 수정
}