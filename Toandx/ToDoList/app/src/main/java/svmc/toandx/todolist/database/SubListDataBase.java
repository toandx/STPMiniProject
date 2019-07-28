package svmc.toandx.todolist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import svmc.toandx.todolist.object.SubList;
public class SubListDataBase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TODOLIST";
    private static final String TABLE_SUBLIST = "SUBLIST";
    private static final String COLUMN_ID ="ID";
    private static final String COLUMN_TITLE ="TITLE";
    private SQLiteDatabase db;
    private Cursor cursor;
    private ArrayList<SubList> subLists;

    public SubListDataBase(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        subLists=new ArrayList<SubList>();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = "CREATE TABLE " + TABLE_SUBLIST + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TITLE + " TEXT)";
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBLIST);
        onCreate(db);
    }
    public void addSubList(SubList x)
    {
        db=this.getWritableDatabase();
        ContentValues temp=new ContentValues();
        temp.put(COLUMN_TITLE,x.title);
        db.insert(TABLE_SUBLIST,null,temp);
        db.close();
    }
    public SubList getSubList(int id) {

        db = this.getReadableDatabase();

        cursor = db.query(TABLE_SUBLIST, new String[] { COLUMN_ID, COLUMN_TITLE }, COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        SubList temp=new SubList(cursor.getInt(0),cursor.getString(1));
        return temp;
    }
    public ArrayList<SubList> getAllSubList(String order){
        subLists.clear();
        String selectQuery="SELECT  * FROM " + TABLE_SUBLIST;
        if (order!=null) selectQuery=selectQuery+" ORDER BY "+order;

        db = this.getWritableDatabase();
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                subLists.add(new SubList(cursor.getInt(0),cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        // return note list
        return subLists;
    }
    public int getSubListCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SUBLIST;
        db = this.getReadableDatabase();
        cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }
    public void deleteAll()
    {
        db=this.getWritableDatabase();
        db.delete(TABLE_SUBLIST,null,null);
        db.close();
    }
    public void updateSubList(int id,SubList x) {
        db = this.getWritableDatabase();
        ContentValues temp = new ContentValues();
        temp.put(COLUMN_ID,id);
        temp.put(COLUMN_TITLE,x.title);
        // updating row
        db.update(TABLE_SUBLIST, temp, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }
    public ArrayList<SubList> searchSubList(String x)
    {
        subLists.clear();
        String selectQuery;
        selectQuery="SELECT  * FROM " + TABLE_SUBLIST+" WHERE TITLE LIKE \'%"+x+"%\'";
        db = this.getWritableDatabase();
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                subLists.add(new SubList(cursor.getInt(0),cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        return subLists;
    }
    public void deleteSubList(int id) {
        db = this.getWritableDatabase();
        db.delete(TABLE_SUBLIST, COLUMN_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }
}
