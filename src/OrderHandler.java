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
        Customers customer = customerID();
        Products.listAllProducts(session);
        String product_id = readString("Podaj id produktu");
        String quantity = readString("Podaj ilość jednostek");
        ArrayList<Products> products = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();
        while(!(product_id.equals("")) &&!(quantity.equals(""))){
            products.add(Products.getProduct(session, Integer.parseInt(product_id)));
            quantities.add(Integer.parseInt(quantity));
            product_id = readString("Podaj id produktu");
            quantity = readString("Podaj ilość jednostek");
        }
        System.out.println("Do zamówienia zostaną dodane pozycje:");
        for(int i = 0; i < products.size(); i++){
            System.out.println(products.get(i).getProductName()
                    + " ilość: " + quantities.get(i));
        }
        Transaction tx = session.beginTransaction();
        Orders order = new Orders(customer, new Date(), Employees.getEmployee(session));
        session.save(order);
        for(int i = 0; i < products.size(); i++){
            OrderDetails od = new OrderDetails(products.get(i), order, quantities.get(i));
            session.save(od);
        }
        tx.commit();
    }

    public Customers customerID(){
        String first_name = readString("Podaj imię");
        String last_name = readString("Podaj nazwisko");
        Customers c = Customers.getID(first_name, last_name, session);
        if(c == null){
            System.out.println("Nie znaleziono klienta");
            String address = readString("Podaj ulicę");
            String city = readString("Podaj miasto");
            String zip_postal_code = readString("Podaj kod pocztowy");
            Transaction tx = this.session.beginTransaction();
            Customers customer = new Customers(first_name, last_name, address, city, zip_postal_code);
            session.save(customer);
            tx.commit();
            c = Customers.getID(first_name, last_name, session);
        }
        return c;
    }

    public static String readString(String message){
        System.out.print(message + ": ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
