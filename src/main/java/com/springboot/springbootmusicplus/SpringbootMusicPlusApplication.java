package com.springboot.springbootmusicplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.springboot.springbootmusicplus.*.repository")
@SpringBootApplication
public class SpringbootMusicPlusApplication {

    /**
     * @author 林李权
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringbootMusicPlusApplication.class, args);
    }

}
