package db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import model.Data;

public class DbHelper  extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "db_menghitungberatbadanideal";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_DATA = "data_table";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //--TABLE ITEM ------------------------------------------------------------------------------//
    private static final String DATA_ID = "data_id";
    private static final String DATA_NAME = "data_name";
    private static final String DATA_BB = "data_bb";
    private static final String DATA_TINGGI = "data_tinggi";
    private static final String DATA_IMT = "data_imt";
    private static final String DATA_KET = "data_ket";

    private static final String CREATE_TABLE_DATA = "CREATE TABLE "
            +TABLE_DATA+ "("+DATA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+DATA_NAME+" TEXT, "+DATA_BB+" REAL, "+DATA_TINGGI+" REAL, "+DATA_IMT+" REAL, "+DATA_KET+" TEXT );";

    //--TABLE ORDER CRUD CODE---------------------------------------------------------------------//
    public long createData(String data_name, int data_bb, int data_tinggi, int data_imt, String data_ket) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DATA_NAME, data_name);
        values.put(DATA_BB, data_bb);
        values.put(DATA_TINGGI, data_tinggi);
        values.put(DATA_IMT, data_imt);
        values.put(DATA_KET, data_ket);

        long insert = db.insert(TABLE_DATA, null, values);
        return insert;
    }

    @SuppressLint("Range")
    public ArrayList<Data> readData() {
        ArrayList<Data> userModelArrayList = new ArrayList<Data>();

        String selectQuery = "SELECT * FROM " + TABLE_DATA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Data read = new Data();
                read.setData_id(c.getInt(c.getColumnIndex(DATA_ID)));
                read.setData_name(c.getString(c.getColumnIndex(DATA_NAME)));
                read.setData_bb(c.getInt(c.getColumnIndex(DATA_BB)));
                read.setData_tinggi(c.getInt(c.getColumnIndex(DATA_TINGGI)));
                read.setData_IMT(c.getInt(c.getColumnIndex(DATA_IMT)));
                read.setData_ket(c.getString(c.getColumnIndex(DATA_KET)));
                userModelArrayList.add(read);
            } while (c.moveToNext());
        }
        return userModelArrayList;
    }

    public int updateData(int data_id, String data_name, int data_bb, int data_tinggi, int data_imt, String data_ket) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DATA_ID, data_id);
        values.put(DATA_NAME, data_name);
        values.put(DATA_BB, data_bb);
        values.put(DATA_TINGGI, data_tinggi);
        values.put(DATA_IMT, data_imt);
        values.put(DATA_KET, data_ket);

        return db.update(TABLE_DATA, values, DATA_ID + " = ?", new String[]{String.valueOf(data_id)});
    }

    public void deleteData(int data_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DATA, DATA_ID + " = ?", new String[]{String.valueOf(data_id)});
    }
    //--------------------------------------------------------------------------------------------//
    //--------------------------------------------------------------------------------------------//

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_DATA + "'");
        onCreate(db);
    }
}
