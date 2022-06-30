package steps;

import model.User;
import client.api;
import ui.MainPage;

import org.apache.commons.lang3.RandomStringUtils;

import static com.codeborne.selenide.Selenide.open;
import static org.apache.hc.core5.http.HttpStatus.SC_ACCEPTED;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;

public class Steps {

    private api apiClient = new api();

    public MainPage openMainPage(){
        return open("https://stellarburgers.nomoreparties.site", MainPage.class);
    }
    public User createCorrectUserData(){
        User user = new User();
        user.setName(RandomStringUtils.randomAlphabetic(10));
        user.setEmail(RandomStringUtils.randomAlphabetic(10) + "@" +RandomStringUtils.randomAlphabetic(6) + "." + RandomStringUtils.randomAlphabetic(3));
        user.setPassword(RandomStringUtils.randomAlphanumeric(10));

        return user;
    }

    public User createIncorrectUserData(){
        User user = new User();
        user.setName(RandomStringUtils.randomAlphabetic(10));
        user.setEmail(RandomStringUtils.randomAlphabetic(10) + "@" +RandomStringUtils.randomAlphabetic(6) + "." + RandomStringUtils.randomAlphabetic(3));
        user.setPassword(RandomStringUtils.randomAlphanumeric(3));

        return user;
    }

    public String createUser(User user) {
        return apiClient.createUserAndReturnResponse(user)
                .then()
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .extract()
                .body()
                .path("accessToken");
    }

    public void deleteUser(String auth) {
        apiClient.deleteUserAndReturnResponse(auth)
                .then()
                .assertThat()
                .statusCode(SC_ACCEPTED);
    }

    public void deleteUser(User user) {
        apiClient.deleteUserAndReturnResponse(user)
                .then()
                .assertThat()
                .statusCode(SC_ACCEPTED);
    }

}
