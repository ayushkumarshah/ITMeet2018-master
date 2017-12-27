package np.edu.ku.itmeet18;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

public class NewsFragment extends Fragment {



    Context context;
    Activity activity;
    ListView list;

    private ProgressDialog loading;
    JSONArray jsonArray;


    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootview = inflater.inflate(R.layout.news, container, false);
        context = rootview.getContext();
        activity = getActivity();
        if (CheckInternetConnection(context))
        {

            getData();

        }
        else
        {
            BackgroundTask backgroundTask=new BackgroundTask(context);
            backgroundTask.execute("get_info");
        }
        list = (ListView) rootview.findViewById(R.id.list2);
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

                    ft.replace(R.id.frag_news, fragment);
                    ft.addToBackStack("tag");
                    ft.commit();

                    return true;

                }

                return false;
            }
        });
    }

    private void getData() {

        loading = ProgressDialog.show(context, "Please wait...", "Fetching...", false, false);

        String url = "http://itmeet.ku.edu.np/index.php?rest_route=/wp/v2/news";
        Log.e("url:", url);

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
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
        NewsDatabase myDB=new NewsDatabase(this.activity);
        myDB.dropDatabase();
        Log.v("iamat","newsdatabase");

        Log.e("length",Integer.toString(json.length()));

        for (int i = 0; i < json.length(); i++) {

            try {

                //insertData(json.getJSONObject(i),i);
                myDB.insertData(json.getJSONObject(i));

                //Toast.makeText(activity, "Update Successfull", Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                Toast.makeText(activity, "Server Error", Toast.LENGTH_SHORT).show();


            }
        }
        BackgroundTask backgroundTask=new BackgroundTask(context);
        backgroundTask.execute("get_info");

    }

    /*public boolean insertData(JSONObject information,int i) {

        try {
            Log.e("title:",information.getJSONObject(COL_1).getString("rendered"));
            Log.e("date:",information.getString(COL_2));
            Log.e("content:",information.getJSONObject(COL_3).getString("rendered"));

            title[i] = information.getJSONObject(COL_1).getString("rendered");
            date[i] = information.getString(COL_2);
            content[i] = information.getJSONObject(COL_3).getString("rendered");
           //String url2=information.getJSONObject("_links").getJSONArray("author").getJSONObject(0).getString("href");

//
        } catch (JSONException e) {
            Log.v("Error", "Database JsonException");
        }


        return true;
    }*/
    public static boolean CheckInternetConnection(Context context) {
        ConnectivityManager connectivity =
                (ConnectivityManager) context.getSystemService(
                        Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

}
