package svmc.toandx.todolist.object;
public class SubList {
    public int id;
    public String title;
    public SubList(int id, String title) {
        this.id = id;
        this.title = title;
    }
    public SubList(String title) {
        this.title = title;
    }
}
