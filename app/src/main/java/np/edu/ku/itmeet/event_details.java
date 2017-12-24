package np.edu.ku.itmeet;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class event_details extends Fragment {

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
        title=bundle.getString("name");
        id=bundle.getString("id");
        TextView Title,Content,Url;
         Title=(TextView)rootview.findViewById(R.id.title);
        Content=(TextView)rootview.findViewById(R.id.content);
       // Url=(TextView)rootview.findViewById(R.id.url);


        EventsDatabase myDB=new EventsDatabase(getContext());

        content=myDB.getContent(id);
        title=myDB.getTitle(id);
        url=myDB.getUrl(id);
        url="<a href=\""+url+"\">"+url+"</a>";

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
