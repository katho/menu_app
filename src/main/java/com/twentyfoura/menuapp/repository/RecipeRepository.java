package com.twentyfoura.menuapp.repository;

import com.twentyfoura.menuapp.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
    Optional<RecipeEntity> getRecipeByName(String name);
    Optional<RecipeEntity> getRecipeById(int id);
}
