package com.vv.easy.server.controller;

import com.vv.easy.server.exceptions.PathNotFoundException;
import com.vv.easy.server.model.FileInfo;
import com.vv.easy.server.model.Message;
import com.vv.easy.server.services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.vv.easy.server.controller.AbstractController.APPLICATION_JSON;

@RestController
public class MetadataController {
    @Autowired
    private FileService fileService;

    Logger logger = LoggerFactory.getLogger(MetadataController.class);

    @GetMapping(value = "/list", produces = APPLICATION_JSON)
    public Message list() {
        System.out.println("XXX");
        File file = new File(FileController.FILE_STORE);
        return new Message<String>(Arrays.stream(file.listFiles())
                .map((f) -> f.getName()).collect(Collectors.joining(",")));
    }

    @GetMapping(value = "/info", produces = APPLICATION_JSON)
    public Message info(@RequestParam("path") String path) {
        logger.info("Info Path " + path );
        return new Message<FileInfo>(fileService.getFileInfo(path));
    }
}
