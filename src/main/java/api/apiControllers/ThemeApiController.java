package api.apiControllers;

import api.businessController.ThemeBusinessController;
import api.dtos.ThemeCreationDto;
import api.dtos.ThemeIdReferenceDto;
import api.entities.Category;
import api.exceptions.ArgumentNotValidException;

import java.util.List;

public class ThemeApiController {
    public static final String THEMES = "/themes";

    public static final String ID_ID = "/{id}";

    public static final String VOTES = "/votes";

    public static final String AVERAGE = "/average";

    public static final String CATEGORY = "/category";

    public static final String SEARCH = "/search";

    private ThemeBusinessController themeBusinessController = new ThemeBusinessController();

    public String create(ThemeCreationDto themeDto) {
        this.validate(themeDto, "themeDto");
        this.validate(themeDto.getReference(), "themeDto reference");
        return this.themeBusinessController.create(themeDto);
    }

    public List<ThemeIdReferenceDto> readAll() {
        return this.themeBusinessController.readAll();
    }

    public void delete(String id) {
        this.themeBusinessController.delete(id);
    }

    public void createVote(String themeId, Integer vote) {
        this.validate(vote, "vote");
        if (vote < 0 || vote > 10) {
            throw new ArgumentNotValidException("vote is between 0 and 10");
        }
        this.themeBusinessController.createVote(themeId, vote);
    }

    public Double readAverage(String themeId) {
        return this.themeBusinessController.readAverage(themeId);
    }

    public void updateCategory(String themeId, Category category) {
        this.validate(category, "category");
        this.themeBusinessController.updateCategory(themeId, category);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is missing");
        }
    }

    public List<ThemeIdReferenceDto> find(String query) {
        this.validate(query, "query param q");
        if (!"average".equals(query.split(":>=")[0])) {
            throw new ArgumentNotValidException("query param q is incorrect, missing 'average:>='");
        }
        return this.themeBusinessController.findByAverageGreaterThanEqual(Double.valueOf(query.split(":>=")[1]));
    }
}
