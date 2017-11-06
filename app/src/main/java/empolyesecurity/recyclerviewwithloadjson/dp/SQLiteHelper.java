package empolyesecurity.recyclerviewwithloadjson.dp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import empolyesecurity.recyclerviewwithloadjson.VolleyBean;

/**
 * Created by Nikunj on 27-08-2015.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SQLiteDatabase.db";

    public static final String TABLE_NAME = "PEOPLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FIRST_NAME = "FIRST_NAME";
    public static final String COLUMN_LAST_NAME = "LAST_NAME";
    public static final String COLUMN_IMAGEURL = "IMAGEURL";

    private SQLiteDatabase database;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FIRST_NAME + " VARCHAR, " + COLUMN_IMAGEURL + " VARCHAR, " + COLUMN_LAST_NAME + " VARCHAR);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertRecord(VolleyBean contact) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FIRST_NAME, contact.getName());
        contentValues.put(COLUMN_IMAGEURL, contact.getImageurl());
        contentValues.put(COLUMN_LAST_NAME, contact.getDate());
        database.insert(TABLE_NAME, null, contentValues);
        database.close();
    }

    public void insertRecordAlternate(VolleyBean contact) {
        database = this.getReadableDatabase();
        database.execSQL("INSERT INTO " + TABLE_NAME + "(" + COLUMN_FIRST_NAME + "," + COLUMN_LAST_NAME + ") VALUES('" + contact.getName() + "','" + contact.getDate() + "')");
        database.close();
    }

    public ArrayList<VolleyBean> getAllRecords() {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<VolleyBean> contacts = new ArrayList<VolleyBean>();
        VolleyBean contactModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                contactModel = new VolleyBean();
                contactModel.setId(cursor.getString(0));
                contactModel.setName(cursor.getString(1));
                contactModel.setImageurl(cursor.getString(2));
                contactModel.setDate(cursor.getString(3));

                contacts.add(contactModel);
            }
        }
        cursor.close();
        database.close();

        return contacts;
    }

    public ArrayList<VolleyBean> getAllRecordsAlternate() {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<VolleyBean> contacts = new ArrayList<VolleyBean>();
        VolleyBean contactModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                contactModel = new VolleyBean();
                contactModel.setId(cursor.getString(0));
                contactModel.setName(cursor.getString(1));
                contactModel.setImageurl(cursor.getString(2));
                contactModel.setDate(cursor.getString(3));

                contacts.add(contactModel);
            }
        }
        cursor.close();
        database.close();

        return contacts;
    }


    public void updateRecord(VolleyBean contact) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FIRST_NAME, contact.getName());
        contentValues.put(COLUMN_LAST_NAME, contact.getDate());
        database.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{contact.getId()});
        database.close();
    }

    public void updateRecordAlternate(VolleyBean contact) {
        database = this.getReadableDatabase();
        database.execSQL("update " + TABLE_NAME + " set " + COLUMN_FIRST_NAME + " = '" + contact.getName() + "', " + COLUMN_LAST_NAME + " = '" + contact.getDate() + "' where " + COLUMN_ID + " = '" + contact.getId() + "'");
        database.close();
    }

    public void deleteAllRecords() {
        database = this.getReadableDatabase();
        database.delete(TABLE_NAME, null, null);
        database.close();
    }

    public void deleteAllRecordsAlternate() {
        database = this.getReadableDatabase();
        database.execSQL("delete from " + TABLE_NAME);
        database.close();
    }

    public void deleteRecord(VolleyBean contact) {
        database = this.getReadableDatabase();
        database.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{contact.getId()});
        database.close();
    }

    public void deleteRecordAlternate(VolleyBean contact) {
        database = this.getReadableDatabase();
        database.execSQL("delete from " + TABLE_NAME + " where " + COLUMN_ID + " = '" + contact.getId() + "'");
        database.close();
    }

    public ArrayList<String> getAllTableName()
    {
        database = this.getReadableDatabase();
        ArrayList<String> allTableNames=new ArrayList<String>();
        Cursor cursor=database.rawQuery("SELECT name FROM sqlite_master WHERE type='table'",null);
        if(cursor.getCount()>0)
        {
            for(int i=0;i<cursor.getCount();i++)
            {
                cursor.moveToNext();
                allTableNames.add(cursor.getString(cursor.getColumnIndex("name")));
            }
        }
        cursor.close();
        database.close();
        return allTableNames;
    }

}
