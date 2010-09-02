package ca.ualberta.cs.courseplanner.tools;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import ca.ualberta.cs.courseplanner.entities.Course;

public class BuildIndex {

	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext("WEB-INF/applicationContext.xml");
		SessionFactory factory = ctx.getBean(SessionFactory.class);
		Session session = factory.openSession();
		try {
			FullTextSession fullTextSession = Search.getFullTextSession(session);
			System.out.print("Rebuilding index... ");
			fullTextSession.createIndexer(Course.class).startAndWait();
			System.out.println("done.");
		} catch (InterruptedException e) {
			System.out.println("interrupted.");
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		System.out.println(session);
	}

}
