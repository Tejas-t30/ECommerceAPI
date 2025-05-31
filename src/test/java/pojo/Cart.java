package pojo;

import java.util.List;

public class Cart {
    private int userid;
    private String date;
    private List<CartProducts> products;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<CartProducts> getProducts() {
        return products;
    }

    public void setProducts(List<CartProducts> products) {
        this.products = products;
    }

    public Cart(int userid, String date, List<CartProducts> products)
    {
        this.userid=userid;
        this.date=date;
        this.products=products;
    }

}
