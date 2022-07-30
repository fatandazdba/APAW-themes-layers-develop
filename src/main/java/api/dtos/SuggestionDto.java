package api.dtos;

public class SuggestionDto {

    private Boolean negative;

    private String description;

    public SuggestionDto(Boolean negative, String description) {
        this.negative = negative;
        this.description = description;
    }

    public Boolean getNegative() {
        return negative;
    }

    public void setNegative(Boolean negative) {
        this.negative = negative;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SuggestionDto{" +
                "negative=" + negative +
                ", description='" + description + '\'' +
                '}';
    }
}
