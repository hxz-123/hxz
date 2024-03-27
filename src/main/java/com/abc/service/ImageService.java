package com.abc.service;

import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

public interface ImageService{
    <classifyingRubbishAdvanceResponse> classifyingRubbishAdvanceResponse ImageScanByurl(URL url) throws Exception; // 调用图像识别接口并返回识别结果
    <classifyingRubbishAdvanceResponse> classifyingRubbishAdvanceResponse ImageScanByLocalFile( MultipartFile file) throws Exception;
}
