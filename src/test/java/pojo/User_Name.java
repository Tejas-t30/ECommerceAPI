package pojo;

public class User_Name {


    private String firstname;

    private String lastname;
    public User_Name(String firstname, String lastname) {
        this.firstname=firstname;
        this.lastname=lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

}
