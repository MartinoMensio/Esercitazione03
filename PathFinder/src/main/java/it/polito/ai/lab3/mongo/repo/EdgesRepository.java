package it.polito.ai.lab3.mongo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import it.polito.ai.lab3.mongo.repo.entities.Edge;

@Repository
public interface EdgesRepository extends MongoRepository<Edge, Long>{

}
