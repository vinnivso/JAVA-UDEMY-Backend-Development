package com.vinnivso.peoplecontrolms.view.controller;

import com.vinnivso.peoplecontrolms.model.People;
import com.vinnivso.peoplecontrolms.service.PeopleService;
import com.vinnivso.peoplecontrolms.shared.PeopleDTO;
import com.vinnivso.peoplecontrolms.view.model.PeopleModelRequest;
import com.vinnivso.peoplecontrolms.view.model.PeopleModelResponse;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/people")
public class PessoaController {
    @Autowired
    private PeopleService service;

    @PostMapping
    public ResponseEntity<PeopleModelResponse> createPeople(@RequestBody @Valid PeopleModelRequest people) {
        // Create a new instance of ModelMapper
        ModelMapper mapper = new ModelMapper();

        // Map the PeopleModelRequest to PeopleDTO using the mapper
        PeopleDTO dto = mapper.map(people, PeopleDTO.class);

        // Create the people using the service
        dto = service.createPeople(dto);

        // Map the created PeopleDTO to PeopleModelResponse and return as ResponseEntity with HttpStatus.CREATED
        return new ResponseEntity<>(mapper.map(dto, PeopleModelResponse.class), HttpStatus.CREATED);
    }

    /**
     * Retrieves all people from the database and maps them to a response model.
     * @return ResponseEntity with a list of PeopleModelResponse
     */
    @GetMapping
    public ResponseEntity<List<PeopleModelResponse>> getAllPeople() {
        // Retrieve all people from the service
        List<PeopleDTO> dtos = service.getAllPeople();

        // If there are no people, return no content
        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // Map the DTOs to response model using ModelMapper
        ModelMapper mapper = new ModelMapper();
        List<PeopleModelResponse> resp = dtos.stream()
                .map(dto -> mapper.map(dto, PeopleModelResponse.class))
                .collect(Collectors.toList());

        // Return the mapped response with OK status
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    /**
     * Get people by ID
     * @param id The ID of the person
     * @return ResponseEntity with the PeopleModelResponse if found, otherwise HttpStatus.NO_CONTENT
     */
    @GetMapping(value="/{id}")
    public ResponseEntity<PeopleModelResponse> getPeopleById(@PathVariable UUID id) {
        Optional<PeopleDTO> people = service.getPeopleById(id);

        if(people.isPresent()) {
            return new ResponseEntity<>(
                    new ModelMapper().map(people.get(), PeopleModelResponse.class),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Delete a person by ID.
     *
     * @param id The ID of the person to delete
     * @return A response entity with no content
     */
    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> deletePeople(@PathVariable UUID id) {
        // Call the service to delete the person
        service.deletePeople(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Update a person with the given ID
     *
     * @param id The ID of the person to update
     * @param people The updated information of the person
     * @return ResponseEntity with the updated person information
     */
    @PutMapping(value="/{id}")
    public ResponseEntity<PeopleModelResponse> updatePeople(@PathVariable UUID id,
                                                            @Valid @RequestBody People people) {
        // Use ModelMapper to map the People object to PeopleDTO
        ModelMapper mapper = new ModelMapper();
        PeopleDTO dto = mapper.map(people, PeopleDTO.class);

        // Call the service to update the person with the given ID
        dto = service.updatePeople(id, dto);

        // Map the updated PeopleDTO back to PeopleModelResponse and return it with HttpStatus.OK
        return new ResponseEntity<>(mapper.map(dto, PeopleModelResponse.class), HttpStatus.OK);
    }
}
