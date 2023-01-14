package edu.virginia.cs.application_submission;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
@MapperScan("edu.virginia.cs.application_submission.mapper")
public class ApplicationSubmissionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationSubmissionApplication.class, args);
    }

}
