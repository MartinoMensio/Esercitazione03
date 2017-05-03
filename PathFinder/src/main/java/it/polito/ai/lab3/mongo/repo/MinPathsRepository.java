package it.polito.ai.lab3.mongo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.polito.ai.lab3.mongo.repo.entities.MinPath;

public interface MinPathsRepository extends MongoRepository<MinPath, Long>{

}
