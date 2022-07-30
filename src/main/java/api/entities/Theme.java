package api.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Theme {

    private String id;

    private String reference;

    private LocalDateTime date;

    private Category category;

    private User user;

    private List<Vote> votes;

    public Theme(String reference) {
        this.reference = reference;
        this.date = LocalDateTime.now();
        this.votes = new ArrayList<>();
    }

    public static Builder builder(String reference) {
        return new Builder(reference);
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

    public LocalDateTime getDate() {
        return date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "id='" + id + '\'' +
                ", reference='" + reference + '\'' +
                ", date=" + date +
                ", category=" + category +
                ", user=" + user +
                ", votes=" + votes +
                '}';
    }

    public static class Builder {
        private Theme theme;

        private Builder(String reference) {
            this.theme = new Theme(reference);
        }

        public Builder id(String id) {
            this.theme.id = id;
            return this;
        }

        public Builder category(Category category) {
            this.theme.category = category;
            return this;
        }

        public Builder user(User user) {
            this.theme.user = user;
            return this;
        }

        public Builder vote(Vote vote) {
            this.theme.votes.add(vote);
            return this;
        }

        public Theme build() {
            return this.theme;
        }
    }
}
