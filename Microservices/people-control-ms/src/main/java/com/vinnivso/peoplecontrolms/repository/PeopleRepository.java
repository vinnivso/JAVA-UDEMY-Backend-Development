package com.vinnivso.peoplecontrolms.repository;

import com.vinnivso.peoplecontrolms.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface  PeopleRepository extends JpaRepository<People, UUID> {
}
