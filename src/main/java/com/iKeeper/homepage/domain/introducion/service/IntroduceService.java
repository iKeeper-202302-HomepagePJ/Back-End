package com.iKeeper.homepage.domain.introducion.service;

import com.iKeeper.homepage.domain.introducion.dao.IntroduceRepository;
import com.iKeeper.homepage.domain.introducion.dto.IntroduceRequest;
import com.iKeeper.homepage.domain.introducion.entity.Introduce;
import com.iKeeper.homepage.global.error.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IntroduceService {

    private final IntroduceRepository introduceRepository;

    public List<Introduce> searchIntroduce() {

        return introduceRepository.findAll();
    }

    @Transactional
    public String updateIntroduce(IntroduceRequest introduceRequest) {

        //Introduce introduce = introduceRepository.findById(Long.valueOf(1));
        //introduce.updateContent(introduceRequest.getContent());
        return "success";
    }
}
