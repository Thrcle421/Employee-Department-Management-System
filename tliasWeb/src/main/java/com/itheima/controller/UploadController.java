package com.itheima.controller;

import com.aliyuncs.exceptions.ClientException;
import com.itheima.pojo.Result;
import com.itheima.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
public class UploadController {

//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image) {
//        log.info("{}{}{}", username, age, image);
//        return Result.success();
//    }
    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException, ClientException {
        log.info("文件名: {}", image.getOriginalFilename());
        String urlname = aliOSSUtils.uploadss(image);
        log.info("文件url为: {}", urlname);
        return Result.success(urlname); // 返回的结果应为 URL 而不是视图名称
    }

}
