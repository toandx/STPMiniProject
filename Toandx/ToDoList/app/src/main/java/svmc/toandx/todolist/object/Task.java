package svmc.toandx.todolist.object;
public class Task {
    public int id,subList_ID;
    public String title,note;
    public String remindTime,dueTime;
    public int priority;
    public int status;
    public Task(String title, String note,String dueTime,String remindTime,int priority,int status,int subList_ID)
    {
        this.title=title;
        this.note=note;
        this.dueTime=dueTime;
        this.remindTime=remindTime;
        this.priority=priority;
        this.status=status;
        this.subList_ID=subList_ID;
    }
    public Task(int id,String title, String note,String dueTime,String remindTime,int priority,int status,int subList_ID)
    {
        this.id=id;
        this.title=title;
        this.note=note;
        this.dueTime=dueTime;
        this.remindTime=remindTime;
        this.priority=priority;
        this.status=status;
        this.subList_ID=subList_ID;
    }
}
