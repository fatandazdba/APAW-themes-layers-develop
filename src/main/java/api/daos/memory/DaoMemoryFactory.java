package api.daos.memory;

import api.daos.DaoFactory;
import api.daos.SuggestionDao;
import api.daos.ThemeDao;
import api.daos.UserDao;

public class DaoMemoryFactory extends DaoFactory {

    private UserDao userDao;

    private SuggestionDao suggestionDao;

    private ThemeDao themeDao;

    @Override
    public UserDao getUserDao() {
        if (this.userDao == null) {
            this.userDao = new UserDaoMemory();
        }
        return this.userDao;
    }

    @Override
    public SuggestionDao getSuggestionDao() {
        if (this.suggestionDao == null) {
            this.suggestionDao = new SuggestionDaoMemory();
        }
        return this.suggestionDao;
    }

    @Override
    public ThemeDao themeDao() {
        if (this.themeDao == null) {
            this.themeDao = new ThemeDaoMemory();
        }
        return this.themeDao;
    }
}
