package com.iKeeper.homepage.domain.attendance.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LectureRequest {

    private String name;

    public LectureRequest(String name) {
        this.name = name;
    }
}
