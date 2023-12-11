package com.example.firstprogram.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@Log4j2
@PropertySource("classpath:application.properties")
public class Util {

    @Value("${upload.path}")
    private String defaultPath;

    /*
    * file upload
    * */
    public JSONObject fileUpload(HttpServletRequest request, MultipartFile[] multipartFile, String filePath){

        JSONObject jsonObject = new JSONObject();
        log.info("defaultPath : " + defaultPath);
        if(multipartFile.length > 0){
            String savePath = defaultPath + filePath;
            log.info("savepath : " + savePath);
            for (MultipartFile file : multipartFile) {
                String filename = file.getOriginalFilename();
                log.info("filename : " + filename);
                String realFile = fileReName(savePath, filename);
                log.info("realFile : " + realFile);

                try {
                    FileOutputStream fos = new FileOutputStream(realFile);
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    byte[] bytes = file.getBytes();

                    bos.write(bytes);
                    bos.close();

                    jsonObject.put("filename", filename);
                    jsonObject.put("filepath", savePath + filename);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }

        return jsonObject;
    }

    public String fileReName(String savePath, String filename) {

        String filepath = "";

        String onlyFilename = filename.substring(0, filename.lastIndexOf("."));
        String extention = filename.substring(filename.lastIndexOf("."));

        log.info("onlyFilename : " + onlyFilename);
        log.info("extention : " + extention);
        int cnt = 0;
        while (true) {
            if(cnt == 0){
                filepath = savePath + onlyFilename + extention;
            }else{
                filepath = savePath + onlyFilename + "(" + cnt + ")" + extention;
            }

            File fileChk = new File(filepath);
            if (!fileChk.exists()) {
                break;
            }
            cnt++;
        }
        return filepath;
    }

}
