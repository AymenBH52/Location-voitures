package com.tekup.location_voitures.dao.entities;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory("voiture-images", registry);
    }

    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path VoitureUploadDir = Paths.get("./voiture-images");
        String VoitureUploadPath = VoitureUploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/voiture-images/**").addResourceLocations("file:/" + VoitureUploadPath + "/");
    }
}
