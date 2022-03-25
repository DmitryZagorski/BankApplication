package home.intexsoft.bank_application.mvc_config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@ComponentScan({"home.intexsoft.bank_application.controller", "home.intexsoft.bank_application.service",
        "home.intexsoft.bank_application.dao", "home.intexsoft.bank_application.mvc_config"})
//@ComponentScan({"home.intexsoft.bank_application.command",
//        "home.intexsoft.bank_application.configuration",
//        "home.intexsoft.bank_application.controller",
//        "home.intexsoft.bank_application.dao",
//        "home.intexsoft.bank_application.service",
//        "home.intexsoft.bank_application.validation",
//        "home.intexsoft.bank_application.validation.commandValidators",
//        "home.intexsoft.bank_application"})
public class AppConfig {

}