package koreatech.link;

/**
 * Created by Kyo on 2016. 8. 16..
 */
public class NameId {
    private String name;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NameId{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}