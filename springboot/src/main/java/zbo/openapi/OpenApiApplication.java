package zbo.openapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "zbo.openapi")
public class OpenApiApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(OpenApiApplication.class, args);
    }
}
