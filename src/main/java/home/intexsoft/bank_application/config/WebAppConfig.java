package home.intexsoft.bank_application.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@Profile("web")
@ComponentScan(basePackages = "home.intexsoft.bank_application.*")
public class WebAppConfig {

}