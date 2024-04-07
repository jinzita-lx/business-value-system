package com.jinzita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author jinzita
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class JinzitaApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(JinzitaApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  项目启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
