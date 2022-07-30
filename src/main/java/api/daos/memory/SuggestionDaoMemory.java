package api.daos.memory;

import api.daos.SuggestionDao;
import api.entities.Suggestion;

public class SuggestionDaoMemory extends GenericDaoMemory<Suggestion> implements SuggestionDao {

    @Override
    public String getId(Suggestion suggestion) {
        return suggestion.getId();
    }

    @Override
    public void setId(Suggestion suggestion, String id) {
        suggestion.setId(id);
    }
}
