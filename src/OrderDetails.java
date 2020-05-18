import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int order_id;
    private int product_id;
    private double quantity;
    private double unit_price;
    private double discount;
    private int status_id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_allocated;

    public OrderDetails(){}

    public int getProductID(){
        return this.product_id;
    }

    public int getOrderID(){
        return this.order_id;
    }
}
