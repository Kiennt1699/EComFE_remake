package Domain;

public class GroupObject {
    private  int id;
    private String name;
    private Runnable onclickListener;

    public Runnable getOnclickListener() {
        if(onclickListener == null)
            return () -> {};
        return onclickListener;
    }

    public void setOnclickListener(Runnable onclickListener) {
        this.onclickListener = onclickListener;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GroupObject(int id, String name) {
        this.id = id;
        this.name = name;
    }


}
