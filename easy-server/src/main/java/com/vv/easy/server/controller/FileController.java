package com.vv.easy.server.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

@Controller
public class FileController {

    public final static String FILE_STORE = System.getProperty("user.home") + "/easy_files";

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public ResponseEntity<String> upload(@RequestParam("file")MultipartFile multipartFile) {
        System.out.println(multipartFile.getOriginalFilename());
        String message = "";
        try {
            File file = new File(multipartFile.getOriginalFilename());
            multipartFile.transferTo(Paths.get(FILE_STORE, file.getName()));
            message = "Upload completed successfully " + file.getName() + " - " + (file.length()/(1024));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(message);
    }


    @GetMapping("download/{fileName}")
    public ResponseEntity<InputStreamResource> download(@PathVariable("fileName") String path) {
        System.out.println(path);
        try {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(new FileInputStream(new File(FILE_STORE + "/" + path))));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return (ResponseEntity<InputStreamResource>) ResponseEntity.notFound();
        }
    }
}
