package com.vinnivso.animalcontrolms.service;

import com.vinnivso.animalcontrolms.model.Animal;
import com.vinnivso.animalcontrolms.repository.AnimalRepository;
import com.vinnivso.animalcontrolms.shared.AnimalDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AnimalServiceImpl implements AnimalService {
    @Autowired
    private AnimalRepository repository;

    /**
     * Creates a new animal based on the provided DTO.
     *
     * @param dto the DTO containing the information of the animal to be created
     * @return the DTO of the created animal
     */
    @Override
    public AnimalDTO createAnimal(AnimalDTO dto) {
        return saveAnimal(dto);
    }

    /**
     * Retrieves all animals and maps them to DTOs.
     *
     * @return         	list of AnimalDTO objects
     */
    @Override
    public List<AnimalDTO> getAllAnimals() {
        List<Animal> animals = repository.findAll();

        return animals.stream()
                .map(animal -> new ModelMapper().map(animal, AnimalDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieve an animal by its ID.
     *
     * @param id The ID of the animal to retrieve
     * @return An Optional containing the AnimalDTO if found, otherwise an empty Optional
     */
    @Override
    public Optional<AnimalDTO> getAnimalById(UUID id) {
        Optional<Animal> animal = repository.findById(id);

        if(animal.isPresent()) {
            return Optional.of(new ModelMapper().map(animal.get(), AnimalDTO.class));
        }

        return Optional.empty();
    }

    /**
     * Retrieve a list of animals by owner ID.
     * @param ownerId The ID of the owner
     * @return List of AnimalDTO objects
     */
    @Override
    public List<AnimalDTO> getAnimalByOwnerId(UUID ownerId) {
        // Retrieve animals from the repository by owner ID
        List<Animal> animals = repository.findAnimalByOwnerId(ownerId);

        // Map each Animal object to AnimalDTO using ModelMapper and collect into a list
        return animals.stream()
                .map(animal -> new ModelMapper().map(animal, AnimalDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Deletes an animal with the specified ID.
     *
     * @param  id  the ID of the animal to be deleted
     */
    @Override
    public void deleteAnimal(UUID id) {
        repository.deleteById(id);
    }

    /**
     * Updates the isAlive status of an animal with the given ID.
     *
     * @param id the ID of the animal
     * @return true if the animal is found and its status is updated, false otherwise
     */
    @Override
    public boolean defineIsAlive(UUID id) {
        Optional<Animal> animal = repository.findById(id);
        if(animal.isPresent()) {
            animal.get().setIsAlive(false);
            repository.save(animal.get());
            return true;
        }
        return false;
    }

    /**
     * Updates an animal with the given ID using the provided data.
     *
     * @param id The ID of the animal to be updated
     * @param dto The data with which to update the animal
     * @return The updated animal data
     */
    @Override
    public AnimalDTO updateAnimal(UUID id, AnimalDTO dto) {
        dto.setId(id);
        return saveAnimal(dto);
    }

    /**
     * Saves the given animal DTO to the database.
     *
     * @param dto the animal DTO to be saved
     * @return the saved animal DTO
     */
    private AnimalDTO saveAnimal(AnimalDTO dto) {
        // Create a ModelMapper instance
        ModelMapper mapper = new ModelMapper();

        // Map the DTO to an Animal entity
        Animal animal = mapper.map(dto, Animal.class);

        // Save the Animal entity to the database
        animal = repository.save(animal);

        // Map the saved Animal entity back to an AnimalDTO and return it
        return mapper.map(animal, AnimalDTO.class);
    }
}
