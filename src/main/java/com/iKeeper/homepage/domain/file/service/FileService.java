package com.iKeeper.homepage.domain.file.service;

import com.iKeeper.homepage.domain.file.dao.FileRepository;
import com.iKeeper.homepage.domain.file.entity.File;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    public List<File> getFileListByPostId(Long postId) {
        return fileRepository.findAllByPostId(postId);
    }

    public Optional<File> getFileById(Long id) {
        return fileRepository.findById(id);
    }

    public void saveFiles(Long postId, List<File> files) {

        for (File file : files) file.setPostId(postId);
        fileRepository.saveAll(files);
    }

    public void deleteFile(Long id) {
        fileRepository.deleteById(id);
    }
}
