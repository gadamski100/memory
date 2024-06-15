package pl.wszib.memoryapi.services;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.wszib.memoryapi.data.entities.CategoryEntity;
import pl.wszib.memoryapi.data.repository.CategoryRepository;
import pl.wszib.memoryapi.web.models.CategoryRequest;
import pl.wszib.memoryapi.web.models.CategoryResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public CategoryResponse createCategory(CategoryRequest request) {
        CategoryEntity categoryEntity = new CategoryEntity(request.name());
        CategoryEntity savedCategory = categoryRepository.save(categoryEntity);
        return new CategoryResponse(savedCategory);
    }

    public List<CategoryResponse> listCategories() {

//        ====streamowanie/mapowanie====
        return categoryRepository.findAll().stream().map(CategoryResponse::new).toList();



//        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
//        List<CategoryResponse> objects = new ArrayList<>();
//        for (CategoryEntity ce : categoryEntities) {
//            objects.add(new CategoryResponse(ce));
//        }
//        return objects;
    }

    public CategoryResponse getCategory(Long categoryId) {
        Optional<CategoryEntity> optional = categoryRepository.findById(categoryId);
        CategoryResponse categoryResponse = optional.map(CategoryResponse::new).orElseThrow(NotFoundException::new);
        return categoryResponse;
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
