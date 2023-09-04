package br.com.listMovie.listMovie.service;

import br.com.listMovie.listMovie.entity.Producer;
import br.com.listMovie.listMovie.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    @Autowired
    private ProducerRepository producerRepositiry;

    public Producer getByName(String name) {
        return this.producerRepositiry.getEntityByName(name);
    }


    public Producer save(Producer producer) {
        return this.producerRepositiry.save(producer);
    }
}