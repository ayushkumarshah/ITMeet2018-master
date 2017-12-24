package np.edu.ku.itmeet;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by ayush on 12/14/17.
 */

public class BackgroundTask extends AsyncTask<String,News,String> {

    Context ctx;
    NewsAdapter newsAdapter;
    Activity activity;
ListView listView;
    BackgroundTask(Context ctx)
    {
        this.ctx=ctx;
        activity=(Activity)ctx;
    }
    @Override
    public void onPreExecute() {

    }

    @Override
    public String doInBackground(String... params) {

        String method=params[0];
        NewsDatabase myDB=new NewsDatabase(ctx);
        if (method.equals("get_info"))
        {
            listView=(ListView)activity.findViewById(R.id.list2);
            newsAdapter=new NewsAdapter(ctx,R.layout.list_single);
            SQLiteDatabase db=myDB.getReadableDatabase();
            Cursor cursor=myDB.getInfo(db);
            String title,date,content;
            while (cursor.moveToNext()) {
                title = cursor.getString(cursor.getColumnIndex(NewsDatabase.COL_1));
                date = cursor.getString(cursor.getColumnIndex(NewsDatabase.COL_2));
                content = cursor.getString(cursor.getColumnIndex(NewsDatabase.COL_3));
                News news=new News(title,date,content);
                publishProgress(news);

            }
            return "get_info";
        }
        return null;
    }

    @Override
    public void onProgressUpdate(News... values) {

        newsAdapter.add(values[0]);

    }

    @Override
    public void onPostExecute(String result) {

        if (result.equals("get_info")) {
            if (newsAdapter != null)
                listView.setAdapter(newsAdapter);
            else
                Toast.makeText(ctx, "No offline data", Toast.LENGTH_LONG).show();
        }
        else
        {
        Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
        }
    }
}
