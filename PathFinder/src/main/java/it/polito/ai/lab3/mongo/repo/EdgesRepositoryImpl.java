package it.polito.ai.lab3.mongo.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;

import it.polito.ai.lab3.mongo.repo.entities.Edge;

public class EdgesRepositoryImpl implements EdgesRepository {

	//@Autowired 
	//private MongoTemplate mongoTemplate;
	
	public List<Edge> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Edge> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public <S extends Edge> List<S> findAll(Example<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public <S extends Edge> List<S> findAll(Example<S> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public <S extends Edge> S insert(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public <S extends Edge> List<S> insert(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public <S extends Edge> List<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<Edge> findAll(Pageable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void delete(Long arg0) {
		// TODO Auto-generated method stub

	}

	public void delete(Edge arg0) {
		// TODO Auto-generated method stub

	}

	public void delete(Iterable<? extends Edge> arg0) {
		// TODO Auto-generated method stub

	}

	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	public boolean exists(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterable<Edge> findAll(Iterable<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Edge findOne(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public <S extends Edge> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public <S extends Edge> long count(Example<S> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public <S extends Edge> boolean exists(Example<S> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public <S extends Edge> Page<S> findAll(Example<S> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public <S extends Edge> S findOne(Example<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
