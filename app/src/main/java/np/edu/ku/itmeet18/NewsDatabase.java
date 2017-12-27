package np.edu.ku.itmeet18;

/**
 * Created by ayush on 8/13/17.
 */


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;


public class NewsDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ITMEET.db";
    public static final String TABLE_NAME = "News";
    public static final String COL_1 = "title";
    public static final String COL_2 = "date";
    public static final String COL_3 = "content";
    public static final String COL_4 = "link";



    private Activity context;

    public NewsDatabase(Context context) {
        super(context, DATABASE_NAME, null, 18);
        Log.e("iamat","NewsDatabase");
//        SQLiteDatabase db = this.getWritableDatabase();


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
        Log.v("iamat", "dboncreate");
        SQL_CREATE_TABLE_NAME = "CREATE TABLE " + NewsDatabase.TABLE_NAME + " (" +
                NewsDatabase.COL_1 + " TEXT NOT NULL, " +
                NewsDatabase.COL_2 + " TEXT NOT NULL, " +
                NewsDatabase.COL_3 + " TEXT NOT NULL, " +
                NewsDatabase.COL_4 + " TEXT NOT NULL " +
                " )";
        db.execSQL(SQL_CREATE_TABLE_NAME);

        Log.v("databasecreated", "oncreate");
        //db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void dropDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
        //db.close();
    }

    public boolean insertData(JSONObject information) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        try {
            contentValues.put(COL_1, information.getJSONObject(COL_1).getString("rendered"));
            contentValues.put(COL_2, information.getString(COL_2));
            contentValues.put(COL_3, information.getJSONObject(COL_3).getString("rendered"));
            contentValues.put(COL_4, information.getString(COL_4));
            Log.v("iamat", "insertData");
        } catch (JSONException e) {
            Log.v("Error", "Database JsonException");
        }
        db.insert(TABLE_NAME, null, contentValues);
        Log.v("iamat", "onerowinserted");
        //db.close();
        return true;
    }




    public Cursor getInfo(SQLiteDatabase db) {
        //String[] columns={COL_1,COL_2,COL_3};
        //SQLiteDatabase database = this.getReadableDatabase();
        String SQL_READ_TABLE_NAME = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + this.COL_2 +" DESC";
        Cursor cursor = db.rawQuery(SQL_READ_TABLE_NAME, null);

        return cursor;
    }




}