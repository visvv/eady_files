package com.vv.springdemo.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
public class FileController {

    private final static String FILE_STORE = "file_store";

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public ResponseEntity<String> upload(@RequestParam("file")MultipartFile multipartFile) {
        System.out.println(multipartFile.getOriginalFilename());
        try {
            File file = new File(multipartFile.getOriginalFilename());
            multipartFile.transferTo(Paths.get(FILE_STORE, file.getName()));
            System.out.println(multipartFile.getSize());
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);

    }

    @GetMapping("list")
    public ResponseEntity<String> list(){
        File file = new File(FILE_STORE);
        String[] files = file.list();
        return  ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                .body(Arrays.asList(files).stream().collect(Collectors.joining("\n")));
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
