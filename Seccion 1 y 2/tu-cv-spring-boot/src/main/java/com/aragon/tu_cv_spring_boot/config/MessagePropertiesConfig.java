package com.aragon.tu_cv_spring_boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;


//esta clase sirve para conectar todos los archivos .properties con una configuracion , excepto el principal que es application todos los demas son personalizables
@Configuration
@PropertySources({
            @PropertySource("classpath:message.properties")
})
public class MessagePropertiesConfig {
}
