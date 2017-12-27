package np.edu.ku.itmeet18;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import static np.edu.ku.itmeet18.NewsFragment.CheckInternetConnection;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment implements View.OnClickListener {

    Context context;
    Activity activity;
    ListView list;

    private ProgressDialog loading;
    JSONArray jsonArray;
    public EventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.event, container, false);

        context = rootview.getContext();
        activity = getActivity();

        ImageView Alumini,Blood,Career,Clash,Codecamp,Ltsp,Selfie,Python,Coding,Cs,Design,Dota,Fifa,Googling,Hackathon,Hwcomp,Ideapitch,Itquiz,Localization,Penetration,Photography,Projectdemo,Swcomp;

        Alumini=(ImageView) rootview.findViewById(R.id.alumini);
        Alumini.setOnClickListener(this);
        Blood=(ImageView) rootview.findViewById(R.id.blood);
        Blood.setOnClickListener(this);
        Career=(ImageView) rootview.findViewById(R.id.career);
        Career.setOnClickListener(this);
        Clash=(ImageView) rootview.findViewById(R.id.clash);
        Clash.setOnClickListener(this);
        Codecamp=(ImageView) rootview.findViewById(R.id.codecamp);
        Codecamp.setOnClickListener(this);
        Coding=(ImageView) rootview.findViewById(R.id.coding);
        Coding.setOnClickListener(this);
        Cs=(ImageView) rootview.findViewById(R.id.cs);
        Cs.setOnClickListener(this);
        Ltsp=(ImageView) rootview.findViewById(R.id.ltsp);
        Ltsp.setOnClickListener(this);
        Selfie=(ImageView) rootview.findViewById(R.id.selfie);
        Selfie.setOnClickListener(this);
        Python=(ImageView) rootview.findViewById(R.id.python);
        Python.setOnClickListener(this);
        Design=(ImageView) rootview.findViewById(R.id.design);
        Design.setOnClickListener(this);
        Dota=(ImageView) rootview.findViewById(R.id.dota);
        Dota.setOnClickListener(this);
        Fifa=(ImageView) rootview.findViewById(R.id.fifa);
        Fifa.setOnClickListener(this);
        Googling=(ImageView) rootview.findViewById(R.id.googling);
        Googling.setOnClickListener(this);
        Hackathon=(ImageView) rootview.findViewById(R.id.hackathon);
        Hackathon.setOnClickListener(this);
        Hwcomp=(ImageView) rootview.findViewById(R.id.hwcomp);
        Hwcomp.setOnClickListener(this);
        Ideapitch=(ImageView) rootview.findViewById(R.id.idea);
        Ideapitch.setOnClickListener(this);
        Itquiz=(ImageView) rootview.findViewById(R.id.itquiz);
        Itquiz.setOnClickListener(this);
        Localization=(ImageView) rootview.findViewById(R.id.localization);
        Localization.setOnClickListener(this);
        Penetration=(ImageView) rootview.findViewById(R.id.penetration);
        Penetration.setOnClickListener(this);
        Photography=(ImageView) rootview.findViewById(R.id.photography);
        Photography.setOnClickListener(this);
        Projectdemo=(ImageView) rootview.findViewById(R.id.projectdemo);
        Projectdemo.setOnClickListener(this);
        Swcomp=(ImageView) rootview.findViewById(R.id.swcomp);
        Swcomp.setOnClickListener(this);

        if (CheckInternetConnection(context))
        {

            getData();

        }
        else
        {

            /*BackgroundTask backgroundTask=new BackgroundTask(context);
            backgroundTask.execute("get_info");*/
        }


        return rootview;
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

                    ft.replace(R.id.events_frag, fragment);
                    ft.addToBackStack("tag");
                    ft.commit();
                    return true;

                }

                return false;
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        // default method for handling onClick Events..
        Fragment fragment = new event_details();
        Bundle bundle = new Bundle();



        // btnsearch.setVisibility(rootView.INVISIBLE);

        switch (v.getId()) {

            case R.id.alumini:
                // do your code
                bundle.putString("name","Alumini Meet");
                bundle.putString("id","143");
                break;

            case R.id.blood:
                // do your code
                bundle.putString("name","Blood Donation Event");
                bundle.putString("id","196");
                break;

            case R.id.career:
                // do your code
                bundle.putString("id","137");
                bundle.putString("name","Career Fair");

                break;
            case R.id.clash:
                // do your code
                bundle.putString("name","Clash Royale");
                bundle.putString("id","132");
                break;

            case R.id.codecamp:
                // do your code
                bundle.putString("name","Yomari Code Camp");
                bundle.putString("id","155");
                break;

            case R.id.coding:
                // do your code
                bundle.putString("name","Coding Competition");
                bundle.putString("id","135");
                break;
            case R.id.cs:
                // do your code
                bundle.putString("name","Counter Strike Tournament");
                bundle.putString("id","193");
                break;
            case R.id.ltsp:
                // do your code
                bundle.putString("name","LTSP");
                bundle.putString("id","327");
                break;
            case R.id.selfie:
                // do your code
                bundle.putString("name","Endeavor ITMEET 2018 Selfie contest");
                bundle.putString("id","307");
                break;
            case R.id.python:
                // do your code
                bundle.putString("name","Python Meetup");
                bundle.putString("id","315");
                break;

            case R.id.design:
                // do your code
                bundle.putString("name","Designing Challenge");
                bundle.putString("id","134");
                break;

            case R.id.dota:
                // do your code
                bundle.putString("name","Dota2 Competition");
                bundle.putString("id","157");
                break;
            case R.id.fifa:
                // do your code
                bundle.putString("name","Fifa 18 Gaming Tournament");
                bundle.putString("id","158");
                break;

            case R.id.googling:
                bundle.putString("name","Googling");
                bundle.putString("id","133");
                // do your code
                break;

            case R.id.hackathon:
                bundle.putString("name","Hackathon");
                bundle.putString("id","130");
                // do your code
                break;
            case R.id.hwcomp:
                bundle.putString("name","Hardware Competition");
                bundle.putString("id","146");
                // do your code
                break;

            case R.id.idea:
                bundle.putString("name","Idea Pitching");
                bundle.putString("id","138");
                // do your code
                break;

            case R.id.itquiz:
                bundle.putString("name","IT Quiz");
                bundle.putString("id","140");
                // do your code
                break;
            case R.id.localization:
                bundle.putString("name","Localization");
                bundle.putString("id","142");
                // do your code
                break;

            case R.id.penetration:
                bundle.putString("name","Penetration Testing Competition");
                bundle.putString("id","139");
                // do your code
                break;

            case R.id.photography:
                bundle.putString("name","Photography Contest");
                bundle.putString("id","141");
                // do your code
                break;

            case R.id.projectdemo:
                bundle.putString("name","Project Demonstration");
                bundle.putString("id","127");
                // do your code
                break;

            case R.id.swcomp:
                bundle.putString("name","Software Competition");
                bundle.putString("id","128");
                // do your code
                break;

            default:
                break;
        }
        fragment.setArguments(bundle);

        if (fragment != null) {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.events_frag, fragment);
            ft.addToBackStack("tag");
            ft.commit();
        }
    }


    private void getData() {

        loading = ProgressDialog.show(context, "Please wait...", "Fetching...", false, false);

        String url = "http://itmeet.ku.edu.np/index.php?rest_route=%2Fwp%2Fv2%2Fevents&per_page=100";
        Log.e("url:", url);

        StringRequest stringRequest = new StringRequest(url,    new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }


    private void showJSON(String response) {


        try {
            jsonArray = new JSONArray(response);
            updateDataBase(jsonArray);


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateDataBase(JSONArray json) {
        EventsDatabase myDB=new EventsDatabase(this.activity);
        myDB.dropDatabase();
        Log.v("iamat","newsdatabase");
        for (int i = 0; i < json.length(); i++) {

            try {

                //insertData(json.getJSONObject(i),i);
                myDB.insertData(json.getJSONObject(i));

                //Toast.makeText(activity, "Update Successfull", Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                Toast.makeText(activity, "Server Error", Toast.LENGTH_SHORT).show();


            }
        }
        /*BackgroundTask backgroundTask=new BackgroundTask(context);
        backgroundTask.execute("get_info");
*/
    }
}
