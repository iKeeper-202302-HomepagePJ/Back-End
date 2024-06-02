package com.iKeeper.homepage.domain.file.controller;

import com.iKeeper.homepage.domain.file.entity.File;
import com.iKeeper.homepage.domain.file.service.FileService;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import com.iKeeper.homepage.global.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class FileController {

    private final FileService fileService;
    private final FileUtils fileUtils;

    @GetMapping(value = "/posts/{categoryId}/{postId}/files")
    public List<File> getFileListByPostId(@PathVariable Long categoryId, @PathVariable Long postId) {
        return fileService.getFileListByPostId(postId);
    }

    @GetMapping(value = "/posts/{postId}/files/{fileId}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long postId, @PathVariable Long fileId) {

        Optional<File> file = fileService.getFileById(fileId);
        Resource resource = fileUtils.getFileAsResource(file);

        try {

            String filename = URLEncoder.encode(file.get().getName(), "UTF-8");
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\";")
                    .header(HttpHeaders.CONTENT_LENGTH, file.get().getSize() + "")
                    .body(resource);
        } catch (UnsupportedEncodingException e) {
            throw new CustomException("filename encoding failed : " + file.get().getName(), ErrorCode.USER_INVALID_VALUE);
        }
    }
}
