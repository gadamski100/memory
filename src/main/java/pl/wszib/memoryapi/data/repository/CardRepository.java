package pl.wszib.memoryapi.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.wszib.memoryapi.data.entities.CardEntity;
import pl.wszib.memoryapi.data.entities.CategoryEntity;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {

    @Query(value = "SELECT * from cards WHERE id = :cardId and category_id = :categoryId", nativeQuery = true)
    Optional<CardEntity> findByIdAndCategoryID(Long cardId, Long categoryId);


}
