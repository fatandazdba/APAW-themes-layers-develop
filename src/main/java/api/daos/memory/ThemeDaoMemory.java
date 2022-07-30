package api.daos.memory;

import api.daos.ThemeDao;
import api.entities.Theme;

import java.util.List;
import java.util.stream.Collectors;

public class ThemeDaoMemory extends GenericDaoMemory<Theme> implements ThemeDao {

    @Override
    public String getId(Theme theme) {
        return theme.getId();
    }

    @Override
    public void setId(Theme theme, String id) {
        theme.setId(id);
    }

    @Override
    public List<Theme> findByVotesNotEmpty() {
        return this.findAll().stream()
                .filter(theme -> !theme.getVotes().isEmpty())
                .collect(Collectors.toList());
    }
}
