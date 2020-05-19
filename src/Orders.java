import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int employee_id;
    private int customer_id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date order_date;
    @Temporal(TemporalType.TIMESTAMP)
    private Date shipped_date;
    private String ship_name;
    private String ship_address;
    private String ship_city;
//    private String ship_state_province;
    private String ship_zip_postal_code;
    private String ship_country_region;
    private double shipping_fee;
//    private double taxes;
//    private String payment_type;
    @Temporal(TemporalType.TIMESTAMP)
    private Date paid_date;
//    private String notes;
//    private double tax_rate;
//    private int status_id;

    public Orders(){}

    public Orders(int customer_id, Date order_date, int employee_id){
        this.customer_id = customer_id;
        this.order_date = order_date;
        this.employee_id = employee_id;
    }

    public int getOrderID(){
        return this.id;
    }

}
