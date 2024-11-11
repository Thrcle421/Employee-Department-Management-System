package com.aliyun.oss;

import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 阿里云 OSS 工具类
 */
//@Component
public class AliOSSUtils {

//    @Autowired
    private AliOSSProperties aliossproperties;

    public AliOSSProperties getAliossproperties() {
        return aliossproperties;
    }

    public void setAliossproperties(AliOSSProperties aliossproperties) {
        this.aliossproperties = aliossproperties;
    }

    /**
     * 实现上传图片到OSS
     */
    public String uploadss(MultipartFile file) throws IOException, ClientException {
        String endpoint=aliossproperties.getEndpoint();
        String bucketName=aliossproperties.getBucketName();

        // 获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();

        // 避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        // 使用 EnvironmentVariableCredentialsProvider 获取访问凭证
        EnvironmentVariableCredentialsProvider credentialsProvider =
                CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        // 创建 OSS 客户端
        OSS ossClient = new OSSClientBuilder()
                .build(endpoint, credentialsProvider);

        // 上传文件到 OSS
        ossClient.putObject(bucketName, fileName, inputStream);

        // 文件访问路径
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;

        // 关闭ossClient
        ossClient.shutdown();
        return url; // 把上传到oss的路径返回
    }

}
