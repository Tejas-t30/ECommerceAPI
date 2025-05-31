package payload;

import java.text.SimpleDateFormat;
import java.util.*;

import com.github.javafaker.Faker;

import pojo.*;

public class Payloads {
    private static final Faker faker=new Faker();
    private static final String categories[]= {"electronics", "furniture", "clothing", "books", "beauty"};

    private static final Random random=new Random();
    public static Products productPayload()
    {
        String name=faker.commerce().productName();
        double price=Double.parseDouble(faker.commerce().price());
        String description=faker.lorem().sentence();
        String imageUrl="https://i.pravatar.cc/100";
        String category=categories[random.nextInt(categories.length)];
        return new Products(name, price, description, imageUrl, category);

    }
    public static User userPayload()
    {
        String email=faker.internet().emailAddress();
        String username=faker.name().username();
        String password=faker.internet().password();

        //name
        String firstname=faker.name().firstName();
        String lastname=faker.name().lastName();
        User_Name name=new User_Name(firstname,lastname);

        //geolocation
        String lat=faker.address().latitude();
        String lan=faker.address().longitude();
        User_Geolocation geolocation=new User_Geolocation(lat,lan);

        //address
        String city=faker.address().city();
        String street=faker.address().streetName();
        int number=random.nextInt(100);
        String zipcode=faker.address().zipCode();
        User_Address address=new User_Address(city,street,number,zipcode,geolocation);
        String phone=faker.phoneNumber().cellPhone();

        User user=new User(email,username,password,name,address,phone);
        return user;
    }

    public static Cart cartPayload(int userid)
    {
        //user ID should be given while calling object

        //Date
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String date=formatter.format(new Date());

        //Products Json Array
        int productid=random.nextInt(100);
        int quantity=random.nextInt(10)+1;

        List<CartProducts> products=new ArrayList<>();
        CartProducts cartProduct=new CartProducts(productid, quantity);
        products.add(cartProduct);

        Cart cart=new Cart(userid, date, products);
        return cart;
    }

}
