package np.edu.ku.itmeet18;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class registration extends Fragment {


    public registration() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_registration, container, false);

       final Bundle bundle = new Bundle();

        root.findViewById(R.id.btn_alumni_meet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bundle.putString("event", "alumni_meet");
                Fragment fragment = new EventRegistration();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_fragment, fragment);
                ft.commit();
            }
        });
        root.findViewById(R.id.btn_yomari_code_camp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bundle.putString("event", "yomari_code_camp");
                Fragment fragment = new EventRegistration();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_fragment, fragment);
                ft.commit();
            }
        });
        root.findViewById(R.id.btn_career_fair).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bundle.putString("event", "career_fair");
                Fragment fragment = new EventRegistration();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_fragment, fragment);
                ft.commit();
            }
        });
        root.findViewById(R.id.btn_hackathon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bundle.putString("event", "hackathon");
                Fragment fragment = new EventRegistration();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_fragment, fragment);
                ft.commit();
            }
        });
        root.findViewById(R.id.btn_hardware_comp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bundle.putString("event", "hardware_comp");
                Fragment fragment = new EventRegistration();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_fragment, fragment);
                ft.commit();
            }
        });
        root.findViewById(R.id.btn_software_comp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bundle.putString("event", "software_comp");
                Fragment fragment = new EventRegistration();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_fragment, fragment);
                ft.commit();
            }
        });

        root.findViewById(R.id.btn_coding_comp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bundle.putString("event", "coding_comp");
                Fragment fragment = new EventRegistration();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_fragment, fragment);
                ft.commit();
            }
        });
        root.findViewById(R.id.btn_design_comp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bundle.putString("event", "design_comp");
                Fragment fragment = new EventRegistration();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_fragment, fragment);
                ft.commit();
            }
        });
        root.findViewById(R.id.btn_nephack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bundle.putString("event", "nephack");
                Fragment fragment = new EventRegistration();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_fragment, fragment);
                ft.commit();
            }
        });

        
        root.findViewById(R.id.btn_data_local).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bundle.putString("event", "data_local");
                Fragment fragment = new EventRegistration();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_fragment, fragment);
                ft.commit();
            }
        });

        root.findViewById(R.id.btn_googling).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bundle.putString("event", "googling");
                Fragment fragment = new EventRegistration();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_fragment, fragment);
                ft.commit();
            }
        });

        
        root.findViewById(R.id.btn_project_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bundle.putString("event", "project_demo");
                Fragment fragment = new EventRegistration();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_fragment, fragment);
                ft.commit();
            }
        });


        root.findViewById(R.id.btn_csgo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bundle.putString("event", "csgo");
                Fragment fragment = new EventRegistration();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_fragment, fragment);
                ft.commit();
            }
        });


        root.findViewById(R.id.btn_dota).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bundle.putString("event", "dota");
                Fragment fragment = new EventRegistration();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_fragment, fragment);
                ft.commit();
            }
        });

        root.findViewById(R.id.btn_itquiz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bundle.putString("event", "itquiz");
                Fragment fragment = new EventRegistration();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_fragment, fragment);
                ft.commit();
            }
        });

        root.findViewById(R.id.btn_vrmeet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bundle.putString("event", "vrmeet");
                Fragment fragment = new EventRegistration();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_fragment, fragment);
                ft.commit();
            }
        });
        
        return root;
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
                   // getActivity().getSupportFragmentManager().popBackStack();
                    Fragment fragment = new HomeFragment();
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

                    ft.replace(R.id.frag_registration, fragment);
                    ft.addToBackStack("tag");
                    ft.commit();
                    return true;

                }

                return false;
            }
        });
    }
}
