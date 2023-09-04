package br.com.listMovie.listMovie.service;

import br.com.listMovie.listMovie.entity.Movie;
import br.com.listMovie.listMovie.entity.MovieProducer;
import br.com.listMovie.listMovie.entity.Producer;
import br.com.listMovie.listMovie.commons.Utils;
import com.opencsv.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
public class FileService {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ProducerService producerService;

    @Autowired
    private MovieProducerService movieProducerService;

    private final String URL_FILE = "files/movielist.csv";

    public void buildDataFile() {
        try {
            List<String[]> contentFile = readFile();
            Movie movie = null;
            for (String[] line : contentFile) {
                Long year = Long.parseLong(line[0]);
                String title = line[1];
                String studios = line[2];
                String producers = line[3];
                String winner = Utils.validaString(line[4]).toLowerCase().equalsIgnoreCase("yes") ? "S" : "N";
                movie = new Movie(year, title, studios, winner);
                movie = this.movieService.save(movie);
                String[] productorsSplit = producers.split(",");
                for (String producerName : productorsSplit) {
                    if (producerName.contains(" and ")) {
                        String[] prdcsAux = producerName.split(" and ");
                        for (String name : prdcsAux) {
                            if (name != null && !name.equalsIgnoreCase("")) {
                                this.linkMovieToProducer(movie, name);
                            }
                        }
                    } else {
                        if (producerName != null && !producerName.equalsIgnoreCase("")) {
                            this.linkMovieToProducer(movie, producerName);
                        }
                    }
                }
            }
            System.out.println("ARQUIVO CARREGADO COM SUCESSO NA BASE DE DADOS.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERRO AO CARREGAR OS DADOS DO ARQUIVO: " + e.getMessage());
        }
    }

    private void linkMovieToProducer(Movie movie, String producerName) {
        if (movie != null && !Utils.validaString(producerName).isEmpty()) {
            Producer producer = this.producerService.getByName(producerName.trim());
            if (producer == null || producer.getId() == null) {
                producer = this.producerService.save(new Producer(producerName.trim()));
            }

            MovieProducer movieProducer = new MovieProducer(movie, producer);
            this.movieProducerService.save(movieProducer);
        }

    }

    private List<String[]> readFile() {
        try {
            URL res = getClass().getClassLoader().getResource(URL_FILE);
            Reader reader = Files.newBufferedReader(Paths.get(res.toURI()));

            final CSVParser parser = new CSVParserBuilder().withSeparator(';').withIgnoreQuotations(false).build();
            CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(parser).withSkipLines(1).build();
            return csvReader.readAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}