package pojo;

public class User_Address {
    private String city;
    private String street;
    private int number;
    private String zipcode;
    private User_Geolocation geolocation;

    public User_Address(String city, String street, int number, String zipcode, User_Geolocation geolocation)
    {
        this.city=city;
        this.street=street;
        this.number=number;
        this.zipcode=zipcode;
        this.geolocation=geolocation;
    }

    public User_Geolocation getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(User_Geolocation geolocation) {
        this.geolocation = geolocation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }


}
