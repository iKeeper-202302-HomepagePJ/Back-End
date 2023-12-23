package iKeeper.iKeeper.Homepage.model.request;

import lombok.Data;

import java.sql.Time;
import java.time.*;

@Data
public class CalendarCreationRequest {

    private String title;

    private LocalDate day;

    private Time time;

    private String place;

    private Boolean check;
} // 일정 리스트 생성 및 업데이트 시 사용됨