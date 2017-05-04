package it.polito.ai.lab3.mongo.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import it.polito.ai.lab3.mongo.repo.entities.Key;
import it.polito.ai.lab3.mongo.repo.entities.MinPath;

@Repository
public class MinPathsRepository implements MongoRepository<MinPath, Long>{

	@Autowired MongoTemplate mongoTemplate;
	
	public List<MinPath> myCustomFind(String idSource, String idDestination) {

		Key key = new Key();
		key.setSrc(idSource);
		key.setDst(idDestination);
		Query q = new Query(Criteria.where("_id").is(key));
		List<MinPath> l = mongoTemplate.find(q, MinPath.class);
		return l;
	} 
	
	public Page<MinPath> findAll(Pageable arg0) {
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

	public void delete(MinPath arg0) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Iterable<? extends MinPath> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	public boolean exists(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterable<MinPath> findAll(Iterable<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public MinPath findOne(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public <S extends MinPath> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public <S extends MinPath> long count(Example<S> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public <S extends MinPath> boolean exists(Example<S> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public <S extends MinPath> Page<S> findAll(Example<S> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public <S extends MinPath> S findOne(Example<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MinPath> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MinPath> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public <S extends MinPath> List<S> findAll(Example<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public <S extends MinPath> List<S> findAll(Example<S> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public <S extends MinPath> S insert(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public <S extends MinPath> List<S> insert(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public <S extends MinPath> List<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
