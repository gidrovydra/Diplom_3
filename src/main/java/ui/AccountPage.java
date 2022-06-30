package ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class AccountPage {


    //ссылка на Конструктор
    @FindBy(how = How.XPATH, using = ".//*[text()='Конструктор']")
    private SelenideElement linkConstructor;

    //ссылка на логотип
    @FindBy(how = How.CSS, using = "[class = \"AppHeader_header__logo__2D0X2\"]")
    private SelenideElement linkLogo;

    //кнопка Выйти
    @FindBy (how = How.XPATH, using = ".//*[text()='Выход']")
    private SelenideElement buttonExit;


    public boolean isAccountPage(){
        return buttonExit.isEnabled();
    }

    public LoginPage clickExitButton() {
        buttonExit.should(Condition.enabled).click();
        return page(LoginPage.class);
    }

    public MainPage clickConstructor(){
        linkConstructor.should(Condition.enabled).click();
        return page(MainPage.class);
    }

    public MainPage clickLogo(){
        linkLogo.should(Condition.enabled).click();
        return page(MainPage.class);
    }

}
