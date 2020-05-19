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
//    private String business_phone;
//    private String home_phone;
//    private String mobile_phone;
    private String fax_number;
    private String address;
    private String city;
//    private String state_province;
    private String zip_postal_code;
//    private String country_region;
//    private String web_page;
//    private String notes;

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

    public static int getID(String first_name, String last_name, Session session){
        int id = -1;
        Transaction tx = session.beginTransaction();
        String hql = "FROM Customers";
        Query query = session.createQuery(hql);
        List<Customers> customers = query.list();
        for(Customers customer: customers){
            if(first_name.equals(customer.getFirstName()) && last_name.equals(customer.getLastName()))
                id = customer.id;
        }
        tx.commit();
        return id;
    }
}