package com.vinnivso.animalcontrolms.view.controller;

import com.vinnivso.animalcontrolms.service.AnimalService;
import com.vinnivso.animalcontrolms.shared.AnimalDTO;
import com.vinnivso.animalcontrolms.view.model.AnimalModelInput;
import com.vinnivso.animalcontrolms.view.model.AnimalModelResponse;
import com.vinnivso.animalcontrolms.view.model.AnimalModelUpdate;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/animal")
public class AnimalController {
    @Autowired
    private AnimalService service;

    /**
     * Create a new animal based on the provided animal model input
     *
     * @param animal the animal model input
     * @return the response entity containing the created animal model response
     */
    @PostMapping
    public ResponseEntity<AnimalModelResponse> createAnimal(@RequestBody @Valid AnimalModelInput animal) {
        ModelMapper mapper = new ModelMapper();
        AnimalDTO dto = mapper.map(animal, AnimalDTO.class);
        dto = service.createAnimal(dto);
        return new ResponseEntity<>(mapper.map(dto, AnimalModelResponse.class), HttpStatus.CREATED);
    }

    /**
     * Retrieve all animals from the database
     * @return ResponseEntity with a list of AnimalModelResponse
     */
    @GetMapping
    public ResponseEntity<List<AnimalModelResponse>> getAllAnimals() {
        // Retrieve all animal data transfer objects from the service
        List<AnimalDTO> dtos = service.getAllAnimals();

        // If there are no animals, return a response with status NO_CONTENT
        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // Map the animal data transfer objects to model responses using ModelMapper
        ModelMapper mapper = new ModelMapper();
        List<AnimalModelResponse> response = dtos.stream()
                .map(dto -> mapper.map(dto, AnimalModelResponse.class))
                .collect(Collectors.toList());

        // Return a response with the list of model responses and status OK
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Retrieves an animal by its ID.
     *
     * @param id The ID of the animal to retrieve
     * @return ResponseEntity with the animal model response if found, or NO_CONTENT status if not found
     */
    @GetMapping(value="/{id}")
    public ResponseEntity<AnimalModelResponse> getAnimalById(@PathVariable UUID id) {
        Optional<AnimalDTO> Animal = service.getAnimalById(id);

        if(Animal.isPresent()) {
            // Map the DTO to the response model and return OK status
            return new ResponseEntity<>(
                    new ModelMapper().map(Animal.get(), AnimalModelResponse.class),
                    HttpStatus.OK
            );
        }

        // Return NO_CONTENT status if animal is not found
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Retrieves a list of animals belonging to the specified owner.
     *
     * @param ownerId the unique identifier of the owner
     * @return a ResponseEntity containing a list of AnimalModelResponse objects
     */
    @GetMapping(value="/{ownerId}/list")
    public ResponseEntity<List<AnimalModelResponse>> getAnimalByOwnerId(@PathVariable UUID ownerId) {
        // Retrieve AnimalDTO objects based on ownerId
        List<AnimalDTO> dtos = service.getAnimalByOwnerId(ownerId);

        // If no animals are found, return a 204 No Content status
        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // Map AnimalDTO objects to AnimalModelResponse objects
        ModelMapper mapper = new ModelMapper();
        List<AnimalModelResponse> resp = dtos.stream()
                .map(dto -> mapper.map(dto, AnimalModelResponse.class))
                .collect(Collectors.toList());

        // Return a ResponseEntity containing the list of AnimalModelResponse objects with a 200 OK status
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    /**
     * Removes an animal by its ID.
     *
     * @param id The ID of the animal to be removed
     * @return ResponseEntity with no content
     */
    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> removerAnimal(@PathVariable UUID id) {
        // Delete the animal from the database
        service.deleteAnimal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Update the state of a specific entity to "dead".
     * @param id The unique identifier of the entity
     * @return ResponseEntity with status code 200 if successful, or 204 if the entity is already dead
     */
    @PatchMapping(value="/{id}")
    public ResponseEntity<Void> defineIsAlive(@PathVariable UUID id) {
        if(service.defineIsAlive(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Update animal by ID
     *
     * @param id The ID of the animal to update
     * @param Animal The updated animal data
     * @return ResponseEntity containing the updated animal model response
     */
    @PutMapping(value="/{id}")
    public ResponseEntity<AnimalModelResponse> updateAnimal(@PathVariable UUID id,
                                                            @Valid @RequestBody AnimalModelUpdate Animal) {
        ModelMapper mapper = new ModelMapper();
        AnimalDTO dto = mapper.map(Animal, AnimalDTO.class);
        dto = service.updateAnimal(id, dto);

        return new ResponseEntity<>(mapper.map(dto, AnimalModelResponse.class), HttpStatus.OK);
    }
}
