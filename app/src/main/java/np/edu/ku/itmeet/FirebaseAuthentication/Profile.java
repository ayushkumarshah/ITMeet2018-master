package np.edu.ku.itmeet.FirebaseAuthentication;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import np.edu.ku.itmeet.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {

    FirebaseAuth mAuth;

    DatabaseReference usersDB;

    private Button buttonLogout;
    TextView profileName;
    TextView profileEmail;
    TextView profileAddress;
    TextView profilePhone;
    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView=inflater.inflate(R.layout.fragment_profile, container, false);
        usersDB = FirebaseDatabase.getInstance().getReference("usersDB");

        //initializing firebase authentication object
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user= mAuth.getCurrentUser();
        //if the user is not logged in
        //that means current user will return null
        if(mAuth.getCurrentUser() == null){
            Fragment fragment=new Login();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.main_fragment, fragment);
            Log.i("dasd","working");
            ft.commit();
        }
        else{

        //initializing views
        profileName =rootView.findViewById(R.id.profile_name);
        profileEmail = (TextView) rootView.findViewById(R.id.profile_email);
        profileAddress = (TextView) rootView.findViewById(R.id.profile_address);
        profilePhone = (TextView) rootView.findViewById(R.id.profile_phone);
        buttonLogout = (Button) rootView.findViewById(R.id.button_logout);




            final SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getActivity());
            String name_x=(mSharedPreference.getString("name",null ));
            String address_x=(mSharedPreference.getString("address",null ));
            String phone_x=(mSharedPreference.getString("phone",null ));


            profileName.setText(name_x);
            profileEmail.setText(user.getEmail());
            profileAddress.setText(address_x);
            profilePhone.setText(phone_x);




            buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Fragment fragment=new Login();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_fragment, fragment);
                ft.commit();            }
        });

        rootView.findViewById(R.id.edit_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment=new EditProfile();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_fragment, fragment);
                ft.commit();
            }
        });

        }
        return rootView;
    }
}

