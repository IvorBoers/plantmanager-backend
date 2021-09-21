package nl.iboers.garden.plantmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class PlantManagerConfiguration extends WebMvcConfigurationSupport {
    public static final String BASE_URL = "/api/";

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
        super.addCorsMappings(registry);
    }


    /**
     * Provides serialization support for jackson's date provided by java8 such as LocalDate
     */
    @Bean(name = "mapperObject")
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper().registerModule(new ParameterNamesModule()).registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());

    }
//
//    @Bean
//    @Override
//    public FormattingConversionService mvcConversionService() {
//        var conversionService = new DefaultFormattingConversionService(false);
//
//        var dateTimeRegistrar = new DateTimeFormatterRegistrar();
//        dateTimeRegistrar.setDateFormatter(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
//        dateTimeRegistrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
//        dateTimeRegistrar.registerFormatters(conversionService);
//
//        var dateRegistrar = new DateFormatterRegistrar();
//        dateRegistrar.setFormatter(new DateFormatter("dd.MM.yyyy"));
//        dateRegistrar.registerFormatters(conversionService);
//
//        return conversionService;
//    }
}
