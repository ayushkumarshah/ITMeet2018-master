package np.edu.ku.itmeet18.bus_route;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import np.edu.ku.itmeet18.R;

/**
 * Created by deepesh on 1/3/18.
 */

public class bus_route_Adapter extends ArrayAdapter<bus_info> {

    private Context mContext;
    private List<bus_info> busList = new ArrayList<>();

    public bus_route_Adapter(Context context,ArrayList<bus_info> list) {
        super(context, 0 , list);
        mContext = context;
        busList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_bus_info,parent,false);

        bus_info bus = busList.get(position);

        TextView date = (TextView) listItem.findViewById(R.id.bus_date);
        TextView origin = (TextView) listItem.findViewById(R.id.bus_origin);
        TextView destination = (TextView) listItem.findViewById(R.id.bus_destination);
        TextView time = (TextView) listItem.findViewById(R.id.bus_time);

        date.setText(bus.getDate());
        origin.setText(bus.getLocation_origin());
        destination.setText(bus.getLocation_destination());
        time.setText(bus.getTime());

        return listItem;
    }
}
