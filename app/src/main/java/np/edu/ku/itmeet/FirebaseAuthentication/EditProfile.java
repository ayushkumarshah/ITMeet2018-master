package np.edu.ku.itmeet.FirebaseAuthentication;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import np.edu.ku.itmeet.FirebaseDBHelper.UserDetails;
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
    EditText profile_name,profile_email,profile_phone,profile_adddress;
    Button save_button,delete_button;
    private ProgressBar progressBar;
    String proS;
    FirebaseAuth mAuth;
    FirebaseUser user;
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
        save_button=rootView.findViewById(R.id.button_save_profile);

        usersDB = FirebaseDatabase.getInstance().getReference("usersDB");

        mAuth = FirebaseAuth.getInstance();

        user=mAuth.getCurrentUser();

        progressBar = rootView.findViewById(R.id.progressBarx);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInformation();
            }
        });

        final SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getActivity());
        String name_x=(mSharedPreference.getString("name",null ));
        String address_x=(mSharedPreference.getString("address",null ));
        String phone_x=(mSharedPreference.getString("phone",null ));


            profile_name.setText(name_x);
            profile_email.setText(user.getEmail());
            profile_adddress.setText(address_x);
            profile_phone.setText(phone_x);

        return rootView;
    }

    private void saveUserInformation() {
        String username= profile_name.getText().toString();
        String phone=profile_phone.getText().toString();
        String address=profile_adddress.getText().toString();
        String email=user.getEmail();
        String id= user.getUid();


        UserDetails userinfo=new UserDetails(id,username,address,phone,email);
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
                                Toast.makeText(getActivity(),"UPDATED",Toast.LENGTH_LONG).show();
                                Log.i("tyag","hereko3");
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
                editor.commit();



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

}
