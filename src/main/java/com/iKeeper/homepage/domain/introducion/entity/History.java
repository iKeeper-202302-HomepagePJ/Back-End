package com.iKeeper.homepage.domain.introducion.entity;

import com.iKeeper.homepage.domain.introducion.dto.HistoryRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "history")
public class History {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long id;

    @Column(name = "history_year")
    private String year;

    @Column(name = "history_content")
    private String content;

    @Builder
    public History(String year, String content) {

        this.year = year;
        this.content = content;
    }

    public static History createHistory(HistoryRequest historyRequest) {

        History history = History.builder()
                .year(historyRequest.getYear())
                .content(historyRequest.getContent())
                .build();
        return history;
    }
}
