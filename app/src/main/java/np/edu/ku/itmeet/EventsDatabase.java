package np.edu.ku.itmeet;

/**
 * Created by ayush on 14/12/17.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;


public class EventsDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ITMEET.db";
    public static final String TABLE_NAME = "Event";
    public static final String COL_1 = "id";
    public static final String COL_2 = "title";
    public static final String COL_3 = "link";
    public static final String COL_4 = "content";
    private  Context context;

    public EventsDatabase(Context context) {
        super(context, DATABASE_NAME, null, 18);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v("iamat", "dboncreate");
        String SQL_CREATE_TABLE_NAME = "CREATE TABLE " + EventsDatabase.TABLE_NAME + " (" +
                EventsDatabase.COL_1 + " TEXT NOT NULL, " +
                EventsDatabase.COL_2 + " TEXT NOT NULL, " +
                EventsDatabase.COL_3 + " TEXT NOT NULL, " +
                EventsDatabase.COL_4 + " TEXT NOT NULL " +
                " )";
        db.execSQL(SQL_CREATE_TABLE_NAME);

        SQL_CREATE_TABLE_NAME = "CREATE TABLE " + NewsDatabase.TABLE_NAME + " (" +
                NewsDatabase.COL_1 + " TEXT NOT NULL, " +
                NewsDatabase.COL_2 + " TEXT NOT NULL, " +
                NewsDatabase.COL_3 + " TEXT NOT NULL " +
                " )";
        db.execSQL(SQL_CREATE_TABLE_NAME);

        Log.v("databasecreated", "oncreate");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void dropDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
    }

    public boolean insertData(JSONObject information) {
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            contentValues.put(COL_1, information.getString(COL_1));
            contentValues.put(COL_2, information.getJSONObject(COL_2).getString("rendered"));
            contentValues.put(COL_3, information.getString(COL_3));
            contentValues.put(COL_4, information.getJSONObject(COL_4).getString("rendered"));
            Log.v("iamat", "eventinserted`");
            System.out.println("JsonInfo"+information.getJSONObject(COL_4).getString("rendered"));

        } catch (JSONException e) {
            Log.v("Error", "Database JsonException");
        }

        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public String getContent(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String SQL_READ_TABLE_NAME = "SELECT "+this.COL_4+" FROM " + TABLE_NAME + " WHERE " + this.COL_1 + "=\"" + id + "\"";
        Cursor res =db.rawQuery(SQL_READ_TABLE_NAME, null);
        if(res.moveToNext())

        {
            String content = res.getString(0);
        Log.e("content ",content);
        return content;
        }
        else
            return "Sorry no data";
    }

    public String getUrl(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String SQL_READ_TABLE_NAME = "SELECT "+this.COL_3+" FROM " + TABLE_NAME + " WHERE " + this.COL_1 + "=\"" + id + "\"";
        Cursor res =db.rawQuery(SQL_READ_TABLE_NAME, null);
        if(res.moveToNext()) {
            String url = res.getString(0);
            Log.e("url ", url);
            return url;
        }
        else
            return "Sorry no data";
    }

    public String getTitle(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String SQL_READ_TABLE_NAME = "SELECT "+this.COL_2+" FROM " + TABLE_NAME + " WHERE " + this.COL_1 + "=\"" + id + "\"";
        Cursor res =db.rawQuery(SQL_READ_TABLE_NAME, null);
        if(res.moveToNext()) {
            String title = res.getString(0);
            Log.e("title ", title);
            return title;
        }
        else
            return "Sorry no data";
    }


}