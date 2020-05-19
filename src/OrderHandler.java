import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class OrderHandler {

    private Session session;

    public OrderHandler(Session session) {
        this.session = session;
    }

    public void submitOrder(){
        int customer_id = customerID();
        Products.listAllProducts(session);
        String product_id = readString("Podaj id produktu");
        String quantity = readString("Podaj ilość jednostek");
        ArrayList<Integer> product_ids = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();
        while(!(product_id.equals("")) &&!(quantity.equals(""))){
            product_ids.add(Integer.parseInt(product_id));
            quantities.add(Integer.parseInt(quantity));
            product_id = readString("Podaj id produktu");
            quantity = readString("Podaj ilość jednostek");
        }
        System.out.println("Do zamówienia zostaną dodane pozycje:");
        for(int i = 0; i < product_ids.size(); i++){
            System.out.println(Products.getProductName(session, product_ids.get(i))
                    + " ilość: " + quantities.get(i));
        }
        Transaction tx = session.beginTransaction();
        Orders order = new Orders(customer_id, new Date(), Employees.getEmployee(session));
        session.save(order);
        for(int i = 0; i < product_ids.size(); i++){
            OrderDetails od = new OrderDetails(product_ids.get(i), order.getOrderID(), quantities.get(i));
            session.save(od);
        }
        tx.commit();
    }

    public int customerID(){
        String first_name = readString("Podaj imię");
        String last_name = readString("Podaj nazwisko");
        int customerID = Customers.getID(first_name, last_name, session);
        if(customerID < 0){
            System.out.println("Nie znaleziono klienta");
            String address = readString("Podaj ulicę");
            String city = readString("Podaj miasto");
            String zip_postal_code = readString("Podaj kod pocztowy");
            Transaction tx = this.session.beginTransaction();
            Customers customer = new Customers(first_name, last_name, address, city, zip_postal_code);
            session.save(customer);
            tx.commit();
            customerID = Customers.getID(first_name, last_name, session);
        }
        return customerID;
    }

    public static String readString(String message){
        System.out.print(message + ": ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
