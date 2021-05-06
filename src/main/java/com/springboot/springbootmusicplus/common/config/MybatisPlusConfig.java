package com.springboot.springbootmusicplus.common.config;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author linliquan
 * @description:
 * @create 2021/5/6 15:23
 */
@Configuration
@MapperScan("com.springboot.springbootmusicplus.dao.repository")
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        paginationInterceptor.setOverflow(true);

        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        paginationInterceptor.setLimit(500);
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }

    @Bean
    public MybatisPlusPropertiesCustomizer plusPropertiesCustomizer() {
        return plusProperties -> plusProperties.getGlobalConfig().getDbConfig().setIdType(IdType.AUTO);
    }
}
