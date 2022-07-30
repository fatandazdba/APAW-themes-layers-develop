package api.entities;

public class Suggestion {

    private String id;

    private Boolean negative;

    private String description;

    public Suggestion(Boolean negative, String description) {
        this.negative = negative;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getNegative() {
        return negative;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Suggestion{" +
                "id='" + id + '\'' +
                ", negative=" + negative +
                ", description='" + description + '\'' +
                '}';
    }
}
