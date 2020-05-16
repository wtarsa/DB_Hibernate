import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "invoices")
public class Invoices {
    //atrybuty raczej powinny być prywatne ale nie chciało mi sie pisać getterów do testów
    //później zmienie
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public int order_id;
    @Temporal(TemporalType.TIMESTAMP)
    public Date invoice_date;
    @Temporal(TemporalType.TIMESTAMP)
    public Date due_date;
    public int tax;
    public int shipping;
    public int amount_due;

    public Invoices(){

    }

}
