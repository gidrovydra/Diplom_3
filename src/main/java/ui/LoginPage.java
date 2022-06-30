package ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    //поле Email
    @FindBy (how= How.NAME, using = "name")
    private SelenideElement inputEmail;

    //поле Password
    @FindBy (how= How.NAME, using = "Пароль")
    private SelenideElement inputPassword;

    //кнопка Войти
    @FindBy(how= How.XPATH, using = ".//*[text()='Войти']")
    private SelenideElement buttonLogin;

    //ссылка Зарегистрироваться
    @FindBy(how = How.LINK_TEXT, using = "Зарегистрироваться")
    private SelenideElement linkMakeReg;

    //ссылка Восстановить пароль
    @FindBy(how = How.LINK_TEXT, using = "Восстановить пароль")
    private SelenideElement linkRestorePassword;


    //ввести email
    public void setInputEmail(String email){
        inputEmail.should(Condition.visible).click();
        inputEmail.setValue(email);
    }

    //ввести пароль
    public void setInputPassword(String pswd){
        inputPassword.click();
        inputPassword.setValue(pswd);
    }

    //ввести регистрацонные данные и нажать Зарегистрироваться
    public MainPage fillLoginDataAndLogin(String email, String pswd) {
        setInputEmail(email);
        setInputPassword(pswd);
        buttonLogin.click();
        return page(MainPage.class);
    }


    //проверка: это страница регистрации?
    public boolean isLoginPage(){
        return linkMakeReg.isEnabled() && linkRestorePassword.isEnabled();
    }

    //перейти по ссылке Зарегистрироваться
    public RegistrationPage clickRegistration(){
        linkMakeReg.should(Condition.visible).click();
        return page(RegistrationPage.class);
    }

    //перейти по ссылке Восстановить пароль
    public RestorePage clickRestorePasswordLink(){
        linkRestorePassword.click();
        return page(RestorePage.class);
    }

}
