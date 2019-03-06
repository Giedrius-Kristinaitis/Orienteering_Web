package com.clickbait.orient.database;

import com.clickbait.orient.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository to CRUD events
 */
public interface EventRepository extends MongoRepository<Event, String> { }
