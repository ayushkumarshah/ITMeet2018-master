package np.edu.ku.itmeet18.bus_route;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

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
String permission=android.Manifest.permission.CALL_PHONE;
    Integer requestCode=1;

    public bus_route() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_bus_route, container, false);

        ImageButton imageButton = (ImageButton) rootView.findViewById(R.id.callbutton);
c=rootView.getContext();
        listView = (ListView) rootView.findViewById(R.id.list_bus);
        ArrayList<bus_info> list = new ArrayList<>();

        list.add(new bus_info("3rd Jan 2018", "Old Bus Park", "IT Park", "9:00 AM"));
        list.add(new bus_info("4th Jan 2018", "Old Bus Park", "IT Park", "8:00 AM"));
        list.add(new bus_info("4th Jan 2018", "IT Park", "Old Bus Park", "5:00 PM"));
        list.add(new bus_info("5th Jan 2018", "Old Bus Park", "IT Park", "7:30 AM"));
        list.add(new bus_info("5th Jan 2018", "Old Bus Park", "IT Park", "8:00 AM"));
        list.add(new bus_info("5th Jan 2018", "IT Park", "Old Bus Park", "4:30 PM"));
        list.add(new bus_info("5th Jan 2018", "IT Park", "Old Bus Park", "6:00 PM"));
        list.add(new bus_info("6th Jan 2018", "Old Bus Park", "IT Park", "7:45 AM"));
        list.add(new bus_info("6th Jan 2018", "Old Bus Park", "IT Park", "8:00AM"));
        list.add(new bus_info("6th Jan 2018", "IT Park", "Old Bus Park", "6:30 PM"));

        bus = new bus_route_Adapter(getActivity(), list);
        listView.setAdapter(bus);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + "9851112849"));

                if (ActivityCompat.checkSelfPermission(c, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.

                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(c, permission) != PackageManager.PERMISSION_GRANTED) {
                        Log.e("apkflow", "askforpermission activated..2");

                        // Should we show an explanation?
                        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission)) {
                            Log.e("apkflow", "askforpermission activated..3");

                            //This is called if user has denied the permission before
                            //In this case I am just asking the permission again
                            ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, requestCode);

                        } else {
                            Log.e("apkflow", "askforpermission activated..4");

                            ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, requestCode);
                        }
                    } else {
                        Log.e("apkflow", "askforpermission activated..5");
                        Toast.makeText(c, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
                getContext().startActivity(callIntent);

            }
        });
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("apkflow", "onRequestpermission activated..1");

        if (ActivityCompat.checkSelfPermission(c, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            Log.e("apkflow", "onRequestpermission activated..2");

            Toast.makeText(c, "Permission granted", Toast.LENGTH_SHORT).show();
        } else {
            Log.e("apkflow", "onRequestpermission activated..3");

            Toast.makeText(c, "Permission denied", Toast.LENGTH_SHORT).show();

            /*setFragment(new healthtips(), "Health Tips");*/
        }


    }
}
