package com.hy.flyy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 黄勇
 * @since 2023/5/7
 */
@Configuration
@ConfigurationProperties(prefix = "app.upload")
public class UploadConfig {
    private String dir;

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

}
