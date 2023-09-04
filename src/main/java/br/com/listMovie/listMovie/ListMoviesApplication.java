package br.com.listMovie.listMovie;

import br.com.listMovie.listMovie.service.FileService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;


@EntityScan(basePackages = "br.com.listMovie.listMovie.entity")
@SpringBootApplication
public class ListMoviesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListMoviesApplication.class, args);
	}

	@Autowired
	private FileService fileService;
	@PostConstruct
	public void buildData() {
		fileService.buildDataFile();
	}
}
