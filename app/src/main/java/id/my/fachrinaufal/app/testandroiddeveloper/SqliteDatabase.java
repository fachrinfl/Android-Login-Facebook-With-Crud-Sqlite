package id.my.fachrinaufal.app.testandroiddeveloper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fachrinfl on 21/07/2017.
 */

public class SqliteDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "crud";
    private static final String TABLE_CRUD = "contact";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_EMAIL = "email";

    public SqliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CRUD_TABLE = "CREATE TABLE " + TABLE_CRUD + "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " + COLUMN_NAME + " TEXT, " + COLUMN_PHONE + " TEXT, " + COLUMN_EMAIL + " TEXT " + ")";
        db.execSQL(CREATE_CRUD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_CRUD);
        onCreate(db);
    }

    public List<Item_Crud> listCrud(){
        String sql = "select * from " + TABLE_CRUD;
        SQLiteDatabase db = this.getReadableDatabase();
        List<Item_Crud> storeCrud = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                String email = cursor.getString(3);
                storeCrud.add(new Item_Crud(id, name, phone, email));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeCrud;
    }

    public void addCrud(Item_Crud item_crud){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, item_crud.getName());
        values.put(COLUMN_PHONE, item_crud.getPhone());
        values.put(COLUMN_EMAIL, item_crud.getEmail());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_CRUD, null, values);
    }

    public void updateCrud(Item_Crud item_crud){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, item_crud.getName());
        values.put(COLUMN_PHONE, item_crud.getPhone());
        values.put(COLUMN_EMAIL, item_crud.getEmail());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_CRUD, values, COLUMN_ID + " = ?", new String[] {String.valueOf(item_crud.getId())});
    }

    public void deleteCrud(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CRUD, COLUMN_ID + " = ?", new String[] {String.valueOf(id)});
    }
}
