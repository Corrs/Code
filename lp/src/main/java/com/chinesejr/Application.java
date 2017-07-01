package com.chinesejr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;

/**
 * @since 2015-12-12 18:22
 */
@Controller
/*@EnableWebMvc*/
@SpringBootApplication
@MapperScan(basePackages = "com.chinesejr.mapper")
//public class Application extends WebMvcConfigurerAdapter {
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
   /* @RequestMapping("/")
    String home() {
//    	return "redirect:countries";
    	return "forward:/index.html";
    }*/
    
    /*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    	// TODO Auto-generated method stub
    	return builder.sources(Application.class);
    }*/
    
    
}
