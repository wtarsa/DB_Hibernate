import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int order_id;
    private int product_id;
    private int quantity;
    private double unit_price;
    private double discount;
    private int status_id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_allocated;

    public OrderDetails(){}

    public OrderDetails(int product_id, int order_id, int quantity){
        this.product_id = product_id;
        this.order_id = order_id;
        this.quantity = quantity;
    }

    public int getProductID(){
        return this.product_id;
    }

    public int getOrderID(){
        return this.order_id;
    }
}
