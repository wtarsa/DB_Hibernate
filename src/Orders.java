import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(insertable = false, updatable = false)
    private int employee_id;
    @Column(insertable = false, updatable = false)
    private int customer_id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date order_date;
    @Temporal(TemporalType.TIMESTAMP)
    private Date shipped_date;
    private String ship_name;
    private String ship_address;
    private String ship_city;
    private String ship_state_province;
    private String ship_zip_postal_code;
    private String ship_country_region;
    private double shipping_fee;
    private double taxes;
    private String payment_type;
    @Temporal(TemporalType.TIMESTAMP)
    private Date paid_date;
    private String notes;
    private double tax_rate;
    private int status_id;

    @ManyToOne
    private Employees employee;

    @ManyToOne
    private Customers customer;

    public Orders(){}

    public Orders(Customers customer, Date order_date, Employees employee){
        this.order_date = order_date;
        this.employee = employee;
        this.customer = customer;
        this.ship_address = customer.getAddress();
        this.ship_city = customer.getCity();
        this.ship_zip_postal_code = customer.getPostalCode();
    }

    public int getOrderID(){
        return this.id;
    }

    /**
     * Wyświetla zamówienia, które zostały złożone w podanym dniu.
     * @param year
     * @param month
     * @param day
     * @param session
     */
    public static void showOrders(int year, int month, int day, Session session){
        Transaction tx = session.beginTransaction();
        String hql = "FROM Orders";
        Query query = session.createQuery(hql);
        List<Orders> orders = query.list();
        for(Orders o: orders){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(o.order_date);
            if(year == calendar.get(Calendar.YEAR)
                    && (month-1) == calendar.get(Calendar.MONTH)
                    && day == calendar.get(Calendar.DAY_OF_MONTH))
                System.out.println("id: " + o.id + "\t\t" +
                        "customer: " + o.customer_id + "\t\t" +
                        "order_date: " + o.order_date + "\t\t" +
                        "ship_name: " + o.ship_name);

        }
        tx.commit();
    }

    /**
     * Wyświetla wszystkie zamówienia osoby o podanym id.
     * @param customer_id
     * @param session
     */
    public static void showOrders(int customer_id, Session session){
        Transaction tx = session.beginTransaction();
        String hql = "FROM Orders";
        Query query = session.createQuery(hql);
        List<Orders> orders = query.list();
        for(Orders o: orders){
            if(o.customer_id == customer_id){
                System.out.println("id: " + o.id + "\t\t" +
                        "customer: " + o.customer_id + "\t\t" +
                        "order_date: " + o.order_date + "\t\t" +
                        "ship_name: " + o.ship_name);
            }
        }
        tx.commit();
    }

}
