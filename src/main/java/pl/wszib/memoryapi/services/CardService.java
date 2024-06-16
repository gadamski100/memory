package pl.wszib.memoryapi.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.wszib.memoryapi.data.entities.CardEntity;
import pl.wszib.memoryapi.data.entities.CategoryEntity;
import pl.wszib.memoryapi.data.repository.CardRepository;
import pl.wszib.memoryapi.data.repository.CategoryRepository;
import pl.wszib.memoryapi.web.models.CardRequest;
import pl.wszib.memoryapi.web.models.CardResponse;

import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final CategoryRepository categoryRepository;
    public CardService(CardRepository cardRepository, CategoryRepository categoryRepository) {
        this.cardRepository = cardRepository;
        this.categoryRepository = categoryRepository;
    }
@Transactional
    public CardResponse createCard(Long categoryId, CardRequest request) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow(NotFoundException::new);
        CardEntity cardEntity = new CardEntity(request.term(), request.definition());
        CardEntity savedCard = cardRepository.save(cardEntity);
        categoryEntity.addCard(savedCard);
        return new CardResponse(categoryId, savedCard);
    }

    public List<CardResponse> fetchAll(Long categoryId) {
        CategoryEntity category = categoryRepository.findById(categoryId).orElseThrow(NotFoundException::new);
        return category.getCards().stream().map(c -> new CardResponse(categoryId, c)).toList();
    }
    @Transactional
    public void removeCard(Long categoryId, Long cardId) {
//        cardRepository.findByIdAndCategoryID(cardId, categoryId).ifPresent(cardRepository::delete);
        categoryRepository.findById(categoryId).ifPresent(category -> category.removeCard(cardId));
    }
}
