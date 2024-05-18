package hackathon.EchoPangPang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableJpaAuditing
public class EchoPangPangApplication {

	public static void main(String[] args) {
		SpringApplication.run(EchoPangPangApplication.class, args);
	}


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:20007", "https://ecopuangpuang.putiez.com"
								, "http://m.tongtongtripmap.com", "https://m.tongtongtripmap.com")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTION")
						.allowCredentials(true).allowedHeaders("*");//;;

				////.allowedHeaders("*");;
			}
		};
	}

}
