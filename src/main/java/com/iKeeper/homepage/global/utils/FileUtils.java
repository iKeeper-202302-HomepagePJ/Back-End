package com.iKeeper.homepage.global.utils;

import com.iKeeper.homepage.domain.file.entity.File;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class FileUtils {

    private final String uploadPath = Paths.get("C:", "Users", "bin54", "Desktop",
            "project", "upload-files").toString();

    public Resource getFileAsResource(Optional<File> file) {

        String uploadedDate = file.get().getTimestamp().toLocalDate().format(DateTimeFormatter.ofPattern("yyMMdd"));
        String fileName = file.get().getSaveName();
        Path filePath = Paths.get(uploadPath, uploadedDate, fileName);

        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (!resource.exists() || !resource.isFile()) throw new CustomException("error", ErrorCode.USER_INVALID_VALUE);
            return resource;
        } catch (MalformedURLException e) {
            throw new CustomException("error", ErrorCode.USER_INVALID_VALUE);
        }
    }

    public List<File> uploadFiles(List<MultipartFile> multipartFiles) {

        List<File> files = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) files.add(uploadFile(multipartFile));
        return files;
    }

    public File uploadFile(MultipartFile multipartFile) {

        String saveName = generateSaveFilename(multipartFile.getOriginalFilename());
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")).toString();
        String uploadPath = getUploadPath(today) + java.io.File.separator + saveName;
        java.io.File uploadFile = new java.io.File(uploadPath);

        try {
            multipartFile.transferTo(uploadFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return File.builder()
                .name(multipartFile.getOriginalFilename())
                .saveName(saveName)
                .size(multipartFile.getSize())
                .delete(Boolean.FALSE)
                .timestamp(LocalDateTime.now())
                .build();
    }

    private String generateSaveFilename(String filename) {

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String extension = StringUtils.getFilenameExtension(filename);
        return uuid + "." + extension;
    }

    private String getUploadPath() {
        return makeDirectories(uploadPath);
    }

    private String getUploadPath(String addPath) {
        return makeDirectories(uploadPath + java.io.File.separator + addPath);
    }

    private String makeDirectories(String path) {

        java.io.File dir = new java.io.File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        return dir.getPath();
    }

    public void deleteFiles(Optional<File> file) {

        String uploadedDate = file.get().getTimestamp().toLocalDate()
                                    .format(DateTimeFormatter.ofPattern("yyMMdd"));
        deleteFile(uploadedDate, file.get().getSaveName());
    }

    private void deleteFile(String addPath, String fileName) {

        String filePath = Paths.get(uploadPath, addPath, fileName).toString();
        deleteFile(filePath);
    }

    private void deleteFile(String filePath) {

        java.io.File file = new java.io.File(filePath);
        if (file.exists()) file.delete();
    }
}