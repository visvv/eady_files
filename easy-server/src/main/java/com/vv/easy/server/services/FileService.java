package com.vv.easy.server.services;

import com.vv.easy.server.controller.FileController;
import com.vv.easy.server.exceptions.PathNotFoundException;
import com.vv.easy.server.model.FileInfo;
import org.springframework.stereotype.Component;
import java.io.File;

@Component
public class FileService {
    public final static String FILE_STORE = System.getProperty("user.home") + "/easy_files";

    public FileInfo getFileInfo(final String path) {
        File file = new File(FileController.FILE_STORE, path);
        if(!file.exists()) {
            throw new PathNotFoundException("Content not found.");
        }
       return new FileInfo(file.getName(), file.getPath(), file.length());
    }
}
