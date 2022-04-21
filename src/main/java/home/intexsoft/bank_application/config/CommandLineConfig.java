package home.intexsoft.bank_application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("commandLine")
@Import({GeneralConfig.class, BankAppRunnerConfiguration.class})
public class CommandLineConfig {

}

