package api.entities;

import java.time.LocalDateTime;

public class Vote {

    private int value;

    private LocalDateTime date;

    public Vote(int value) {
        this.value = value;
        this.date = LocalDateTime.now();
    }

    public int getValue() {
        return value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Vote{" +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
