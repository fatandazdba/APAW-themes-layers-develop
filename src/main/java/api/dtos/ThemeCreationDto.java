package api.dtos;

import api.entities.Category;

public class ThemeCreationDto {

    private String reference;

    private Category category;

    private String userId;

    public ThemeCreationDto(String reference, Category category, String userId) {
        this.reference = reference;
        this.category = category;
        this.userId = userId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ThemeCreationDto{" +
                "reference='" + reference + '\'' +
                ", category=" + category +
                ", userId='" + userId + '\'' +
                '}';
    }
}
