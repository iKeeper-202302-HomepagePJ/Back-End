package iKeeper.iKeeper.Homepage.model.request;

import iKeeper.iKeeper.Homepage.model.Field;
import lombok.Data;

import java.time.*;

@Data
public class CalendarCreationRequest {

    private String title;

    private LocalDate day;

    private LocalTime time;

    private String place;

    private Boolean check;
} // 일정 리스트 생성 및 업데이트 시 사용됨