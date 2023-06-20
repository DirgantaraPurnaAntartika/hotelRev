package id.co.indivara.finalproject.hotelrev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@SpringBootApplication
public class HotelrevApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelrevApplication.class, args);
	}

	@Bean
	public InternalResourceViewResolver defaultViewResolver(){return new InternalResourceViewResolver();}

}

//INI PERUBAHAN TERBARU
//OKEE BROOOOO
