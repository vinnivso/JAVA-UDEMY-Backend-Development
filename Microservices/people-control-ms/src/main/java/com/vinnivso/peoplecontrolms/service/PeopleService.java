package com.vinnivso.peoplecontrolms.service;

import com.vinnivso.peoplecontrolms.shared.PeopleDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PeopleService {
    PeopleDTO createPeople(PeopleDTO dto);
    List<PeopleDTO> getAllPeople();
    Optional<PeopleDTO> getPeopleById(UUID id);
    void deletePeople(UUID id);
    PeopleDTO updatePeople(UUID id, PeopleDTO dto);
}
