package com.hy.flyy.controller;

import com.hy.flyy.config.RedisConfig;
import com.hy.flyy.config.UploadConfig;
import com.hy.flyy.utils.R;
import com.hy.flyy.utils.RedisUtils;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

/**
 * @author 黄勇
 * @since 2023/5/7
 */
@RestController
@CrossOrigin
public class FileController {

    @Autowired
    private UploadConfig uploadConfig;

    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            return R.fail("请选择需要上传的文件");
        }

//         获取原始文件名
        String filename = UUID.randomUUID()+file.getOriginalFilename();
        System.out.println(filename);

        if (!new File(uploadConfig.getDir()).exists()) {
            new File(uploadConfig.getDir()).mkdirs();
        }

        // 创建文件存储路径
        Path uploadPath = Paths.get(uploadConfig.getDir(), filename);
        redisUtils.setCacheObject("image", filename);

        // 将上传的图片存储到服务器指定路径
        Files.copy(file.getInputStream(), uploadPath);
        return R.success("文件上传成功");
    }
}
