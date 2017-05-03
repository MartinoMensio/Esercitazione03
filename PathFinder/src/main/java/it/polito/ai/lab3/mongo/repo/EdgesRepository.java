package it.polito.ai.lab3.mongo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.polito.ai.lab3.mongo.repo.entities.Edge;

public interface EdgesRepository extends MongoRepository<Edge, Long>{

}
