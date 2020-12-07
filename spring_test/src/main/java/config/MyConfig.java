package config;

import bean.Account;
import bean.People;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    @Bean
    public Account account(){
        Account account = new Account(2,"wangwu",3000d);

        return account;
    }
    @Bean
    public People people(){
        People people = new People(170);
        return people;
    }
}
