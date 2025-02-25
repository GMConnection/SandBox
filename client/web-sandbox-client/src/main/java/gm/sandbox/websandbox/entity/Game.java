package gm.sandbox.websandbox.entity;


import gm.sandbox.websandbox.type.GenreType;
import gm.sandbox.websandbox.type.PlatformType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "game_master", schema = "game")
public class Game {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private GenreType genre;
    @Column(name = "platform")
    @Enumerated(EnumType.STRING)
    private PlatformType platform;
    @Column(name = "rating")
    private String rating;
    @Column(name = "description")
    private String description;

    public Game(Long id, String name, GenreType genre, PlatformType platform, String rating, String description) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.platform = platform;
        this.rating = rating;
        this.description = description;
    }

    public Game() {
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

    public GenreType getGenre() {
        return genre;
    }

    public void setGenre(GenreType genre) {
        this.genre = genre;
    }

    public PlatformType getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformType platform) {
        this.platform = platform;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
