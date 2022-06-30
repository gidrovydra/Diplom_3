package ui;

import model.User;
import steps.Steps;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import com.codeborne.selenide.Configuration;

public class RegisterUserTest {

    private User user = new User();
    private Steps steps = new Steps();
    private MainPage mainPage;

    //для запуска в edge
   /* @BeforeClass
    public static void setProperties() {
        Configuration.browser = "edge";
    }*/

    @Before
    public void setUp(){
        mainPage = steps.openMainPage();
    }

    @Test
    @DisplayName("Проверка создания пользователя с коротким паролем")
    @Description("Тест проверяет, что при создании пользователя с паролем длиной мене 6 символов, система выдает ошибку 'Некорректный пароль'")
      public void checkCreateUserWithIncorrectPassword(){
        user = steps.createIncorrectUserData();
        Assert.assertEquals("Некорректное поведение системы при регистрации пользователя::", "Некорректный пароль", mainPage.clickMyAccountAndLogin()
                .clickRegistration()
                .fillRegistrationsFieldsAndRegister(user)
                .checkDisplayTextIncorrectPassword());
    }

    @Test
    @DisplayName("Проверка создания пользователя с корректными данными")
    @Description("Тест провреяет, что при корректных данных успешно создается пользватель и осуществляется переход на страницу Входа в личный кабинет")
    public void checkCreateUserWithCorrectPassword(){
        user = steps.createCorrectUserData();
        Assert.assertTrue("Некорректное поведение системы при регистрации пользователя:", mainPage.clickMyAccountAndLogin()
                .clickRegistration()
                .fillRegistrationsFieldsAndRegister(user)
                .checkIsLoginPage());

        steps.deleteUser(user);
    }

}
