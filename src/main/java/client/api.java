package client;

import model.User;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
public class api {

    private final String JSON = "application/json";

    public String handleCreateUser = "/api/auth/register";
    public String handleDeleteUser = "/api/auth/user";
    public String handleLoginUser = "/api/auth/login";

    //базовая спецификация запроса
    private RequestSpecification requestSpec = given()
            .baseUri("https://stellarburgers.nomoreparties.site")
            .header("Content-Type", JSON);


    //post-запрос создание пользователя
    public Response createUserAndReturnResponse(User user) {
        return requestSpec
                .and()
                .body(user)
                .when()
                .post(handleCreateUser);
    }

    public String loginUserAndReturnAuth(User user) {
        return requestSpec
                .and()
                .body(user)
                .when()
                .post(handleLoginUser)
                .then()
                .extract()
                .path("accessToken");
    }

    //delete-запрос удаление пользователя по токену
    public Response deleteUserAndReturnResponse(String auth) {
        return requestSpec
                .header("Authorization", auth)
                .delete(handleDeleteUser);
    }

    public Response deleteUserAndReturnResponse(User user) {
        return requestSpec
                .header("Authorization",loginUserAndReturnAuth(user))
                .delete(handleDeleteUser);
    }
}
