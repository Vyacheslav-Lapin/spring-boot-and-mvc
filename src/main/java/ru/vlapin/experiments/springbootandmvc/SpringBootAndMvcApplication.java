package ru.vlapin.experiments.springbootandmvc;

import java.util.Arrays;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.vlapin.experiments.springbootandmvc.dao.CatRepository;
import ru.vlapin.experiments.springbootandmvc.model.Cat;

@SpringBootApplication
public class SpringBootAndMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAndMvcApplication.class, args);
	}

	@Bean
  ApplicationRunner applicationRunner(CatRepository catRepository) {
	    return arguments -> Arrays.stream(
          "Барсик, Мурзик, Мурка".split(", "))
                       .map(Cat::new)
                       .forEach(catRepository::save);
	}
}

