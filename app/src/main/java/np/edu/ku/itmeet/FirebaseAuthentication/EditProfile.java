package np.edu.ku.itmeet.FirebaseAuthentication;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import np.edu.ku.itmeet.FirebaseDBHelper.UserDetails;
import np.edu.ku.itmeet.HomeFragment;
import np.edu.ku.itmeet.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditProfile extends Fragment {

    private static final int CHOOSE_IMAGE=101;
    private static final String TAG ="tyag" ;

    DatabaseReference usersDB;

    ImageView imageView;
    EditText profile_name,profile_email,profile_phone,profile_adddress,profile_inst;
    Button save_button,delete_button;
    Spinner profile_age,profile_gender,profile_event,profile_lvl;
    private ProgressBar progressBar;
    String proS;
    FirebaseAuth mAuth;
    FirebaseUser user;
    
    String type="";
    TextView title;

    ArrayAdapter<CharSequence> spinner_age,spinner_gender,spinner_lvl,spinner_event;
    public EditProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_edit, container, false);
        imageView=rootView.findViewById(R.id.edit_profile_image);
            profile_name =rootView.findViewById(R.id.edit_profile_name);
        profile_email =rootView.findViewById(R.id.edit_profile_email);
        profile_phone =rootView.findViewById(R.id.edit_profile_phone);
        profile_adddress=rootView.findViewById(R.id.edit_profile_address);
        profile_inst=rootView.findViewById(R.id.edit_profile_inst);


        profile_age=rootView.findViewById(R.id.spinner_profile_age);
        profile_gender=rootView.findViewById(R.id.spinner_profile_gender);
        profile_event=rootView.findViewById(R.id.spinner_profile_events);
        profile_lvl=rootView.findViewById(R.id.spinner_profile_lvl);
        save_button=rootView.findViewById(R.id.button_save_profile);

        title=rootView.findViewById(R.id.edit_profile_title);


        usersDB = FirebaseDatabase.getInstance().getReference("usersDB");

        mAuth = FirebaseAuth.getInstance();

        user=mAuth.getCurrentUser();

        progressBar = rootView.findViewById(R.id.progressBarx);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEmpty(profile_name)||isEmpty(profile_adddress)||isEmpty(profile_phone)){
                    profile_name.setError("Required Field");
                    profile_adddress.setError("Required Field");
                    profile_phone.setError("Required Field");
                }
                else{
                    saveUserInformation();
                }           
            }
        });
  spinner_age = ArrayAdapter.createFromResource(getActivity(),R.array.age, android.R.layout.simple_spinner_item);
        spinner_gender = ArrayAdapter.createFromResource(getActivity(),R.array.gender, android.R.layout.simple_spinner_item);
        spinner_lvl = ArrayAdapter.createFromResource(getActivity(),R.array.ed, android.R.layout.simple_spinner_item);
        spinner_event = ArrayAdapter.createFromResource(getActivity(),R.array.event_d, android.R.layout.simple_spinner_item);

        spinner_age.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner_gender.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner_lvl.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner_event.setDropDownViewResource(android.R.layout.simple_spinner_item);
        profile_age.setAdapter(spinner_age);
        profile_gender.setAdapter(spinner_gender);
        profile_event.setAdapter(spinner_event);
        profile_lvl.setAdapter(spinner_lvl);

        final SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getActivity());



        String name_x=(mSharedPreference.getString("name",null ));
        String address_x=(mSharedPreference.getString("address",null ));
        String phone_x=(mSharedPreference.getString("phone",null ));
        String age_x=(mSharedPreference.getString("age",null ));
        String gender_x=(mSharedPreference.getString("gender",null ));
        String inst_x=(mSharedPreference.getString("institution",null ));
        String lvl_x=(mSharedPreference.getString("level",null ));
        String event_x=(mSharedPreference.getString("event_d",null ));


        profile_name.setText(name_x);
        profile_email.setText(user.getEmail());
        profile_adddress.setText(address_x);
        profile_phone.setText(phone_x);
        profile_inst.setText(inst_x);
        selectSpinnerValue(profile_age,age_x);
        selectSpinnerValue(profile_gender,gender_x);
        selectSpinnerValue(profile_lvl,lvl_x);
        selectSpinnerValue(profile_event,event_x);
        
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            type = bundle.getString("type", null);
        }
        title.setText(type);
       
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

                    ft.replace(R.id.frag_edit_profile, fragment);
                    ft.addToBackStack("tag");
                    ft.commit();
                    return true;

                }

                return false;
            }
        });
    }

    private void saveUserInformation() {
       String username= profile_name.getText().toString();
        String phone=profile_phone.getText().toString();
        String address=profile_adddress.getText().toString();
        String institution=profile_inst.getText().toString();

        String email=user.getEmail();
        String id= user.getUid();
        String age=profile_age.getSelectedItem().toString();
        String gender=profile_gender.getSelectedItem().toString();
        String lvl=profile_lvl.getSelectedItem().toString();
        String event=profile_event.getSelectedItem().toString();



        UserDetails userinfo=new UserDetails(id,username,address,phone,email,age,gender,institution,lvl,event);
        usersDB.child(id).setValue(userinfo);
        Log.i("tyag","hereko");



        if(user!=null){
            UserProfileChangeRequest profile=new UserProfileChangeRequest.Builder()
                    .setDisplayName(username)
                    .build();
            user.updateProfile(profile)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                 }
                        }
                    });
        }

      SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("name", username);
        editor.putString("email", email);
        editor.putString("address", address);
        editor.putString("phone", phone);
        editor.putString("age", age);
        editor.putString("gender", gender);
        editor.putString("institution", institution);
        editor.putString("level", lvl);
        editor.putString("event_d", event);
        editor.commit();
        
         if(type=="Add Information"){
            Fragment fragment=new HomeFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.main_fragment, fragment);
            ft.commit();        }
        if(type=="Edit Profile"){
            Fragment fragment=new Profile();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.main_fragment, fragment);
            ft.commit();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CHOOSE_IMAGE&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            try{
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

        private void selectSpinnerValue(Spinner spinner, String myString)
    {
        int index = 0;
        for(int i = 0; i < spinner.getCount(); i++){
            if(spinner.getItemAtPosition(i).toString().equals(myString)){
                spinner.setSelection(i);
                break;
            }
        }
    }
    
    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
   
    
}
