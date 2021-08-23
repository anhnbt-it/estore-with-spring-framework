package vn.aptech.estore.services;

import vn.aptech.estore.entities.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 8/20/2021
 * Time: 8:10 PM
 */
public interface ShoppingCartService {
    void addToCart(Product product);

    void updateCart(Long id, Product product);

    void removeProduct(Product product);

    void removeProductById(Long id);

    void removeAll();

    Map<Long, Product> getItems();

    Set<Product> getProducts();

    double getTotal();

    int getNumberOfItems();
}
