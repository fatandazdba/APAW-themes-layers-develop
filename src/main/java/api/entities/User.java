package api.entities;

public class User {

    private String id;

    private String nick;

    private String email;

    public User(String nick, String email) {
        this.nick = nick;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getNick() {
        return nick;
    }

    public User setNick(String nick) {
        this.nick = nick;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", nick='" + nick + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
