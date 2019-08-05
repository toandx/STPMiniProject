package svmc.toandx.todolist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import svmc.toandx.todolist.model.SubList;
import svmc.toandx.todolist.model.Task;

public class CustomDataBase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TODOLIST";
    private static final String TABLE_TASK = "TASKS";
    private static final String TABLE_SUBLIST = "SUBLIST";
    public static final String COLUMN_ID ="ID";
    private static CustomDataBase instance;
    public static final String COLUMN_COLOR1 ="R";
    public static final String COLUMN_COLOR2 ="G";
    public static final String COLUMN_COLOR3 ="B";
    public static final String COLUMN_TITLE ="TITLE";
    public static final String COLUMN_NOTE ="NOTE";
    public static final String COLUMN_DUETIME ="DUETIME";
    public static final String COLUMN_REMINDTIME ="REMINDTIME";
    public static final String COLUMN_PRIORITY ="PRIORITY";
    public static final String COLUMN_STATUS ="STATUS";
    public static final String COLUMN_SUBLISTID = "SUBLISTID";
    public static final String COLUMN_REMINDERTYPE="REMINDERTYPE";
    private SQLiteDatabase db;
    private Cursor cursor;
    private ArrayList<Task> tasks;
    private ArrayList<SubList> subLists;

    private CustomDataBase(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        tasks=new ArrayList<Task>();
        subLists=new ArrayList<SubList>();
    }
    public static CustomDataBase getInstance(Context context)
    {
        if (instance==null)
        {
            instance=new CustomDataBase(context);
        }
        return(instance);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script=String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT,%s TEXT,%s INT"
                        +",%s INT,%s INT,%s INT,%s INT,%s INT)",TABLE_TASK,COLUMN_ID,COLUMN_TITLE,COLUMN_NOTE,
                COLUMN_DUETIME,COLUMN_REMINDTIME,COLUMN_PRIORITY,COLUMN_STATUS,COLUMN_SUBLISTID,COLUMN_REMINDERTYPE);
        db.execSQL(script);
        script=String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT,"+
                        "%s INT,%s INT,%s INT)",
                TABLE_SUBLIST,COLUMN_ID,COLUMN_TITLE,COLUMN_COLOR1,COLUMN_COLOR2,COLUMN_COLOR3);
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBLIST);
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
        task.put(COLUMN_REMINDERTYPE,x.remindType);
        db.insert(TABLE_TASK,null,task);
        db.close();
    }
    public Task getTask(int id) {

        db = this.getReadableDatabase();

        cursor = db.query(TABLE_TASK, new String[] { COLUMN_ID,
                        COLUMN_TITLE
                        ,COLUMN_NOTE,COLUMN_DUETIME,COLUMN_REMINDTIME,COLUMN_PRIORITY,COLUMN_STATUS,COLUMN_SUBLISTID,COLUMN_REMINDERTYPE},
                COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Task temp=new
                Task(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getLong(3),cursor.getLong(4),cursor.getInt(5),cursor.getInt(6),cursor.getInt(7),cursor.getInt(8));
        return temp;
    }
    public ArrayList<Task> getAllTasks(int subListID,String order,Boolean hideCompleted){
        tasks.clear();
        String selectQuery;
        if (subListID>=0)
        {
            if (hideCompleted) selectQuery=String.format("SELECT  * FROM %s WHERE %s=%s AND %s=%s",TABLE_TASK,COLUMN_SUBLISTID,
                    Integer.toString(subListID),COLUMN_STATUS,Integer.toString(0)); else
                selectQuery=String.format("SELECT  * FROM %s WHERE %s=%s",TABLE_TASK,COLUMN_SUBLISTID,
                        Integer.toString(subListID));
        } else
        {
            if (hideCompleted) selectQuery=String.format("SELECT  * FROM %s WHERE %s=%s",TABLE_TASK,COLUMN_STATUS,
                    Integer.toString(0)); else
                selectQuery=String.format("SELECT  * FROM %s",TABLE_TASK);
        }
        if (order!=null) selectQuery=selectQuery+" ORDER BY "+order;
        db = this.getWritableDatabase();
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                tasks.add(new
                        Task(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getLong(3),cursor.getLong(4),cursor.getInt(5),cursor.getInt(6),cursor.getInt(7),cursor.getInt(8)));
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
    public void deleteAllTask()
    {
        db=this.getWritableDatabase();
        db.delete(TABLE_TASK,null,null);
        db.close();
    }
    public void removeCompleteTask()
    {
        db=this.getWritableDatabase();
        db.delete(TABLE_TASK,COLUMN_STATUS+" =?",new String[] { String.valueOf(1)});
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
        task.put(COLUMN_REMINDERTYPE,x.remindType);
        // updating row
        db.update(TABLE_TASK, task, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }
    public void completedAll(int subListID)
    {
        db=this.getWritableDatabase();
        String query;
        if (subListID>=0) {
            query=String.format("UPDATE %s SET %s=%s WHERE %s=%s",TABLE_TASK,COLUMN_STATUS,
                    Integer.toString(1),COLUMN_SUBLISTID,Integer.toString(subListID));
            db.execSQL(query);
        } else // All Task mode
        {
            query=String.format("UPDATE %s SET %s=%s",TABLE_TASK,COLUMN_STATUS,
                    Integer.toString(1));
            db.execSQL(query);
        }
        db.close();
    }
    public ArrayList<Task> searchTasks(String x,int subListID,String order,Boolean hideCompleted)
    {
        tasks.clear();
        String selectQuery;
        selectQuery="SELECT  * FROM " + TABLE_TASK+" WHERE (TITLE LIKE \'%"+x+"%\' OR NOTE LIKE \'%"
                +x+"%\')";
        if (hideCompleted) selectQuery=selectQuery+" AND "+COLUMN_STATUS+"=0";
        if (subListID!=-1) selectQuery=selectQuery+" AND "+COLUMN_SUBLISTID+"="+Integer.toString(subListID);
        if (order!=null) selectQuery=selectQuery+" ORDER BY "+order;
        db = this.getWritableDatabase();
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                tasks.add(new
                        Task(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getLong(3),cursor.getLong(4),cursor.getInt(5),cursor.getInt(6),cursor.getInt(7),cursor.getInt(8)));
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
    public void addSubList(SubList x)
    {
        db=this.getWritableDatabase();
        ContentValues temp=new ContentValues();
        temp.put(COLUMN_TITLE,x.title);
        temp.put(COLUMN_COLOR1,x.colorR);
        temp.put(COLUMN_COLOR2,x.colorG);
        temp.put(COLUMN_COLOR3,x.colorB);
        db.insert(TABLE_SUBLIST,null,temp);
        db.close();
    }
    public SubList getSubList(int id) {

        db = this.getReadableDatabase();
        cursor = db.query(TABLE_SUBLIST, new String[] { COLUMN_ID, COLUMN_TITLE, COLUMN_COLOR1,COLUMN_COLOR2,COLUMN_COLOR3},
                COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        SubList temp=new SubList(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4));
        return temp;
    }
    public ArrayList<SubList> getAllSubList(){
        subLists.clear();
        String selectQuery="SELECT  * FROM " + TABLE_SUBLIST;
        db = this.getWritableDatabase();
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                subLists.add(new SubList(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4)));
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
    public void updateSubList(int id,SubList x) {
        db = this.getWritableDatabase();
        ContentValues temp = new ContentValues();
        temp.put(COLUMN_ID,id);
        temp.put(COLUMN_TITLE,x.title);
        temp.put(COLUMN_COLOR1,x.colorR);
        temp.put(COLUMN_COLOR2,x.colorG);
        temp.put(COLUMN_COLOR3,x.colorB);
        // updating row
        db.update(TABLE_SUBLIST, temp, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }
    public void deleteSubList(int id) {
        db = this.getWritableDatabase();
        db.delete(TABLE_SUBLIST, COLUMN_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.delete(TABLE_TASK,COLUMN_SUBLISTID+" = ?",new String[] { String.valueOf(id) });
        db.close();
    }
}