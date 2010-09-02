package ca.ualberta.cs.courseplanner.server;

import org.dozer.Mapper;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.transaction.annotation.Transactional;

import ca.ualberta.cs.courseplanner.model.*;
import ca.ualberta.cs.courseplanner.services.CourseDataService;


public class CourseDataServiceImpl implements CourseDataService {

	private DataRepository dataRepository;
	
	private SearchEngine searchEngine;
	
	private Mapper mapper;
	
	public void setDataRepository (DataRepository dataRepository) {
		this.dataRepository = dataRepository;
	}
	
	public void setSearchEngine (SearchEngine searchEngine) {
		this.searchEngine = searchEngine;
	}
	
	public void setMapper (Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	@Transactional(readOnly=true)
	public CourseDetails getCourseDetails (Long id) {
		return mapper.map(dataRepository.getCourse(id), CourseDetails.class);
	}

	@Override
	@Transactional(readOnly=true)
	public CourseSearchResults searchCourses (String queryString, int firstResult, int maxResults) {
		return searchEngine.searchCourses(queryString, firstResult, maxResults);
	}

}
