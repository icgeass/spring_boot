package com.zeroq6.spring_boot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

// @Controller
@RequestMapping("/file")
@Slf4j
public class FileController {

    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request, String path) {
        try {
            File file = new File(path);
            byte[] body = null;
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attchement;filename=" + file.getName());
            HttpStatus statusCode = HttpStatus.OK;
            InputStream is = new FileInputStream(file);
            body = new byte[is.available()];
            is.read(body);
            ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
            return entity;
        } catch (Exception e) {
            log.error("download error", e);
        }
        return null;
    }

}
