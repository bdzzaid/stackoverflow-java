package zbo.openapi;

import io.swagger.v3.oas.models.parameters.HeaderParameter;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "zbo.openapi")
public class OpenApiApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(OpenApiApplication.class, args);
    }

    // Resolve https://stackoverflow.com/questions/64489812/how-to-set-additional-properties-to-boolean/64490457#64490457
    @Bean
    public OpenApiCustomiser openApiCustomiser() {
        return openApi -> openApi.getComponents().getSchemas().values().forEach( s -> s.setAdditionalProperties(false));
    }
}
