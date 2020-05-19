import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.metamodel.EntityType;

import java.util.List;
import java.util.Map;

public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        try {
            //Products.listAllProducts(session);

            OrderHandler handler = new OrderHandler(session);
           // handler.customerID();
          //  handler.addNewProductToOrder();
            handler.submitOrder();
            //            String n = OrderHandler.readString("Podaj nazwisko");
//            String i = OrderHandler.readString("Podaj imie");
//            System.out.println(Customers.getID(i, n, session));
//
//
//            String hql = "FROM Invoices";
//            Query query = session.createQuery(hql);
//            List<Invoices> allInvoices = query.list();
//            for(Invoices invoice: allInvoices){
//                System.out.println("id: " + invoice.id + " order_id: " +
//                        invoice.order_id + " invoice_date: " + invoice.invoice_date);
//            }
//
//            String hql_products = "FROM Products";
//            Query query_products = session.createQuery(hql_products);
//            List<Products> allProducts = query_products.list();
//            for(Products product: allProducts){
//                System.out.println(product.getProductName());
//            }
//
//            String hql_order_details = "FROM OrderDetails";
//            Query query_order_details = session.createQuery(hql_order_details);
//            List<OrderDetails> allOrderDetails = query_order_details.list();
//            for(OrderDetails orderDetail: allOrderDetails){
//                System.out.println("product: " + orderDetail.getProductID() +
//                        " order: " + orderDetail.getOrderID());
//            }
//
//            String hql_orders = "FROM Orders";
//            Query query_orders = session.createQuery(hql_orders);
//            List<Orders> allOrders = query_orders.list();
//            for(Orders order: allOrders){
//                System.out.println(order.getOrderID());
//            }

//            System.out.println("querying all the managed entities...");
            /*final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }*/
        } finally {
            session.close();
        }
    }
}