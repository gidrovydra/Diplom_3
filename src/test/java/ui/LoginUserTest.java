package ui;

import model.User;
import steps.Steps;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import com.codeborne.selenide.Configuration;

public class LoginUserTest {

    private MainPage mainPage;
    private User user = new User();
    private Steps steps = new Steps();
    private String auth;

    //для запуска в edge
    /*@BeforeClass
    public static void setProperties() {
        Configuration.browser = "edge";
    }*/

    @Before
    public void setUp(){
        user = steps.createCorrectUserData();
        auth = steps.createUser(user);
        mainPage = steps.openMainPage();
    }

    @After
    public void cleanUp(){
        steps.deleteUser(auth);
    }

    @Test
    @DisplayName("Логин по кнопке 'Войти в аккаунт' на главной странице")
    @Description("Тест проверяет логин пользователя при переходе по кнопке Войти в аккаунт на главной странице")
    public void checkLoginByEnterButtonInMainPage(){
        Assert.assertTrue("Некорректно поведение системы. Ошибка при входе в личный кабинет. ", mainPage.clickLoginButton()
                .fillLoginDataAndLogin(user.getEmail(), user.getPassword())
                .clickMyAccount()
                .isAccountPage());
    }

    @Test
    @DisplayName("Логин по ссылке 'Личный кабинет' на главной странице")
    @Description("Тест проверяет логин пользователя при переходе по ссылке 'Личный кабинет' на главной странице")
    public void checkLoginByAccountInMainPage(){
        Assert.assertTrue("Некорректно поведение системы. Ошибка при входе в личный кабинет. ", mainPage.clickMyAccountAndLogin()
                .fillLoginDataAndLogin(user.getEmail(), user.getPassword())
                .clickMyAccount()
                .isAccountPage());

    }

    @Test
    @DisplayName("Логин по ссылке 'Войти' со старницы восстановление пароля")
    @Description("Тест проверяет логин пользователя при переходе по кнопке 'Войти' с формы восстановления пароля")
    public void checkLoginByEnterInRestorePassword(){
        Assert.assertTrue("Некорректно повеение системы. Ошибка при входе в личный кабинет. ", mainPage.clickLoginButton()
                .clickRestorePasswordLink()
                .clickEnterLink()
                .fillLoginDataAndLogin(user.getEmail(), user.getPassword())
                .clickMyAccount()
                .isAccountPage());
    }

    @Test
    @DisplayName("Логин по ссылке 'Войти' со страницы регистрации")
    @Description("Тест проверяет логин пользователя при переходе по ссылке 'Войти' с формы регистрации пользователя")
    public void checkLoginByEnterInRegistration() {
        Assert.assertTrue("Некорректно повеение системы. Ошибка при входе в личный кабинет. ", mainPage.clickLoginButton()
                .clickRegistration()
                .clickEnterLink()
                .fillLoginDataAndLogin(user.getEmail(), user.getPassword())
                .clickMyAccount()
                .isAccountPage());
    }

}
