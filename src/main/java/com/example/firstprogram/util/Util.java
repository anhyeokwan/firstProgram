package com.example.firstprogram.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

@Component
@Log4j2
@PropertySource("classpath:application.properties")
public class Util {

    @Value("${resource.path}")
    public String resourcePaht;
    @Value("${upload.path}")
    public String defaultPath;

    String imgExtention = "jpeg, jpg, png, svg";

    /*
    * file upload
    * */
    public List<LinkedHashMap<String, Object>> fileUpload(HttpServletResponse response, MultipartFile[] multipartFile, String filePath){
        List<LinkedHashMap<String, Object>> list = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        log.info("defaultPath : " + defaultPath);
        if(multipartFile.length > 0){
            resourcePaht = resourcePaht.substring(resourcePaht.lastIndexOf("D"));
            String savePath = resourcePaht + defaultPath + filePath;

            for (MultipartFile file : multipartFile) {
                LinkedHashMap<String, Object> map = new LinkedHashMap<>();
                String filename = file.getOriginalFilename();
                String realFile = this.fileReName(savePath, filename);
                Path realPath = Paths.get(realFile);
                log.info("realPath : " + realPath);
                try {
                    /*FileOutputStream fos = new FileOutputStream(realFile);
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    byte[] bytes = file.getBytes();

                    bos.write(bytes);
                    bos.close();*/

                    /*URL fileUrl = new URL(realFile);
                    URLConnection connection = fileUrl.openConnection();*/

                    file.transferTo(realPath);

                    boolean imageBool = false;

                    if (Files.probeContentType(realPath).startsWith("image")) {
                        imageBool = true;

                        File thumbNail = new File(realFile);
                        Thumbnailator.createThumbnail(realPath.toFile(), thumbNail, 200, 200);
                    }


                    map.put("filename", filename);
                    map.put("filepath", savePath + filename);
                    map.put("image", imageBool);

                    list.add(map);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }

        log.info(list);
        return list;
    }

    public void imageURL(HttpServletResponse response, String filename){
        String extention = filename.substring(filename.lastIndexOf("."));

        if (imgExtention.contains(extention)) response.setContentType("image/" + extention);


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
