package com.dekard02.librarymanagement.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dekard02.librarymanagement.service.StorageService;

@Service
public class StorageServiceImpl implements StorageService {

    public StorageServiceImpl() {
        init();
    }

    @Override
    public void init() {
        // create folder if not exist
        try {
            var assetPath = Paths.get(RESOURCE_FOLDER);
            if (!Files.exists(assetPath)) {
                Files.createDirectory(assetPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stream.of(StorageFolder.values()).forEach(folder -> {
            var path = Paths.get(RESOURCE_FOLDER, folder.toString());
            if (!Files.exists(path)) {
                try {
                    Files.createDirectory(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public String store(MultipartFile multipartFile, String dir) {
        var fullDir = RESOURCE_FOLDER + "/" + dir;
        var path = Paths.get(fullDir);
        try {
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }

            var splitingFileName = multipartFile.getOriginalFilename().split("\\.");
            var fileExtension = splitingFileName[splitingFileName.length - 1];
            var fileName = UUID.randomUUID().toString() + "." + fileExtension;

            var filePath = Paths.get(fullDir, fileName);
            var stream = multipartFile.getInputStream();
            Files.copy(stream, filePath, StandardCopyOption.REPLACE_EXISTING);
            stream.close();
            return Paths.get(fullDir, fileName).toString().replace("\\", "/");
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong");
        }
    }

    @Override
    public Resource loadAsResourceInPublicFolder(String filePath) {
        if (filePath.startsWith("/")) {
            filePath = filePath.subSequence(1, filePath.length()).toString();
        }

        var resource = new FileSystemResource(RESOURCE_FOLDER + "/" + filePath);
        if (!resource.exists()) {
            return null;
        }

        return resource;
    }

    @Override
    public void delete(String filePath) {
        var path = Paths.get(filePath);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
