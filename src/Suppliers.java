import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "suppliers")
public class Suppliers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String company;
    private String last_name;
    private String first_name;
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

    public Suppliers(){}

    /**
     * Wyświetla dostawców istniejących w tabeli suppliers.
     * @param session
     */
    public static void showAllSuppliers(Session session) {
        Transaction tx = session.beginTransaction();
        List<Object[]> suppliers = session.createQuery("SELECT id, company, first_name, last_name, address, city, zip_postal_code FROM Suppliers").getResultList();

        for (Object[] s : suppliers) {
            System.out.println(
                    "id: " + s[0] + "\t\t" +
                            "company: " + s[1] + "\t\t\t" +
                            "name: " + s[2] + "\t\t\t" +
                            "lastname: " + s[3] + "\t\t\t" +
                            "address: " + s[4] + "\t\t" + s[5] + "\t\t" + s[6]
            );
        }
        tx.commit();
    }

}
