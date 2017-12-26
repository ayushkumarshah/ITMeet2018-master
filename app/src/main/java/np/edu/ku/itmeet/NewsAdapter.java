package np.edu.ku.itmeet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ayush on 12/14/17.
 */

public class NewsAdapter extends ArrayAdapter {

    List list=new ArrayList();


    public NewsAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }


    public void add(News object) {
        list.add(object);
        super.add(object);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row=convertView;
        NewsHolder newsHolder;

        if(row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.list_single,parent,false);
            newsHolder=new NewsHolder();
            newsHolder.Title= (TextView) row.findViewById(R.id.title);
            newsHolder.Date= (TextView) row.findViewById(R.id.date);
            newsHolder.Content= (TextView) row.findViewById(R.id.content);
            row.setTag(newsHolder);
        }
        else
        {
            newsHolder=(NewsHolder)row.getTag();
        }
        News news=(News)getItem(position);
        newsHolder.Title.setText(news.getTitle());
        newsHolder.Date.setText(news.getDate());
        String url;
        url=news.getLink();
        url="<a href=\""+url+"\">"+url+"</a>";
        PicassoImageGetter imageGetter = new PicassoImageGetter(newsHolder.Content,getContext());
        Spannable html;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            html = (Spannable) Html.fromHtml(news.getContent()+"<br>Source :"+url, Html.FROM_HTML_MODE_LEGACY, imageGetter, null);
        } else {
            html = (Spannable) Html.fromHtml(news.getContent()+"<br>Source : "+url, imageGetter, null);
        }
        newsHolder.Content.setText(html);

        newsHolder.Content.setMovementMethod(LinkMovementMethod.getInstance());
        /*newsHolder.Content.setText(Html.fromHtml(news.getContent().replaceAll("<img.+?>", "")));
        newsHolder.Content.setMovementMethod(LinkMovementMethod.getInstance());*/
        return row;
    }

    static class NewsHolder
    {
        TextView Title, Date,Content;



    }
}
