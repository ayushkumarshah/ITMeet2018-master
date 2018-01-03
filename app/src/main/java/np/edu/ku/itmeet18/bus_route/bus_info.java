package np.edu.ku.itmeet18.bus_route;

/**
 * Created by deepesh on 1/3/18.
 */

public class bus_info {

    String date,location_origin,location_destination,time;

    public bus_info(String date, String location_origin, String location_destination, String time) {
        this.date = date;
        this.location_origin = location_origin;
        this.location_destination = location_destination;
        this.time = time;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation_origin() {
        return location_origin;
    }

    public void setLocation_origin(String location_origin) {
        this.location_origin = location_origin;
    }

    public String getLocation_destination() {
        return location_destination;
    }

    public void setLocation_destination(String location_destination) {
        this.location_destination = location_destination;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
