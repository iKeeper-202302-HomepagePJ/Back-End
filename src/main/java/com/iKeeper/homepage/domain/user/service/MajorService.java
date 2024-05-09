package com.iKeeper.homepage.domain.user.service;

import com.iKeeper.homepage.domain.user.dao.MajorRepository;
import com.iKeeper.homepage.domain.user.entity.Major;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MajorService {

    private final MajorRepository majorRepository;

    public List<Major> searchAllMajor() {
        return majorRepository.findAll();
    }
}
