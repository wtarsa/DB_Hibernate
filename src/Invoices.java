import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "invoices")
public class Invoices {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int order_id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoice_date;
    @Temporal(TemporalType.TIMESTAMP)
    private Date due_date;
    private int tax;
    private int shipping;
    private int amount_due;

    public Invoices(){

    }

}
