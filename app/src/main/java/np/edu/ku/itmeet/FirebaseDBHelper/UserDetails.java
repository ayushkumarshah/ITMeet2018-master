package np.edu.ku.itmeet.FirebaseDBHelper;

/**
 * Created by deepesh on 12/22/17.
 */

public class UserDetails {

    String id;
    String name;
    String address;
    String phone;
    String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public UserDetails(String name) {
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public UserDetails(String id,String name, String address, String phone,String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
