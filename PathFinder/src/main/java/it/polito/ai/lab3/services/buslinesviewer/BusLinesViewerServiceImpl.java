package it.polito.ai.lab3.services.buslinesviewer;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.polito.ai.lab3.repo.BusLinesRepository;
import it.polito.ai.lab3.repo.entities.BusLine;

@Service
@Transactional
public class BusLinesViewerServiceImpl implements BusLinesViewerService {
	@Autowired
	private BusLinesRepository busLinesRepository;
	
	
	public List<BusLine> getAllLines() {
		List<BusLine> list = new ArrayList<BusLine>();
		
		// Get all the lines sorted by line name
		Sort sortByLine = new Sort("line");
		Iterable<BusLine> res = busLinesRepository.findAll(sortByLine);
		
		// Put all the found lines into the list
		for (BusLine bl : res) {
			list.add(bl);
		}
		
		return list;
	}


	public BusLine getLineById(String lineId) {
		// Get all the requested line
		return busLinesRepository.findOne(lineId);
	}
}
