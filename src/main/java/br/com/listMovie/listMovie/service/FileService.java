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

    private final String FILE_PATH = "files/movielist.csv";

    public void buildDataFile() {
        try {
            List<String[]> contentFile = readFiles(FILE_PATH);

            contentFile.stream().forEach(line -> {
                Long year = Long.parseLong(line[0]);
                String title = line[1];
                String studios = line[2];
                String producers = line[3];
                String winner = Utils.validaString(line[4]).equalsIgnoreCase("yes") ? "S" : "N";

                Movie movie = movieService.save(new Movie(year, title, studios, winner));

                String[] producerNames = producers.split(",");
                Arrays.stream(producerNames)
                        .map(String::trim)
                        .forEach(producerName -> movieToProducer(movie, producerName));
            });

            System.out.println("CSV CARREGADO COM SUCESSO!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERRO AO CARREGAR OS DADOS: " + e.getMessage());
        }
    }

    private Producer vallInfoProducer(Movie movie, String producerName){
        return producerService.save(new Producer(producerName.trim()));
    }


    private void movieToProducer(Movie movie, String producerName) {
        if (movie != null && !producerName.trim().isEmpty()) {
            Producer producer = producerService.getByName(producerName.trim());

            if (producer == null) {
                producer = vallInfoProducer(movie, producerName);
            }

            movieProducerService.save(new MovieProducer(movie, producer));
        }
    }

    private List<String[]> readFiles(String filePath) throws IOException {
        try {
            URL res = getClass().getClassLoader().getResource(filePath);
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