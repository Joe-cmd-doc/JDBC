public class athlete {
    private String name;
    private int id;
    private int sportId;


    public athlete(String name,int sportId,int id) {
        this.name = name;
        this.sportId = sportId;
        this.id = id;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getSportId() {
        return sportId;
    }

    @Override
    public String toString() {
        return "athlete [name=" + name + ", id=" + id + "]";
    }
}
