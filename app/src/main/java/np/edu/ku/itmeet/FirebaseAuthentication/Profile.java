package np.edu.ku.itmeet.FirebaseAuthentication;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import np.edu.ku.itmeet.HomeFragment;
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
    TextView profileAge,profileGender,profileIns,profileLvl,profileEvent;

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
            Fragment fragment=new Register();
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
            profileAge = (TextView) rootView.findViewById(R.id.profile_age);
            profileGender= (TextView) rootView.findViewById(R.id.profile_gender);
            profileIns = (TextView) rootView.findViewById(R.id.profile_inst);
            profileLvl = (TextView) rootView.findViewById(R.id.profile_lvl);
            profileEvent = (TextView) rootView.findViewById(R.id.profile_event);
        buttonLogout = (Button) rootView.findViewById(R.id.button_logout);




            final SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getActivity());
            String name_x=(mSharedPreference.getString("name",null ));
            String address_x=(mSharedPreference.getString("address",null ));
            String phone_x=(mSharedPreference.getString("phone",null ));
            String age_x=(mSharedPreference.getString("age",null ));
            String gender_x=(mSharedPreference.getString("gender",null ));
            String inst_x=(mSharedPreference.getString("institution",null ));
            String lvl_x=(mSharedPreference.getString("level",null ));
            String event_x=(mSharedPreference.getString("event_d",null ));


            profileName.setText(name_x);
            profileEmail.setText(user.getEmail());
            profileAddress.setText(address_x);
            profilePhone.setText(phone_x);
            profileIns.setText(inst_x);
            profileAge.setText(age_x);
            profileGender.setText(gender_x);
            profileLvl.setText(lvl_x);
            profileEvent.setText(event_x);



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
                final Bundle bundle = new Bundle();
                bundle.putString("type", "Edit Profile");
                Fragment fragment=new EditProfile();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_fragment, fragment);
                ft.commit();
            }
        });

        }
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
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

                    ft.replace(R.id.frag_profile, fragment);
                    ft.addToBackStack("tag");
                    ft.commit();
                    return true;

                }

                return false;
            }
        });
    }
}

