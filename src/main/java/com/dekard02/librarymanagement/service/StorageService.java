package com.dekard02.librarymanagement.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    public static final String RESOURCE_FOLDER = "public";

    public static enum StorageFolder {
        IMAGE {
            @Override
            public String toString() {
                return "images";
            }
        };
    }

    public void init();

    public String store(MultipartFile file, String dir);

    public Resource loadAsResourceInPublicFolder(String filePath);

}
