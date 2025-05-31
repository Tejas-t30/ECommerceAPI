package pojo;

public class User {
    private String email;
    private String username;
    private String password;
    private User_Name name;
    private User_Address address;
    private String phone;

    public User(String email, String username, String password, User_Name name, User_Address address, String phone)
    {
        this.email=email;
        this.username=username;
        this.password=password;
        this.name=name;
        this.address=address;
        this.phone=phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User_Name getName() {
        return name;
    }

    public void setName(User_Name name) {
        this.name = name;
    }

    public User_Address getAddress() {
        return address;
    }

    public void setAddress(User_Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
