package com.sample.cricket.repository;

import com.sample.cricket.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CricketRepository extends MongoRepository<Team, String> {
}
