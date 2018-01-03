package np.edu.ku.itmeet18.bus_route;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import np.edu.ku.itmeet18.HomeFragment;
import np.edu.ku.itmeet18.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class bus_route extends Fragment {
    private ListView listView;
    private bus_route_Adapter bus;
    private Context c;

    public bus_route() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_bus_route, container, false);


        listView = (ListView) rootView.findViewById(R.id.list_bus);
        ArrayList<bus_info> list = new ArrayList<>();

        list.add(new bus_info("3rd Jan 2018" , "Old Bus Park","IT Park","9:00 AM"));
        list.add(new bus_info("4th Jan 2018" , "Old Bus Park","IT Park","8:00 AM"));
        list.add(new bus_info("4th Jan 2018" , "IT Park","Old Bus Park","5:00 PM"));
        list.add(new bus_info("5th Jan 2018" , "Old Bus Park","IT Park","7:30 AM"));
        list.add(new bus_info("5th Jan 2018" , "Old Bus Park","IT Park","8:00 AM"));
        list.add(new bus_info("5th Jan 2018" , "IT Park","Old Bus Park","4:30 PM"));
        list.add(new bus_info("5th Jan 2018" , "IT Park","Old Bus Park","6:00 PM"));
        list.add(new bus_info("6th Jan 2018" , "Old Bus Park","IT Park","7:45 AM"));
        list.add(new bus_info("6th Jan 2018" , "Old Bus Park","IT Park","8:00AM"));
        list.add(new bus_info("6th Jan 2018" , "IT Park","Old Bus Park","6:30 PM"));

        bus = new bus_route_Adapter(getActivity(),list);
        listView.setAdapter(bus);


        return rootView;
    }

    @Override
    public void onResume() {

        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){

                    // handle back button
                    //getActivity().getSupportFragmentManager().popBackStack();
                    Fragment fragment = new HomeFragment();
                    FragmentTransaction ft  = getActivity().getSupportFragmentManager().beginTransaction();

                    ft.replace(R.id.frag_bus_info, fragment);
                    ft.addToBackStack("tag");
                    ft.commit();
                    return true;

                }

                return false;
            }
        });
    }
}
