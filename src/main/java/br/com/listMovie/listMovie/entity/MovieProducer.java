package br.com.listMovie.listMovie.entity;


import javax.persistence.*;

@Entity
@Table(name = "db_movie_producer")
public class MovieProducer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "MOVIE_ID", referencedColumnName = "id", nullable = false)
    private Movie movie;

    @OneToOne
    @JoinColumn(name = "PRODUCER_ID", referencedColumnName = "id", nullable = false)
    private Producer producer;

    public MovieProducer() {
    }
    public MovieProducer(Movie movie, Producer producer) {
        this.movie = movie;
        this.producer = producer;
    }
    public MovieProducer(Long id, Movie movie, Producer producer) {
        this.id = id;
        this.movie = movie;
        this.producer = producer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }
}
