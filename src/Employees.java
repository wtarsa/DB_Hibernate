import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.*;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "employees")
public class Employees {

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

    public Employees(){}

    public static Employees getEmployee(Session session){
        String hql = "FROM Employees";
        Query query = session.createQuery(hql);
        List<Employees> employees = query.list();
        Random generator = new Random();
        return employees.get(generator.nextInt(employees.size()));
    }
}
