package com.zcit.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by huangyifeng on 2019/4/29.
 */
@Data
@Component
@ConfigurationProperties(prefix = "config-attributes")
public class MyConfig {

    // 配置排除拦截路径
    private List<String> excludePathPatterns;
}
