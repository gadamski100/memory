package pl.wszib.memoryapi.data.entities;

import jakarta.persistence.*;
import pl.wszib.memoryapi.web.models.CategoryRequest;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    private String name;
    private Date createdDate;
@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
@JoinColumn(name = "category_id")
    private Set<CardEntity> cards = new HashSet<>();

    @PrePersist
    public void prePersist() {
        createdDate = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEntity() {
    }

    public CategoryEntity(String name) {
        this.name = name;
    }

    public void addCard(CardEntity card) {
        this.cards.add(card);
    }

    public Set<CardEntity> getCards() {
        return Set.copyOf(cards);
    }
}
