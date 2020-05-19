import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String supplier_ids;
    private String product_code;
    private String product_name;
    private String description;
    private double standard_cost;
    private double list_price;
    private int reorder_level;
    private String quantity_per_unit;
    private int discontinued;
    private String category;

    public Products(){}

    public String getProductName(){
        return this.product_name;
    }

    public double getStandardCost() { return this.standard_cost; }

    public int getProductID() { return this.id; }

    public String getQuantityPerUnit() { return this.quantity_per_unit; }

    /**
     * Wypisuje id, nazwę, cenę oraz informację na temat ilości jednostek w opakowaniu dla każdego produktu
     * znajdującego się w tabeli Products.
     * @param session
     */
    public static void listAllProducts(Session session){
        Transaction tx = session.beginTransaction();
        String hql = "FROM Products";
        Query query = session.createQuery(hql);
        List<Products> allProducts = query.list();
        for(Products product: allProducts){
            System.out.println("id: " + product.getProductID() +
                    " nazwa: " + product.getProductName() +
                    " cena: " + product.getStandardCost() +
                    " ilość w opakowaniu: " + product.getQuantityPerUnit());
        }
        tx.commit();
    }

    /**
     * Zwraca produkt o podanym id. Jeżeli tabela nie zawiera produktu o podanym id zwraca null.
     * @param session
     * @param id
     * @return
     */
    public static Products getProduct(Session session, int id){
        Products product = null;
        Transaction tx = session.beginTransaction();
        String hql = "FROM Products";
        Query query = session.createQuery(hql);
        List<Products> allProducts = query.list();
        for(Products p: allProducts){
            if(p.id == id) product = p;
        }
        tx.commit();
        return product;
    }

}
