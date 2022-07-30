package api.daos;

import api.entities.Theme;

import java.util.List;

public interface ThemeDao extends GenericDao<Theme, String> {
    List<Theme> findByVotesNotEmpty();
}
