package np.edu.ku.itmeet18.FirebaseAuthentication;


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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import np.edu.ku.itmeet18.HomeFragment;
import np.edu.ku.itmeet18.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Reset extends Fragment {

    EditText forgot_email;
    Button back,email;
    private FirebaseAuth auth;
    private ProgressBar progressBar;

    public Reset() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_reset, container, false);

        forgot_email=rootView.findViewById(R.id.forgot_email);
        //back=rootView.findViewById(R.id.btn_back);
        email=rootView.findViewById(R.id.button_forgot_password);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        auth = FirebaseAuth.getInstance();


        /*back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });*/

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = forgot_email.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    forgot_email.setError("Please enter registered email");                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getActivity(), "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity(), "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                }

                                progressBar.setVisibility(View.GONE);
                            }
                        });
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
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

                    ft.replace(R.id.reset, fragment);
                    ft.addToBackStack("tag");
                    ft.commit();

                    return true;

                }

                return false;
            }
        });
    }

}
