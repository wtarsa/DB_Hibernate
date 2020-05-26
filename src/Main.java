import com.sun.org.apache.xpath.internal.operations.Or;
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
                            "Generowanie faktury - wybierz 3\n" +
                            "Wybór");
            if (Integer.parseInt(choice) == 1) {
                OrderHandler handler = new OrderHandler(session);
                handler.submitOrder();
            }
            else if (Integer.parseInt(choice) == 2) {
                String report_choice = OrderHandler.readString(
                        "Lista klientów - wybierz 1\n" +
                                "Lista dostawców - wybierz 2\n" +
                                "Lista zamówień z dnia - wybierz 3\n" +
                                "Lista zamówień klienta - wybierz 4\n" +
                                "Wybór");
                if (Integer.parseInt(report_choice) == 1) {
                    Customers.showAllCustomers(session);
                }
                else if(Integer.parseInt(report_choice) == 2){
                    Suppliers.showAllSuppliers(session);
                }
                else if(Integer.parseInt(report_choice) == 3){
                    String s_year = OrderHandler.readString("Podaj rok");
                    String s_month = OrderHandler.readString("Podaj miesiąc");
                    String s_day = OrderHandler.readString("Podaj dzień");
                    int year = Integer.parseInt(s_year);
                    int month = Integer.parseInt(s_month);
                    int day = Integer.parseInt(s_day);
                    Orders.showOrders(year, month, day, session);
                }
                else if (Integer.parseInt(report_choice) == 4){
                    Customers.showAllCustomers(session);
                    String customer_id = OrderHandler.readString("Podaj id klienta");
                    int cid = Integer.parseInt(customer_id);
                    Orders.showOrders(cid, session);
                }
                else{
                    System.out.println("Nieprawidłowy wybór!");
                }
            }
            else if (Integer.parseInt(choice) == 3) {
                InvoiceHandler handler = new InvoiceHandler(session);
                handler.createInvoice();
            }
            else {
                System.out.println("Nieprawidłowy wybór!");
            }

        } finally {
            session.close();
        }
    }
}