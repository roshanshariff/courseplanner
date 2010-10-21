package ca.ualberta.cs.courseplanner.server.impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.ualberta.cs.courseplanner.model.*;
import ca.ualberta.cs.courseplanner.server.services.DataRepository;
import ca.ualberta.cs.courseplanner.server.services.SearchEngine;
import ca.ualberta.cs.courseplanner.services.CourseDataService;

@Service
@Singleton
@Named("courseDataService")
public class CourseDataServiceImpl implements CourseDataService {

	private final DataRepository dataRepository;
	
	private final SearchEngine searchEngine;
	
	private final Mapper mapper;
	

	@Inject
	public CourseDataServiceImpl (DataRepository dataRepository, SearchEngine searchEngine, Mapper mapper) {
		this.dataRepository = dataRepository;
		this.searchEngine = searchEngine;
		this.mapper = mapper;
	}

	
	@Override
	@Transactional(readOnly=true)
	public CourseDetails getCourseDetails (Long id) {
		return mapper.map(dataRepository.getCourse(id), CourseDetails.class);
	}

	@Override
	public SearchResults searchCourses (String queryString, SearchOrdering ordering, int firstResult, int maxResults) {
		if (ordering == null) ordering = SearchOrdering.DEFAULT;
		return searchEngine.searchCourses(queryString, ordering, firstResult, maxResults);
	}

}
