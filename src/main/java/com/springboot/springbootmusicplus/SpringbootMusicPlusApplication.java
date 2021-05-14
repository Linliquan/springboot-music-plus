package com.springboot.springbootmusicplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.springboot.springbootmusicplus.*.repository")
@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication
public class SpringbootMusicPlusApplication {

    /**
     * @author 林李权
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringbootMusicPlusApplication.class, args);
    }

}
