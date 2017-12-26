package np.edu.ku.itmeet.FirebaseAuthentication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import np.edu.ku.itmeet.HomeFragment;
import np.edu.ku.itmeet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private FirebaseAuth firebaseAuth;
    private Button buttonSignup,skip;
    private ProgressDialog progressDialog;
    private TextView textViewSignin;
    public Register() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_register, container, false);

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        editTextEmail = (EditText) rootView.findViewById(R.id.email_register);
        editTextPassword = (EditText) rootView.findViewById(R.id.password_register);

        buttonSignup = (Button) rootView.findViewById(R.id.button_register);
        skip = (Button) rootView.findViewById(R.id.skip_register);

        progressDialog = new ProgressDialog(getActivity());

        //attaching listener to button
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling register method on click
                registerUser();
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new HomeFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frag_register, fragment);
                ft.addToBackStack("tag");
                ft.commit();
            }
        });

        rootView.findViewById(R.id.already_registered).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Fragment fragment=new Login();
              FragmentTransaction ft = getFragmentManager().beginTransaction();
              ft.replace(R.id.main_fragment, fragment);
               ft.commit();
            }
        });


        //if getCurrentUser does not returns null
        if(firebaseAuth.getCurrentUser() != null){
            Fragment fragment=new Profile();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.main_fragment, fragment);
            ft.commit();
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

                    ft.replace(R.id.frag_register, fragment);
                    ft.addToBackStack("tag");
                    ft.commit();

                    return true;

                }

                return false;
            }
        });
    }

    private void registerUser(){

        //getting email and password from edit texts
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(getActivity(),"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(getActivity(),"Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();
        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here
                            Toast.makeText(getActivity(),"Successfully registered",Toast.LENGTH_LONG).show();
                            Fragment fragment=new Profile();
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.replace(R.id.main_fragment, fragment);
                            ft.commit();
                        }else{
                            //display some message here
                            Toast.makeText(getActivity(),"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
}

