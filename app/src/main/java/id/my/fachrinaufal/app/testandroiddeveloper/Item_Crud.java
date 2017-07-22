package id.my.fachrinaufal.app.testandroiddeveloper;

/**
 * Created by fachrinfl on 21/07/2017.
 */

public class Item_Crud {

    private int id;
    private String name;
    private String phone;
    private String email;

    public Item_Crud(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    public Item_Crud(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
