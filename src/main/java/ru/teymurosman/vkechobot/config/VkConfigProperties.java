package ru.teymurosman.vkechobot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for vk api properties
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "vk.api")
public class VkConfigProperties {

    private String token;

    private String confirmation;

    private String secret;

    private String version;
}
