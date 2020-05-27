import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class InvoiceHandler {

    private Session session;

    public InvoiceHandler(Session session) {
        this.session = session;
    }

    /**
     * Wyświetla produkty zawarte na jednym zamówieniu. (Faktura)
     */
    public void createInvoice(){
        Customers.showAllCustomers(session);
        int client_id = Integer.parseInt(OrderHandler.readString("Podaj ID klienta"));

        Orders.showOrders(client_id, session);
        int order_id = Integer.parseInt(OrderHandler.readString("Podaj ID zamówienia"));

        Transaction tx = session.beginTransaction();
        List<Object[]> invoices = session.createQuery("SELECT o.order_id, p.product_name, p.category, o.quantity, o.unit_price FROM OrderDetails o JOIN Products p ON o.product_id = p.id").getResultList();

        int no_results = 0;
        for(Object[] i: invoices){
            if((Integer)i[0] == order_id){
                System.out.println("product_name: " + i[1] + "\t\t" +
                        "category: " + i[2] + "\t\t" +
                        "quantity: " + i[3] + "\t\t" +
                        "unit_price: " + i[4]);
            }
        }
        tx.commit();
    }
}
