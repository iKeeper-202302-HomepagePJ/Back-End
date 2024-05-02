package com.iKeeper.homepage.domain.introducion.entity;

import com.iKeeper.homepage.domain.introducion.dto.HyperlinkRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hyperlink")
public class Hyperlink {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hyperlink_id")
    private Long id;

    @Column(name = "hyperlink_img")
    private String img;

    @Column(name = "hyperlink_url")
    private String url;

    @Builder
    public Hyperlink(String img, String url) {

        this.img = img;
        this.url = url;
    }

    public static Hyperlink createHyperlink(HyperlinkRequest hyperlinkRequest) {

        Hyperlink hyperlink = Hyperlink.builder()
                .img(hyperlinkRequest.getImg())
                .url(hyperlinkRequest.getUrl())
                .build();
        return hyperlink;
    }
}
