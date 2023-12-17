package com.example.firstprogram.controller;

import com.example.firstprogram.util.Util;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@Log4j2
@RequiredArgsConstructor
public class fileController {

    private final Util util;

    // consumes = MediaType.MULTIPART_FORM_DATA_VALUE : swagger 상에서 파일 전송 가능
    /*@ResponseBody
    @PostMapping(value = "/editorFileUpload", produces = "application/json;charset=utf-8")
    public String editorFileUpload(HttpServletRequest request, @RequestParam("files") MultipartFile[] files){

        String savePath = "/editor/qna/";

        JSONObject fileJson = util.fileUpload(request, files, savePath);

        String filepath = (String) fileJson.get("filepath");
        return filepath;
    }*/

    @ResponseBody
    @PostMapping(value = "/fileUpload", produces = "application/json;charset=utf-8")
    public JSONObject fileUpload(HttpServletResponse response, HttpServletRequest request, @RequestParam Map<String, Object> map
            , @RequestParam("fileArr") MultipartFile[] multipartFiles) {
        JSONObject obj = new JSONObject();
        String type = (String) map.get("type");
        //MultipartFile[] multipartFiles = (MultipartFile[]) map.get("fileArr");
        String savePath = "/qna/";
        log.info("type : " + type);
        List<LinkedHashMap<String, Object>> fileJson = util.fileUpload(response, multipartFiles, savePath);
        obj.put("files", fileJson);
        return obj;
    }

    @GetMapping("/getImgUrl")
    public void getImgUrl(HttpServletResponse response, @RequestParam Map<String, Object> map) {

        String filepath = (String) map.get("filepath");
        String fileType = (String) map.get("type");

        String extention = filepath.substring(filepath.lastIndexOf(".") + 1);
        response.setContentType("image/" + extention);

        log.info(util.resourcePaht);

        String fileSource = util.resourcePaht + util.defaultPath + "/" + fileType + "/" + filepath;
        log.info(">>>" + fileSource);
        URL url = null;
        try {
            url = new URL("file:///" + fileSource);
            URLConnection connection = url.openConnection();

            connection.setReadTimeout(3000);
            InputStream is = new BufferedInputStream(connection.getInputStream());
            IOUtils.copy(is, response.getOutputStream());
            is.close();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
