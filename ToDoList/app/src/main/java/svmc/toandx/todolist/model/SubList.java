package svmc.toandx.todolist.model;
public class SubList {
    public int id;
    public String title;
    public int colorR,colorG,colorB;

    public SubList(int id, String title, int colorR, int colorG, int colorB) {
        this.id = id;
        this.title = title;
        this.colorR = colorR;
        this.colorG = colorG;
        this.colorB = colorB;
    }

    public SubList(String title, int colorR, int colorG, int colorB) {
        this.title = title;
        this.colorR = colorR;
        this.colorG = colorG;
        this.colorB = colorB;
    }
}