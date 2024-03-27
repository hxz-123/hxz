package com.abc.controller;
import com.abc.bean.Examinations;
import com.abc.bean.Result;
import com.abc.service.ExaminationsService;
import com.abc.service.ImageService;
import com.abc.service.RubbishService;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;

@RestController
public class RubbishController {

    @Autowired
    private RubbishService rubbishService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ExaminationsService examinationsService;
    @Autowired
    private static final String DASHSCOPE_API_KEY = "sk-38c2d71e9c354799b9917b411c610357";
    private static final String filePath = System.getProperty("user.dir") + "/file/";
    @GetMapping("/rubbish")
    public Result rubbish() {
        // 调用垃圾服务处理垃圾数据
        List list = rubbishService.findAll();
        return Result.success(list);
    }
    @GetMapping("/imageScanByUrl")
    public Result imageScanByurl(@RequestParam("url") String urlString) throws Exception {
        URL url = new URL(urlString);
        System.out.println("urlllllllllllll: " + url);
        Object object= imageService.ImageScanByurl(url);
        // 调用垃圾服务处理垃圾数据
        return Result.success(object);
    }
    @PostMapping("/imageScanByLocalFile")
    public Result imageScanByLocalFile(@RequestParam("file")MultipartFile file) {
        try {
            Object object= imageService.ImageScanByLocalFile(file);
            return Result.success(object);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.error("Image scan failed");
        }
    }
    @PostMapping("/examinations")
    public Result examinations(@RequestBody Examinations examinations) {
        List list1 = examinationsService.findAll();
        return Result.success(examinations);
    }
    @PostMapping("/alibaba_chat")
    public Result alibaba_chat(@RequestBody String message) throws NoApiKeyException, InputRequiredException {
        String response = rubbishService.message(message);
        return Result.success(response);
    }
}
