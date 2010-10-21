package ca.ualberta.cs.courseplanner.server.impl;

import java.util.EnumMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

import org.apache.lucene.queryParser.core.QueryNodeException;
import org.apache.lucene.queryParser.standard.StandardQueryParser;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.ualberta.cs.courseplanner.entities.Course;
import ca.ualberta.cs.courseplanner.model.CourseInfo;
import ca.ualberta.cs.courseplanner.model.SearchResults;
import ca.ualberta.cs.courseplanner.model.SearchOrdering;
import ca.ualberta.cs.courseplanner.server.search.CourseInfoResultTransformer;
import ca.ualberta.cs.courseplanner.server.services.SearchEngine;


@Repository
@Singleton
public class SearchEngineImpl implements SearchEngine {
	
	private static final EnumMap<SearchOrdering, Sort> SORT;
	
	static {
		SortField score = SortField.FIELD_SCORE;		
		SortField id = new SortField("id", SortField.LONG);
		SortField subject = new SortField("subject.id", SortField.STRING);
		SortField number = new SortField("number", SortField.STRING);
		SortField numberRev = new SortField("number", SortField.STRING, true);
		SortField level = new SortField("level", SortField.INT);
		SortField levelRev = new SortField("level", SortField.INT, true);
		
		SORT = new EnumMap<SearchOrdering, Sort>(SearchOrdering.class);
		SORT.put(SearchOrdering.DEFAULT,  new Sort(new SortField[] {score, subject, number, id}));
		SORT.put(SearchOrdering.SUBJECT,  new Sort(new SortField[] {subject, number, id}));
		SORT.put(SearchOrdering.LEVEL,    new Sort(new SortField[] {level, subject, number, id}));
		SORT.put(SearchOrdering.LEVELREV, new Sort(new SortField[] {levelRev, subject, numberRev, id}));
	}
	
	
	private final Provider<FullTextSession> fullTextSession;
	
	private final ResultTransformer courseInfoResultTransformer = new CourseInfoResultTransformer();
	

	@Inject
	public SearchEngineImpl (@Named("hibernateFullTextSession") Provider<FullTextSession> fullTextSession) {
		this.fullTextSession = fullTextSession;
	}


	@Override
	@Transactional(readOnly=true)
	public SearchResults searchCourses (String queryString, SearchOrdering ordering, int firstResult, int maxResults) {
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
			
			if (ordering != null) query.setSort(SORT.get(ordering));
			
			@SuppressWarnings("unchecked")
			List<CourseInfo> courses = query.list();

			SearchResults results = new SearchResults();
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
