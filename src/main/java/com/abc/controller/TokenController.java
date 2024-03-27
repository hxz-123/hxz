package com.abc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

   @GetMapping("/upload_token")
    public String getUploadToken() {
        // 生成上传凭证
        String uploadToken = "QeQL4fbC6LqVHRmvdWc3jbmenpF1_sVNu5uiZqmk";
        return uploadToken;
}
}