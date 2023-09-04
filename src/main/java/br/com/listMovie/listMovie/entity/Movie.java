package br.com.listMovie.listMovie.entity;


import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "db_movie")
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "YEAR_WIN", nullable = false, length = 4)
    private Long year;

    @Column(name = "TITLE", nullable = false, length = 255)
    private String title;

    @Column(name = "STUDIOS", nullable = false, length = 255)
    private String studios;

    @Column(name = "WINNER", nullable = true, length = 1)
    private String winner;

    public Movie() {
    }

    public Movie(Long year, String title, String studios, String winner) {
        this.year = year;
        this.title = title;
        this.studios = studios;
        this.winner = winner;
    }

    public Movie(Long id, Long year, String title, String studios, String winner) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.studios = studios;
        this.winner = winner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudios() {
        return studios;
    }

    public void setStudios(String studios) {
        this.studios = studios;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}