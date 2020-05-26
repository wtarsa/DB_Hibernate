import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String company;
    private String first_name;
    private String last_name;
    private String email_address;
    private String job_title;
    private String business_phone;
    private String home_phone;
    private String mobile_phone;
    private String fax_number;
    private String address;
    private String city;
    private String state_province;
    private String zip_postal_code;
    private String country_region;
    private String web_page;
    private String notes;

    public Customers(){}

    public Customers(String first_name, String last_name, String address, String city, String zip_postal_code){
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.city = city;
        this.zip_postal_code = zip_postal_code;
    }


    public String getFirstName() { return this.first_name; }

    public String getLastName() { return this.last_name; }

    public String getAddress() { return this.address; }

    public String getCity() { return this.city; }

    public String getPostalCode() { return this.zip_postal_code; }

    /**
     * Zwraca klienta, którego imię i nazwisko jest podane w parametrach.
     * Jeżeli klient o podanych danych nie istnieje zwraca null.
     * @param first_name
     * @param last_name
     * @param session
     * @return
     */
    public static Customers getID(String first_name, String last_name, Session session){
        Customers customer = null;
        Transaction tx = session.beginTransaction();
        String hql = "FROM Customers";
        Query query = session.createQuery(hql);
        List<Customers> customers = query.list();
        for(Customers c: customers){
            if(first_name.equals(c.getFirstName()) && last_name.equals(c.getLastName()))
                customer = c;
        }
        tx.commit();
        return customer;
    }

    /**
     * Wyświetla wszystkich klientów istniejących w tabeli customers.
     * @param session
     */
    public static void showAllCustomers(Session session) {
        Transaction tx = session.beginTransaction();
        List<Object[]> customers = session.createQuery("SELECT id, first_name, last_name, address, city, zip_postal_code FROM Customers").getResultList();

        for (Object[] c : customers) {
            System.out.println(
                    "id: " + c[0] + "\t\t" +
                    "name: " + c[1] + "\t\t\t" +
                            "lastname: " + c[2] + "\t\t\t" +
                            "address: " + c[3] + "\t\t" + c[4] + "\t\t" + c[5]
            );
        }
        tx.commit();
    }
}
