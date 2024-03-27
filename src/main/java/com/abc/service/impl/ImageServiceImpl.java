package com.abc.service.impl;

import com.abc.service.ImageService;
import com.aliyun.imagerecog20190930.models.ClassifyingRubbishResponse;
import com.aliyun.tea.TeaException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

@Service
public class ImageServiceImpl implements ImageService {
    public static com.aliyun.imagerecog20190930.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        /*
          初始化配置对象com.aliyun.teaopenapi.models.Config
          Config对象存放AccessKeyId、AccessKeySecret、endpoint等配置
         */
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                .setAccessKeyId(accessKeyId)
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "imagerecog.cn-shanghai.aliyuncs.com";
        return new com.aliyun.imagerecog20190930.Client(config);
    }
    @Override
    public Object ImageScanByurl(URL url) throws Exception {
        // 创建AccessKey ID和AccessKey Secret，请参考https://help.aliyun.com/document_detail/175144.html
        // 如果您使用的是RAM用户的AccessKey，还需要为子账号授予权限AliyunVIAPIFullAccess，请参考https://help.aliyun.com/document_detail/145025.html
        // 从环境变量读取配置的AccessKey ID和AccessKey Secret。运行代码示例前必须先配置环境变量。
        String accessKeyId = System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID");
        String accessKeySecret = System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET");
        com.aliyun.imagerecog20190930.Client client = ImageServiceImpl.createClient(accessKeyId, accessKeySecret);
        // 场景一，使用本地文件
        // InputStream inputStream = new FileInputStream(new File("/tmp/ClassifyingRubbish.jpg"));
        // 场景二，使用任意可访问的url
//        URL url = new URL("https://viapi-test-bj.oss-cn-beijing.aliyuncs.com/viapi-3.0domepic/imagerecog/ClassifyingRubbish/ClassifyingRubbish1.jpg");
        InputStream inputStream = url.openConnection().getInputStream();
        com.aliyun.imagerecog20190930.models.ClassifyingRubbishAdvanceRequest classifyingRubbishAdvanceRequest = new com.aliyun.imagerecog20190930.models.ClassifyingRubbishAdvanceRequest()
                .setImageURLObject(inputStream);
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            ClassifyingRubbishResponse classifyingRubbishAdvanceResponse = client.classifyingRubbishAdvance(classifyingRubbishAdvanceRequest, runtime);
            // 获取整体结果
//            System.out.println(com.aliyun.teautil.Common.toJSONString(TeaModel.buildMap(classifyingRubbishAdvanceResponse)));
            // 获取单个字段
            return classifyingRubbishAdvanceResponse.getBody();
        } catch (TeaException teaException) {
            // 获取整体报错信息
            System.out.println(com.aliyun.teautil.Common.toJSONString(teaException));
            // 获取单个字段
            System.out.println(teaException.getCode());
        }
        return null;
    }
    @Override
    public Object ImageScanByLocalFile(MultipartFile file) throws Exception {
        // 创建AccessKey ID和AccessKey Secret，请参考https://help.aliyun.com/document_detail/175144.html
        // 如果您使用的是RAM用户的AccessKey，还需要为子账号授予权限AliyunVIAPIFullAccess，请参考https://help.aliyun.com/document_detail/145025.html
        // 从环境变量读取配置的AccessKey ID和AccessKey Secret。运行代码示例前必须先配置环境变量。
        String accessKeyId = System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID");
        String accessKeySecret = System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET");
        com.aliyun.imagerecog20190930.Client client = ImageServiceImpl.createClient(accessKeyId, accessKeySecret);
        System.out.println("fileeeeeeee" + file);
         //，使用本地文件
         InputStream inputStream = file.getInputStream();
        System.out.println("filellllllll" + inputStream);
        // 场景二，使用任意可访问的url
        //  URL url = new URL("https://viapi-test-bj.oss-cn-beijing.aliyuncs.com/viapi-3.0domepic/imagerecog/ClassifyingRubbish/ClassifyingRubbish1.jpg");
        //  InputStream inputStream = url.openConnection().getInputStream();
        com.aliyun.imagerecog20190930.models.ClassifyingRubbishAdvanceRequest classifyingRubbishAdvanceRequest = new com.aliyun.imagerecog20190930.models.ClassifyingRubbishAdvanceRequest()
                .setImageURLObject(inputStream);
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            ClassifyingRubbishResponse classifyingRubbishAdvanceResponse = client.classifyingRubbishAdvance(classifyingRubbishAdvanceRequest, runtime);
            // 获取整体结果
//            System.out.println(com.aliyun.teautil.Common.toJSONString(TeaModel.buildMap(classifyingRubbishAdvanceResponse)));
            // 获取单个字段
            return classifyingRubbishAdvanceResponse.getBody();
        } catch (TeaException teaException) {
            // 获取整体报错信息
            System.out.println(com.aliyun.teautil.Common.toJSONString(teaException));
            // 获取单个字段
            System.out.println(teaException.getCode());
        }
        return null;
    }
}
