import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(insertable = false, updatable = false)
    private int order_id;
    @Column(insertable = false, updatable = false)
    private int product_id;
    private int quantity;
    private double unit_price;
    private double discount;
    private int status_id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_allocated;

    @ManyToOne
    private Orders order;

    @ManyToOne
    private Products product;

    public OrderDetails(){}


    public OrderDetails(Products product, Orders order, int quantity){
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.unit_price = product.getStandardCost();
    }

    public int getProductID(){
        return this.product_id;
    }

    public int getOrderID(){
        return this.order_id;
    }
}
