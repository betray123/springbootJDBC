package com.zk.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zk on 18/6/27.
 */

@Configuration
public class DruidConfig {

    //自动配置druid数据源,加入容器
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }

    //配置druid监控
    //1.配置一个管理后台的servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        //配置管理后台的servlet:StatViewServlet,以及配置后台servlet访问路径:/druid/*
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        //设置初始化参数:从StatViewServlet的父类中找到相对应的登陆账号密码参数配置
        Map<String, String> map = new HashMap<>();
        map.put("loginUsername","root");
        map.put("loginPassword","123456");
        map.put("allow","");//配置允许哪些端口访问,默认全部
//        map.put("deny","");//配置拒绝哪些端口访问
        servletRegistrationBean.setInitParameters(map);
        return servletRegistrationBean;
    }

    //2.配置一个监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());

        //设置初始化参数:从WebStatFilter中找对应的参数,放到map中的key,value为对应的配置值.
        Map<String, String> map = new HashMap<>();
        map.put("exclusions","*.js,*.css,/druid/*");//设置排除一些拦截请求
        filterRegistrationBean.setInitParameters(map);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));//拦截所有请求
        return filterRegistrationBean;
    }

}
