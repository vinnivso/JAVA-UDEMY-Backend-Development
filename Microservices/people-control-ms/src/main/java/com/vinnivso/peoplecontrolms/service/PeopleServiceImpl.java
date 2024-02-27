package com.vinnivso.peoplecontrolms.service;

import com.vinnivso.peoplecontrolms.model.People;
import com.vinnivso.peoplecontrolms.repository.PeopleRepository;
import com.vinnivso.peoplecontrolms.shared.PeopleDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PeopleServiceImpl implements PeopleService {
    @Autowired
    private PeopleRepository repository;

    /**
     * Creates a new PeopleDTO.
     *
     * @param dto The PeopleDTO to be created
     * @return The created PeopleDTO
     */
    @Override
    public PeopleDTO createPeople(PeopleDTO dto) {
        return savePeople(dto);
    }

    /**
     * Retrieve all people from the repository and map them to PeopleDTO objects.
     *
     * @return List of PeopleDTO representing all people
     */
    @Override
    public List<PeopleDTO> getAllPeople() {
        // Retrieve all people from the repository
        List<People> peopleList = repository.findAll();

        // Map People objects to PeopleDTO objects using ModelMapper
        return peopleList.stream()
                .map(people -> new ModelMapper().map(people, PeopleDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieve a person by their ID.
     * @param id the ID of the person to retrieve
     * @return an Optional containing the PeopleDTO if found, or empty if not found
     */
    @Override
    public Optional<PeopleDTO> getPeopleById(UUID id) {
        // Find the person by their ID
        Optional<People> people = repository.findById(id);

        // If the person is found, map it to a DTO and return it
        if(people.isPresent()) {
            PeopleDTO dto = new ModelMapper().map(people.get(), PeopleDTO.class);
            return Optional.of(dto);
        }

        // If the person is not found, return an empty Optional
        return Optional.empty();
    }

    /**
     * Deletes a person by their ID.
     *
     * @param id the ID of the person to delete
     */
    @Override
    public void deletePeople(UUID id) {
        repository.deleteById(id);
    }

    /**
     * Update a person with the given ID.
     *
     * @param id The ID of the person to update
     * @param dto The updated person data
     * @return The updated person data
     */
    @Override
    public PeopleDTO updatePeople(UUID id, PeopleDTO dto) {
        dto.setId(id);
        return savePeople(dto);
    }

    /**
     * Saves a PeopleDTO object to the database
     *
     * @param dto the PeopleDTO object to save
     * @return the saved PeopleDTO object
     */
    private PeopleDTO savePeople(PeopleDTO dto) {
        // Create a ModelMapper instance
        ModelMapper mapper = new ModelMapper();

        // Map PeopleDTO to People entity
        People peopleEntity = mapper.map(dto, People.class);

        // Save the People entity to the database
        peopleEntity = repository.save(peopleEntity);

        // Map the saved People entity back to PeopleDTO and return
        return mapper.map(peopleEntity, PeopleDTO.class);
    }
}
