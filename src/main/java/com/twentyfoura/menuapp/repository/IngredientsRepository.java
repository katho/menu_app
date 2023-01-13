package com.twentyfoura.menuapp.repository;

import com.twentyfoura.menuapp.entity.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientsRepository extends JpaRepository<IngredientEntity, Long> {
    Optional<IngredientEntity> getIngredientByIngredientId(Long Id);
    Optional<IngredientEntity> getIngredientByIngredient(String ingredient);

}
