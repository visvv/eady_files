package com.vv.easy.server.services;

import com.vv.easy.server.controller.FileController;
import com.vv.easy.server.exceptions.PathNotFoundException;
import com.vv.easy.server.model.FileInfo;
import com.vv.easy.server.model.Message;
import org.springframework.stereotype.Component;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileService {
    public final static String FILE_STORE = System.getProperty("user.home") + "/easy_files";

    public List<FileInfo> getFileList(String path) {
        File file = new File(FileController.FILE_STORE + path);
        return (Arrays.stream(file.listFiles())
                .map((f) -> new FileInfo(f.getName(), f.getPath(), f.length()))
                .collect(Collectors.toList()));
    }
    public FileInfo getFileInfo(final String path) {
        File file = new File(FileController.FILE_STORE, path);
        if(!file.exists()) {
            throw new PathNotFoundException("Content not found.");
        }
       return new FileInfo(file.getName(), file.getPath(), file.length());
    }
}
