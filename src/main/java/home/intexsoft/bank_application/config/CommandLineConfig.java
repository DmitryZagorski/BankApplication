package home.intexsoft.bank_application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({GeneralConfig.class, BankAppRunnerConfiguration.class})
public class CommandLineConfig {

}

