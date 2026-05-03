package Utils;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


public class HibernateUtils {
	private static SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
