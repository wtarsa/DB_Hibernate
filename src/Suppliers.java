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
        String hql = "FROM Suppliers";
        Query query = session.createQuery(hql);
        List<Suppliers> suppliers = query.list();
        for (Suppliers s : suppliers) {
            System.out.println(
                    "id: " + s.id + "\t\t" +
                            "company: " + s.company + "\t\t\t" +
                            "name: " + s.first_name + "\t\t\t" +
                            "lastname: " + s.last_name + "\t\t\t" +
                            "address: " + s.address + "\t\t" + s.city + "\t\t" + s.zip_postal_code
            );
        }
        tx.commit();
    }

}
