package jackson;

import java.util.Arrays;

import static java.lang.String.format;

public class Tick {
    private Integer tick;

    private Integer[][] users;

    public Integer getTick() {
        return tick;
    }

    public void setTick(Integer tick) {
        this.tick = tick;
    }

    public Integer[][] getUsers() {
        return users;
    }

    public void setUsers(Integer[][] users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return format("#%-5d %s", tick, Arrays.toString(users));
    }
}
