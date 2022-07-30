package api;

import api.apiControllers.ThemeApiController;
import api.apiControllers.UserApiController;
import api.dtos.ThemeCreationDto;
import api.dtos.ThemeIdReferenceDto;
import api.dtos.UserDto;
import api.entities.Category;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ThemeIT {

    @Test
    void testCreateTheme() {
        this.createTheme("Theme one");
    }

    private String createTheme(String theme) {
        String userId = this.createUser();
        HttpRequest request = HttpRequest.builder(ThemeApiController.THEMES)
                .body(new ThemeCreationDto(theme, Category.SPORT, userId)).post();
        return (String) new Client().submit(request).getBody();
    }

    private String createUser() {
        HttpRequest request = HttpRequest.builder(UserApiController.USERS).body(new UserDto("uno","uno@email")).post();
        return (String) new Client().submit(request).getBody();
    }

    @Test
    void testCreateThemeUserIdNotFound() {
        HttpRequest request = HttpRequest.builder(ThemeApiController.THEMES)
                .body(new ThemeCreationDto("Theme one", Category.SPORT, "h3rFdEsw")).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    void testCreateThemeWithoutCategoryUser() {
        HttpRequest request = HttpRequest.builder(ThemeApiController.THEMES)
                .body(new ThemeCreationDto("Theme one", null, null)).post();
        new Client().submit(request);
    }

    @Test
    void testReadAll() {
        for (int i = 0; i < 5; i++) {
            this.createTheme("theme" + i);
        }
        HttpRequest request = HttpRequest.builder(ThemeApiController.THEMES).get();
        List<ThemeIdReferenceDto> themes = (List<ThemeIdReferenceDto>) new Client().submit(request).getBody();
        assertTrue(themes.size() >= 5);
    }

    @Test
    void testDelete() {
        String id = this.createTheme("uno");
        HttpRequest request1 = HttpRequest.builder(ThemeApiController.THEMES).get();
        int count = ((List<ThemeIdReferenceDto>) new Client().submit(request1).getBody()).size();
        HttpRequest request2 = HttpRequest.builder(ThemeApiController.THEMES).path(UserApiController.ID_ID)
                .expandPath(id).delete();
        new Client().submit(request2);
        assertTrue(((List<ThemeIdReferenceDto>) new Client().submit(request1).getBody()).size() < count);
    }

    @Test
    void testVoteTheme() {
        String id = this.createTheme("uno");
        voteTheme(id, 5);
    }

    private void voteTheme(String themeId, Integer vote) {
        HttpRequest request = HttpRequest.builder(ThemeApiController.THEMES).path(ThemeApiController.ID_ID)
                .expandPath(themeId).path(ThemeApiController.VOTES).body(vote).post();
        new Client().submit(request);
    }

    @Test
    void testVoteThemeThemeIdNotFound() {
        HttpException exception = assertThrows(HttpException.class, () -> this.voteTheme("h3rFdEsw", 5));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    void testThemeAverage() {
        String id = this.createTheme("uno");
        this.voteTheme(id, 5);
        this.voteTheme(id, 10);
        HttpRequest request = HttpRequest.builder(ThemeApiController.THEMES).path(ThemeApiController.ID_ID)
                .expandPath(id).path(ThemeApiController.AVERAGE).get();
        assertEquals(7.5, ((Double) new Client().submit(request).getBody()), 10e-5);
    }

    @Test
    void testUpdateCategory() {
        String id = this.createTheme("uno");
        HttpRequest request = HttpRequest.builder(ThemeApiController.THEMES).path(ThemeApiController.ID_ID)
                .expandPath(id).path(ThemeApiController.CATEGORY).body(Category.LEISURE_TIME).patch();
        new Client().submit(request);
    }

    @Test
    void testSearchAverage() {
        String id = this.createTheme("uno");
        this.voteTheme(id, 5);
        this.voteTheme(id, 10);
        HttpRequest request = HttpRequest.builder(ThemeApiController.THEMES).path(ThemeApiController.SEARCH)
                .param("q", "average:>=7").get();
        List<ThemeIdReferenceDto> themes = (List<ThemeIdReferenceDto>) new Client().submit(request).getBody();
        assertFalse(themes.isEmpty());
    }

    @Test
    void testSearchAverageWithoutParamQ() {
        String id = this.createTheme("uno");
        this.voteTheme(id, 5);
        this.voteTheme(id, 10);
        HttpRequest request = HttpRequest.builder(ThemeApiController.THEMES).path(ThemeApiController.SEARCH)
                .param("error", "average:>=7").get();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testSearchAverageParamError() {
        String id = this.createTheme("uno");
        this.voteTheme(id, 5);
        this.voteTheme(id, 10);
        HttpRequest request = HttpRequest.builder(ThemeApiController.THEMES).path(ThemeApiController.SEARCH)
                .param("error", "error:>=7").get();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }


}
