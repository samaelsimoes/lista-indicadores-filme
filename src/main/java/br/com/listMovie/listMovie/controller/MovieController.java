package br.com.listMovie.listMovie.controller;

import br.com.listMovie.listMovie.dataDto.DataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.listMovie.listMovie.service.MovieService;

@RestController
@RequestMapping(path = "/movie")
@CrossOrigin(origins="*")
public class MovieController  {
    @Autowired
    private MovieService movieService;

    @GetMapping("/producers/interval")
    private DataResponse getProducersIntervalMinMax() {
        return this.movieService.getProducersIntervalMinMax();
    }
}
