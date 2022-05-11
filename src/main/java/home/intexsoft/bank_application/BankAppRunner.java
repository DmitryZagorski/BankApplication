package home.intexsoft.bank_application;

import home.intexsoft.bank_application.config.CommandLineConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan
@Profile("commandLine")
public class BankAppRunner {

    private static final Logger log = LoggerFactory.getLogger(BankAppRunner.class);

    private Menu menu;

    public static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(CommandLineConfig.class);

    @Autowired
    public BankAppRunner(Menu menu) {
        this.menu = menu;
    }

    public static void main(String[] args) {
        log.debug("PSVM started");
        Menu menu = applicationContext.getBean(Menu.class);
        menu.runMenu();
        log.debug("PSVM finished");

//        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);

    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}