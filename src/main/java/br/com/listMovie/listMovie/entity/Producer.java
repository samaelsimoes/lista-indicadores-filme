package br.com.listMovie.listMovie.entity;

import javax.persistence.*;

@Entity
@Table(name = "db_producer")
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 255)
    private String name;

    public Producer() {
    }

    public Producer(String name) {
        this.name = name;
    }

    public Producer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
