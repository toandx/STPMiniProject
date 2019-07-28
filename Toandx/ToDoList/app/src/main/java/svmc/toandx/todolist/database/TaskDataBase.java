package svmc.toandx.todolist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import svmc.toandx.todolist.object.Task;

public class TaskDataBase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TODOLIST";
    private static final String TABLE_TASK = "TASKS";
    private static final String COLUMN_ID ="ID";
    private static final String COLUMN_TITLE ="TITLE";
    private static final String COLUMN_NOTE ="NOTE";
    private static final String COLUMN_DUETIME ="DUETIME";
    private static final String COLUMN_REMINDTIME ="REMINDTIME";
    private static final String COLUMN_PRIORITY ="PRIORITY";
    private static final String COLUMN_STATUS ="STATUS";
    private static final String COLUMN_SUBLISTID = "SUBLISTID";
    private SQLiteDatabase db;
    private Cursor cursor;
    private ArrayList<Task> tasks;

    public TaskDataBase(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        tasks=new ArrayList<Task>();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = "CREATE TABLE " + TABLE_TASK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TITLE + " TEXT,"+COLUMN_NOTE+" TEXT,"
                + COLUMN_DUETIME+" DATETIME,"+COLUMN_REMINDTIME+" DATETIME,"+ COLUMN_PRIORITY+" INT,"
                + COLUMN_STATUS+" INT,"+COLUMN_SUBLISTID+" INT)";
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        onCreate(db);
    }
    public void addTask(Task x)
    {
        db=this.getWritableDatabase();
        ContentValues task=new ContentValues();
        task.put(COLUMN_TITLE,x.title);
        task.put(COLUMN_NOTE,x.note);
        task.put(COLUMN_DUETIME,x.dueTime);
        task.put(COLUMN_REMINDTIME,x.remindTime);
        task.put(COLUMN_PRIORITY,x.priority);
        task.put(COLUMN_STATUS,x.status);
        task.put(COLUMN_SUBLISTID,x.subList_ID);
        db.insert(TABLE_TASK,null,task);
        db.close();
    }
    public Task getTask(int id) {

        db = this.getReadableDatabase();

        cursor = db.query(TABLE_TASK, new String[] { COLUMN_ID,
                        COLUMN_TITLE ,COLUMN_NOTE,COLUMN_DUETIME,COLUMN_REMINDTIME,COLUMN_PRIORITY,COLUMN_STATUS,COLUMN_SUBLISTID}, COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Task temp=new Task(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5),cursor.getInt(6),cursor.getInt(7));
        return temp;
    }
    public ArrayList<Task> getTasksFromList(int subListID,String order)
    {
        tasks.clear();
        String selectQuery="SELECT  * FROM " + TABLE_TASK;
        if (subListID>=0) selectQuery=selectQuery+" WHERE "+COLUMN_SUBLISTID+"="+Integer.toString(subListID);
        if (order!=null) selectQuery=selectQuery+" ORDER BY "+order;

        db = this.getWritableDatabase();
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                tasks.add(new Task(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5),cursor.getInt(6),cursor.getInt(7)));
            } while (cursor.moveToNext());
        }
        // return note list
        return tasks;
    }
    public ArrayList<Task> getAllTasks(String order){
        tasks.clear();
        String selectQuery="SELECT  * FROM " + TABLE_TASK;
        if (order!=null) selectQuery=selectQuery+" ORDER BY "+order;

        db = this.getWritableDatabase();
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                tasks.add(new Task(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5),cursor.getInt(6),cursor.getInt(7)));
            } while (cursor.moveToNext());
        }
        // return note list
        return tasks;
    }
    public int getTasksCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TASK;
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
        db.delete(TABLE_TASK,null,null);
        db.close();
    }
    public void updateTask(int id,Task x) {
        db = this.getWritableDatabase();
        ContentValues task = new ContentValues();
        task.put(COLUMN_ID,id);
        task.put(COLUMN_TITLE,x.title);
        task.put(COLUMN_NOTE,x.note);
        task.put(COLUMN_DUETIME,x.dueTime);
        task.put(COLUMN_REMINDTIME,x.remindTime);
        task.put(COLUMN_PRIORITY,x.priority);
        task.put(COLUMN_STATUS,x.status);
        task.put(COLUMN_SUBLISTID,x.subList_ID);
        // updating row
        db.update(TABLE_TASK, task, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }
    public ArrayList<Task> searchTasks(String x)
    {
        tasks.clear();
        String selectQuery;
        selectQuery="SELECT  * FROM " + TABLE_TASK+" WHERE TITLE LIKE \'%"+x+"%\' OR NOTE LIKE \'%"+x+"%\'";
        db = this.getWritableDatabase();
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                tasks.add(new Task(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5),cursor.getInt(6),cursor.getInt(7)));
            } while (cursor.moveToNext());
        }
        return tasks;
    }
    public void deleteTask(int id) {
        db = this.getWritableDatabase();
        db.delete(TABLE_TASK, COLUMN_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }
}
