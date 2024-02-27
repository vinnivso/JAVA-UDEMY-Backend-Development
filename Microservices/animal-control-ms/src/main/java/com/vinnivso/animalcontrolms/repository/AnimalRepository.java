package com.vinnivso.animalcontrolms.repository;

import com.vinnivso.animalcontrolms.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AnimalRepository extends JpaRepository<Animal, UUID> {
    List<Animal> findAnimalByOwnerId(UUID ownerId);
}
