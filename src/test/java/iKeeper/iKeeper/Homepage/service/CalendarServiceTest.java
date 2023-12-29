package iKeeper.iKeeper.Homepage.service;

import iKeeper.iKeeper.Homepage.model.Calendar;
import iKeeper.iKeeper.Homepage.model.request.CalendarCreationRequest;
import iKeeper.iKeeper.Homepage.repository.CalendarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class CalendarServiceTest {

    CalendarService calendarService = new CalendarService();

    @Test
    @DisplayName("일정 생성 확인 테스트")
    void createSchedule() {
        //given
        Calendar calendar = new Calendar();
        calendar.setTitle("시작 세미나");
        calendar.setField(3);

        //when


    }

    @Test
    void readSchedule() {

    }

    @Test
    void updateSchedule() {

    }

    @Test
    void deleteSchedule() {

    }
}
