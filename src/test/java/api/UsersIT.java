package api;

import api.apiControllers.UserApiController;
import api.dtos.UserDto;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UsersIT {

    @Test
    void testCreateUser() {
        this.createUser();
    }

    private String createUser() {
        HttpRequest request = HttpRequest.builder(UserApiController.USERS).body(new UserDto("uno","uno@email")).post();
        return (String) new Client().submit(request).getBody();
    }

    @Test
    void testUserInvalidRequest() {
        HttpRequest request = HttpRequest.builder(UserApiController.USERS).path("/invalid").body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateUserWithoutUserDto() {
        HttpRequest request = HttpRequest.builder(UserApiController.USERS).body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateUserWithoutUserDtoNick() {
        HttpRequest request = HttpRequest.builder(UserApiController.USERS).body(new UserDto(null)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testUpdateUser() {
        String id = this.createUser();
        HttpRequest request = HttpRequest.builder(UserApiController.USERS).path(UserApiController.ID_ID)
                .expandPath(id).body(new UserDto("dos")).put();
        new Client().submit(request);
    }

    @Test
    void testUpdateUserWithoutUserDto() {
        String id = this.createUser();
        HttpRequest request = HttpRequest.builder(UserApiController.USERS).path(UserApiController.ID_ID)
                .expandPath(id).body(null).put();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testUpdateUserNotFoundException() {
        HttpRequest request = HttpRequest.builder(UserApiController.USERS).path(UserApiController.ID_ID)
                .expandPath("s5FdeGf54D").body(new UserDto("dos")).put();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }
}
