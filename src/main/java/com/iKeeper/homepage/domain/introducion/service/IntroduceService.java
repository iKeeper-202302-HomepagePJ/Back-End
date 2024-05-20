package com.iKeeper.homepage.domain.introducion.service;

import com.iKeeper.homepage.domain.introducion.dao.AwardRepository;
import com.iKeeper.homepage.domain.introducion.dao.HistoryRepository;
import com.iKeeper.homepage.domain.introducion.dao.HyperlinkRepository;
import com.iKeeper.homepage.domain.introducion.dao.IntroduceRepository;
import com.iKeeper.homepage.domain.introducion.dto.AwardRequest;
import com.iKeeper.homepage.domain.introducion.dto.HistoryRequest;
import com.iKeeper.homepage.domain.introducion.dto.IntroduceRequest;
import com.iKeeper.homepage.domain.introducion.entity.Award;
import com.iKeeper.homepage.domain.introducion.entity.History;
import com.iKeeper.homepage.domain.introducion.entity.Hyperlink;
import com.iKeeper.homepage.domain.introducion.entity.Introduce;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IntroduceService {

    private final IntroduceRepository introduceRepository;
    private final HyperlinkRepository hyperlinkRepository;
    private final HistoryRepository historyRepository;
    private final AwardRepository awardRepository;

    public List<Introduce> searchIntroduce() {
        return introduceRepository.findAll();
    }

    public List<Hyperlink> hyperlinkList() {
        return hyperlinkRepository.findAll();
    }

    public List<History> historyList() {
        return historyRepository.findAll();
    }

    public List<Award> awardList() {
        return awardRepository.findAll();
    }

    public Hyperlink createHyperlink(Hyperlink hyperlink) {
        return hyperlinkRepository.save(hyperlink);
    }

    public History createHistory(History history) {
        return historyRepository.save(history);
    }

    public Award createAward(Award award) {
        return awardRepository.save(award);
    }

    @Transactional
    public String updateIntroduce(IntroduceRequest introduceRequest) {
        Introduce introduce = introduceRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new CustomException("해당 ID의 일정이 존재하지 않습니다.", ErrorCode.CALENDAR_NOT_FOUND));

        introduce.updateContent(introduceRequest.getContent());
        return "success";
    }

    @Transactional
    public String updateHistory(Long id, HistoryRequest historyRequest) {
        History history = historyRepository.findById(id)
                .orElseThrow(() -> new CustomException("해당 ID의 연혁이 존재하지 않습니다.", ErrorCode.USER_INVALID_VALUE));

        history.updateHistory(historyRequest.getYear(), historyRequest.getContent());
        return "success";
    }

    @Transactional
    public String updateAward(Long id, AwardRequest awardRequest) {
        Award award = awardRepository.findById(id)
                .orElseThrow(() -> new CustomException("해당 ID의 수상 이력이 존재하지 않습니다.", ErrorCode.USER_INVALID_VALUE));

        award.updateAward(awardRequest.getName(), awardRequest.getDay(), awardRequest.getContent(),
                awardRequest.getPeople());
        return "success";
    }

    public void deleteHyperlink(Long id) {
        hyperlinkRepository.deleteById(id);
    }
}
