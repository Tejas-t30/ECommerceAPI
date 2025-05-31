package pojo;

public class CartProducts {
    private int productid;
    private int quantity;

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CartProducts(int productid, int quantity)
    {
        this.productid=productid;
        this.quantity=quantity;
    }
}
