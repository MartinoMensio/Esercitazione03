package it.polito.ai.lab3.mongo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import it.polito.ai.lab3.mongo.repo.entities.MinPath;

@Repository
public interface MinPathsRepository extends MongoRepository<MinPath, Long>{

}
