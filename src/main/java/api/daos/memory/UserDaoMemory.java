package api.daos.memory;

import api.daos.UserDao;
import api.entities.User;

public class UserDaoMemory extends GenericDaoMemory<User> implements UserDao {

    @Override
    public String getId(User user) {
        return user.getId();
    }

    @Override
    public void setId(User user, String id) {
        user.setId(id);
    }
}
