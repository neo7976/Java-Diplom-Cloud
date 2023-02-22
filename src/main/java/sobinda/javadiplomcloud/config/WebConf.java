package sobinda.javadiplomcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConf implements WebMvcConfigurer {

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //устанавливаем разрешения с нашего хоста
                .allowedOrigins("http://localhost:8080", "http://localhost:8085")
                .allowCredentials(true)
                .allowedMethods("*")
                .allowedHeaders("*");
    }

//        @Override
//        public void addCorsMappings(CorsRegistry registry) {
//            registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE");
//        }
}

