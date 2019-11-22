package com.project.common.config;

import com.project.common.entity.FileInfo;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * WebMvc配置
 *
 * @author zhuyifa
 * @since 2019-07-11
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 添加资源处理器
     *
     * @param registry 资源处理器注册表
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(FileInfo.PREFIX + "**")
                .addResourceLocations("file:" + FileInfo.DEST_PATH + FileInfo.PREFIX);
    }
}
