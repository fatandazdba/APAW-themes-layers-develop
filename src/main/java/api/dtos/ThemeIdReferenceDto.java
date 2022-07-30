package api.dtos;

import api.entities.Theme;

public class ThemeIdReferenceDto {

    private String id;

    private String reference;

    public ThemeIdReferenceDto(Theme theme) {
        this.id = theme.getId();
        this.reference = theme.getReference();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "ThemeIdReferenceDto{" +
                "id='" + id + '\'' +
                ", reference='" + reference + '\'' +
                '}';
    }
}
