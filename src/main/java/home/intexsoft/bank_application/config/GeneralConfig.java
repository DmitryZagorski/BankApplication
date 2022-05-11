package home.intexsoft.bank_application.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"home.intexsoft.bank_application.service",
        "home.intexsoft.bank_application.dao",
        "home.intexsoft.bank_application.command",
        "home.intexsoft.bank_application.validation",
        "home.intexsoft.bank_application.jms",
        "home.intexsoft.bank_application.mapper"})
public class GeneralConfig {

}
