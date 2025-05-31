package pojo;

public class User_Geolocation {
    private String lat;
    private String lan;

    public User_Geolocation(String lat, String lan){
        this.lat=lat;
        this.lan=lan;
    }


    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }



}
