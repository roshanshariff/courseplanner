package ca.ualberta.cs.courseplanner.server;

import java.util.List;

import org.apache.lucene.queryParser.core.QueryNodeException;
import org.apache.lucene.queryParser.standard.StandardQueryParser;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.transform.ResultTransformer;
import org.springframework.transaction.annotation.Transactional;

import ca.ualberta.cs.courseplanner.entities.Course;
import ca.ualberta.cs.courseplanner.model.CourseInfo;
import ca.ualberta.cs.courseplanner.model.CourseSearchResults;
import ca.ualberta.cs.courseplanner.server.search.CourseInfoResultTransformer;


public class SearchEngineImpl implements SearchEngine {
	
	private SessionFactory sessionFactory;
	
	private final ResultTransformer courseInfoResultTransformer = new CourseInfoResultTransformer();
	
	public void setSessionFactory (SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private FullTextSession getSession () {
		return Search.getFullTextSession(sessionFactory.getCurrentSession());
	}

	@Override
	@Transactional(readOnly=true)
	public CourseSearchResults searchCourses (String queryString, int firstResult, int maxResults) {
		try {
			
			FullTextSession session = getSession();

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
