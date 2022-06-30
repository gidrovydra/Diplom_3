package ui;

import com.codeborne.selenide.Configuration;
import steps.Steps;
import model.User;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;


public class AccountTest {

    private AccountPage accountPage;
    private User user = new User();
    private Steps steps = new Steps();
    private String auth;

    //для запуска в edge
  /*  @BeforeClass
    public static void setProperties() {
        Configuration.browser = "edge";
    }*/

    @Before
    public void setUpOnce() {
        user = steps.createCorrectUserData();
        auth = steps.createUser(user);

        accountPage = steps.openMainPage()
                .clickLoginButton()
                .fillLoginDataAndLogin(user.getEmail(), user.getPassword())
                .clickMyAccount();
    }

    @After
    public void cleanUp(){
        steps.deleteUser(auth);
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    @Description("Тест проверяет переход в личный кабинет пользователя по ссылке 'Личный кабинет'")
    public void checkAccountClick(){
        Assert.assertTrue("Некорректно поведение системы. Ошибка при переходе в личный кабинет. ", accountPage.isAccountPage());
    }

    @Test
    @DisplayName("Переход по Stellar Burgers из личного кабинет")
    @Description("Тест проверяет переход по логотипу Stellar Burgers из личного кабинета пользователя")
    public void checkLogoClick() {
        Assert.assertTrue("Некорректно поведение системы. Ошибка при переходе по логотипу из личного кабинета. ", accountPage.clickLogo().isMainPage());
    }

    @Test
    @DisplayName("Переход по ссылке 'Конструктор' из личного кабинет")
    @Description("Тест проверяет переход по ссылке 'Конструктор' из личного кабинета пользователя")
    public void checkConstructorClick() {
        Assert.assertTrue("Некорректно поведение системы. Ошибка при переходе по ссылке Конструктор из личного кабинета. ", accountPage.clickConstructor().isMainPage());
    }

    @Test
    @DisplayName("Выход из личного кабинет")
    @Description("Тест проверяет переход по кнопке 'Выйход' из личного кабинета пользователя")
    public void checkExitClick() {
        Assert.assertTrue("Некорректно поведение системы. Ошибка при переходе по кнпке Выход из личного кабинета. ", accountPage.clickExitButton().isLoginPage());
    }
}
