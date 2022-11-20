package online.store.project.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig extends WebSecurityConfigurerAdapter{
    @Bean
    public Docket productApi() {
        String groupName = "products";
        Docket pro=new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("online.store.project"))
                .paths(regex("/product.*"))
                .build()
                .groupName(groupName)
                .apiInfo(metaData());;
        return pro;
    }
    @Bean
    public Docket UserApi() {
        String groupName = "customers";
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("online.store.project"))
                .paths(regex("/customers.*"))
                .build()
                .groupName(groupName)
                .apiInfo(metaData());
    }
    @Bean
    public Docket StoreApi() {
        String groupName = "stores";
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("online.store.project"))
                .paths(regex("/store.*"))
                .build()
                .groupName(groupName)
                .apiInfo(metaData());
    }
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Spring Boot REST API")
                .description("\"Spring Boot REST API for Online Store\"")
                .version("1.0.0")
                .build();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }



}
