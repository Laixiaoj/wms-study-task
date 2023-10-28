package vip.study.parent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ServletComponentScan
@EnableWebMvc  // 解决报 documentationPluginsBootstrapper 的编译错误
public class LoadServer {
    public static void  main(String[] args){
        SpringApplication.run(LoadServer.class, args);
    }
}
