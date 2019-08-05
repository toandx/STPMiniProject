package svmc.toandx.todolist.model;

public class Task {
    public int id,subList_ID;
    public String title,note;
    public long remindTime,dueTime;
    public int priority;
    public int status;
    public int remindType;
    public static final int REMIND_NONE=0;
    public static final int REMIND_NOTI=1;
    public static final int REMIND_ALARM=2;
    public Task(String title, String note,long dueTime,long remindTime,int priority,int status,
                int subList_ID,int remindType)
    {
        this.title=title;
        this.note=note;
        this.dueTime=dueTime;
        this.remindTime=remindTime;
        this.priority=priority;
        this.status=status;
        this.subList_ID=subList_ID;
        this.remindType=remindType;
    }
    public Task(int id,String title, String note,long dueTime,long remindTime,int priority,int
                        status,int subList_ID,int remindType)
    {
        this.id=id;
        this.title=title;
        this.note=note;
        this.dueTime=dueTime;
        this.remindTime=remindTime;
        this.priority=priority;
        this.status=status;
        this.subList_ID=subList_ID;
        this.remindType=remindType;
    }
}
