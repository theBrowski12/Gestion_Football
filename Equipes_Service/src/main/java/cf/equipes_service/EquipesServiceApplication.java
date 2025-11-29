package cf.equipes_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
//@EnableConfigurationProperties(RsaKeys.class)
public class EquipesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EquipesServiceApplication.class, args);
    }

}
