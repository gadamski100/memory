package pl.wszib.memoryapi.web.models;

import pl.wszib.memoryapi.data.entities.CategoryEntity;

public record CategoryResponse(Long id, String name) {

    public CategoryResponse(CategoryEntity entity) {
        this(entity.getId(), entity.getName());
    }

    public CategoryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
