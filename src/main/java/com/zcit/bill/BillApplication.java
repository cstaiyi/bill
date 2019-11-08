package com.zcit.bill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.zcit"})
@EnableTransactionManagement
@MapperScan("com.zcit.bill.**.mapper")
@ConfigurationProperties
public class BillApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillApplication.class, args);
        System.out.println("Service started successfully!");
    }

}
