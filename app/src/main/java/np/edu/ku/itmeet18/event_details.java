package np.edu.ku.itmeet18;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class event_details extends Fragment implements View.OnClickListener {

    Button button;
String title,content,id,url;
    public event_details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_event_details, container, false);

        Bundle bundle = this.getArguments();
       // title=bundle.getString("name");
        id=bundle.getString("id");
        EventsDatabase myDB=new EventsDatabase(getContext());

        content=myDB.getContent(id);
        title=myDB.getTitle(id);
        url=myDB.getUrl(id);
        url="<a href=\""+url+"\">"+url+"</a>";
        TextView Title,Content,Url;
         Title=(TextView)rootview.findViewById(R.id.title);
        Content=(TextView)rootview.findViewById(R.id.content);
       // Url=(TextView)rootview.findViewById(R.id.url);
        button=(Button)rootview.findViewById(R.id.register);
        button.setVisibility(View.VISIBLE);
        String[] event_forms={"Alumini Meet","Yomari Code Camp","Hackathon","Career Fair","NepHack","Software Competition","Hardware Competition","Coding Tournament","Design Challenge","Nephack","Datathon and Localization","Googling","Counter-Strike GO Tournament","Dota 2 Competition"};
        int btn=0;
        for(int i=0;i<event_forms.length;i++)
        {


            if (title.equals(event_forms[i]))
            {
                btn=1;
            }
        }
        if (btn==0)
            button.setVisibility(View.GONE);



        button.setOnClickListener(this);



        Title.setText(title);

        /*Content.setText(
                Html.fromHtml(content, new Html.ImageGetter() {
                    int id1 = 500;
                    @Override

                    public Drawable getDrawable(String s) {
                        Log.d("Image",s);
                        Drawable d = Picasso.with(getContext())
                                .load(s)
                                .placeholder(R.drawable.blooddonation);
                        d.setBounds(0,0,d.getIntrinsicWidth(),d.getIntrinsicHeight());
                        return d;
                    }
                },null)
        );*/

        PicassoImageGetter imageGetter = new PicassoImageGetter(Content,getContext());
        Spannable html;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            html = (Spannable) Html.fromHtml(content+"<br>Source :"+url, Html.FROM_HTML_MODE_LEGACY, imageGetter, null);
        } else {
            html = (Spannable) Html.fromHtml(content+"<br>Source : "+url, imageGetter, null);
        }
        Content.setText(html);

        Content.setMovementMethod(LinkMovementMethod.getInstance());
        //Url.setText(Html.fromHtml(url));
        //Url.setMovementMethod(LinkMovementMethod.getInstance());




        //Toast.makeText(getContext(),"JJJ",Toast.LENGTH_LONG).show();

        return rootview;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        final Bundle bundle = new Bundle();
        int check=0;
        switch (v.getId()) {

            case R.id.register:
                Fragment fragment = new EventRegistration();
                switch (title){
                    case "Alumni Meet":
                        bundle.putString("event", "alumni_meet");
                        check=1;
                        break;

                    case "Yomari Code Camp":
                        bundle.putString("event", "yomari_code_camp");
                        check=1;

                        break;

                    case "Career Fair":
                        bundle.putString("event", "career_fair");
                        check=1;

                        break;

                    case "Hackathon":
                        bundle.putString("event", "hackathon");
                        check=1;

                        break;

                    case "Hardware Competition":
                        bundle.putString("event", "hardware_comp");
                        check=1;
                        break;

                    case "Software Competition":
                        bundle.putString("event", "software_comp");
                        check=1;
                        break;

                    case "Coding Tournament":
                        bundle.putString("event", "coding_comp");
                        check=1;
                        break;


                    case "Design Challenge":
                        bundle.putString("event", "design_comp");
                        check=1;
                        break;

                    case "NepHack":
                        bundle.putString("event", "nephack");
                        check=1;
                        break;

                    case "Datathon and Localization":
                        bundle.putString("event", "data_local");
                        check=1;
                        break;

                    case "Googling":

                        bundle.putString("event", "googling");
                        check=1;
                        break;

                    case "Counter-Strike GO Tournament":

                        bundle.putString("event", "csgo");
                        check=1;
                        break;

                    case "Dota 2 Competition":

                        bundle.putString("event", "dota");
                        check=1;
                        break;

                    default:
                        check=0;

                }
                if (check == 1) {

                    fragment.setArguments(bundle);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.frag_event_details, fragment);
                    ft.commit();
                }
                else
                {
                    Toast.makeText(getContext(),"Registration Form Not Available",Toast.LENGTH_LONG).show();
                    button.setVisibility(View.INVISIBLE);
                }
                break;

        }
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
                    getActivity().getSupportFragmentManager().popBackStack();

                    return true;

                }

                return false;
            }
        });
    }


}
