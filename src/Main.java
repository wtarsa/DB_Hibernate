import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.metamodel.EntityType;

import java.util.List;
import java.util.Map;

public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        try {
            String choice = OrderHandler.readString(
                            "Dodawanie zamówienia - wybierz 1\n" +
                            "Wyświetlanie raportów - wybierz 2\n" +
                            "Wybór");
            if(Integer.parseInt(choice) == 1) {
                OrderHandler handler = new OrderHandler(session);
                handler.submitOrder();
            }
            else if(Integer.parseInt(choice) == 2){
                /*
                 tutaj handler do wyswietlania raportów
                 */
            }
            else{
                System.out.println("Nieprawidłowy wybór!");
            }

        } finally {
            session.close();
        }
    }
}