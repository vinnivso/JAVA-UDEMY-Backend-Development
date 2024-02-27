package com.vinnivso.animalcontrolms.service;

import com.vinnivso.animalcontrolms.shared.AnimalDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnimalService {
    AnimalDTO createAnimal(AnimalDTO dto);
    List<AnimalDTO> getAllAnimals();
    Optional<AnimalDTO> getAnimalById(UUID id);
    List<AnimalDTO> getAnimalByOwnerId(UUID ownerId);
    void deleteAnimal(UUID id);
    boolean defineIsAlive(UUID id);
    AnimalDTO updateAnimal(UUID id, AnimalDTO dto);
}
