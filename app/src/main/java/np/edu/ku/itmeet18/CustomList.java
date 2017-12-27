package np.edu.ku.itmeet18;

/**
 * Created by ayush on 8/13/17.
 */

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class CustomList extends ArrayAdapter<String> {
    private final Context context;
    private final Activity activity;
    private final String[] title,date,content;


    public CustomList(Context context,
                      String[] title, String[] date, String[] content, Activity activity) {
        super(context, R.layout.list_single, title);
        this.context = context;
        this.title = title;

        this.date = date;
        this.content = content;

        this.activity=activity;


    }


    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        final View rowView= inflater.inflate(R.layout.list_single, null, true);


        TextView  Title, Date,Content;




        Title= (TextView) rowView.findViewById(R.id.title);
        Date= (TextView) rowView.findViewById(R.id.date);
        Content= (TextView) rowView.findViewById(R.id.content);
        Title.setText(title[position]);
        Date.setText(date[position]);
        Content.setText(Html.fromHtml(content[position]));
        Content.setMovementMethod(LinkMovementMethod.getInstance());


//"yyyy-MM-dd'T'HH:mm:ss



        return rowView;
    }

}
