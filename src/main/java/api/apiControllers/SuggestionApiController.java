package api.apiControllers;

import api.businessController.SuggestionBusinessController;
import api.dtos.SuggestionDto;
import api.exceptions.ArgumentNotValidException;

public class SuggestionApiController {

    public static final String SUGGESTIONS = "/suggestions";

    private SuggestionBusinessController suggestionBusinessController = new SuggestionBusinessController();

    public void create(SuggestionDto suggestionDto) {
        this.validate(suggestionDto, "suggestionDto");
        this.validate(suggestionDto.getNegative(), "SuggestionDto negative");
        this.validate(suggestionDto.getDescription(), "SuggestionDto description");
        this.suggestionBusinessController.create(suggestionDto);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is missing");
        }
    }

}
