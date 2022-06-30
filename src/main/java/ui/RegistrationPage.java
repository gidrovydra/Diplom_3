package ui;

import model.User;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class RegistrationPage {

    //кнопка Зарегистрироваться
    @FindBy(how = How.CSS, using = "[class=\"button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa\"]")
    private SelenideElement buttonRegistration;

    //поля ввода регистрационных данных
    @FindBy (how = How.CSS, using = "[class=\"text input__textfield text_type_main-default\"]")
    private ElementsCollection fieldsRegistration;

    //текст Некорректный пароль
    @FindBy (how = How.XPATH, using = "//*[contains(text(), 'Некорректный пароль')]")
    private SelenideElement textIncorrectPassword;

    //ссылка Войти
    @FindBy (how = How.LINK_TEXT, using = "Войти")
    private SelenideElement linkEnter;

    public RegistrationPage fillRegistrationsFieldsAndRegister(User user) {

        fieldsRegistration.get(0).click();
        fieldsRegistration.get(0).setValue(user.getName());
        fieldsRegistration.get(1).click();
        fieldsRegistration.get(1).setValue(user.getEmail());
        fieldsRegistration.get(2).click();
        fieldsRegistration.get(2).setValue(user.getPassword());
        buttonRegistration.click();

        return this;
}

    public String checkDisplayTextIncorrectPassword() {
        textIncorrectPassword.isDisplayed();
        return textIncorrectPassword.text();
    }

    //страница login с проверкой, точно ли это она
    public boolean checkIsLoginPage(){
        return page(LoginPage.class).isLoginPage();
    }

    //нажать на ссылку Войти
    public LoginPage clickEnterLink(){
        linkEnter.shouldBe(visible, Duration.ofSeconds(5)).click();
        return page(LoginPage.class);
    }

}
