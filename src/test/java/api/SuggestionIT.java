package api;

import api.apiControllers.SuggestionApiController;
import api.dtos.SuggestionDto;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SuggestionIT {

    @Test
    void testCreateUser() {
        HttpRequest request = HttpRequest.builder(SuggestionApiController.SUGGESTIONS).body(new SuggestionDto(false, "Mejorable...")).post();
        new Client().submit(request);
    }

    @Test
    void createSuggestionWithoutSuggestionDto() {
        HttpRequest request = HttpRequest.builder(SuggestionApiController.SUGGESTIONS).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void createSuggestionWithoutSuggestionDtoNegative() {
        HttpRequest request = HttpRequest.builder(SuggestionApiController.SUGGESTIONS).body(new SuggestionDto(null, "Mejorable...")).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

}
