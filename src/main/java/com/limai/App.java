package com.limai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.limai.dao")
@EnableTransactionManagement
public class App
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);
    }
}
