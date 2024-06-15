package pl.wszib.memoryapi.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wszib.memoryapi.data.entities.CardEntity;
import pl.wszib.memoryapi.data.entities.CategoryEntity;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {

}
