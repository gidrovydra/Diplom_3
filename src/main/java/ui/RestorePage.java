package ui;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class RestorePage {

    @FindBy(how = How.LINK_TEXT, using = "Войти")
    private SelenideElement linkEnter;

    @FindBy(how = How.XPATH, using= ".//*[text()=Вспомнили пароль?]")
    private SelenideElement textRecallPassword;

    public LoginPage clickEnterLink(){
        linkEnter.click();
        return page(LoginPage.class);
    }

}
