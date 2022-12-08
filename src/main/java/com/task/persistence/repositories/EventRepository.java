package com.task.persistence.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.task.persistence.entities.Event;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {

    List<Event> findByTitle(String title);

}
