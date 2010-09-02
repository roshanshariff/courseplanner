package ca.ualberta.cs.courseplanner.server.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import org.apache.lucene.queryParser.core.QueryNodeException;
import org.apache.lucene.queryParser.standard.StandardQueryParser;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.ualberta.cs.courseplanner.entities.Course;
import ca.ualberta.cs.courseplanner.model.CourseInfo;
import ca.ualberta.cs.courseplanner.model.CourseSearchResults;
import ca.ualberta.cs.courseplanner.server.search.CourseInfoResultTransformer;
import ca.ualberta.cs.courseplanner.server.services.SearchEngine;


@Repository
@Singleton
public class SearchEngineImpl implements SearchEngine {
	
	private final Provider<FullTextSession> fullTextSession;
	
	private final ResultTransformer courseInfoResultTransformer = new CourseInfoResultTransformer();
	

	@Inject
	public SearchEngineImpl (SessionFactory sessionFactory) {
		this.fullTextSession = new CurrentFullTextSessionProvider(new CurrentSessionProvider(sessionFactory));
	}


	@Override
	@Transactional(readOnly=true)
	public CourseSearchResults searchCourses (String queryString, int firstResult, int maxResults) {
		try {
			
			FullTextSession session = fullTextSession.get();

			StandardQueryParser queryParser = new StandardQueryParser();
			queryParser.setAnalyzer(session.getSearchFactory().getAnalyzer(Course.class));

			FullTextQuery query =
				session.createFullTextQuery(queryParser.parse(queryString, "all"), Course.class)
				.setProjection("id", "subject.id", "number", "name")
				.setResultTransformer(courseInfoResultTransformer)
				.setFirstResult(firstResult)
				.setMaxResults(maxResults);
			
			@SuppressWarnings("unchecked")
			List<CourseInfo> courses = query.list();

			CourseSearchResults results = new CourseSearchResults();
			results.setQueryString(queryString);
			results.setFirstResult(firstResult);
			results.setNumResults(query.getResultSize());
			results.setNumResultsExact(false);
			results.setResults(courses.toArray(new CourseInfo[courses.size()]));
			
			if (results.getResults().length < maxResults) {
				results.setNumResults(results.getFirstResult()+results.getResults().length);
				results.setNumResultsExact(true);
			}
			
			return results;
		}
		catch (QueryNodeException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
}
