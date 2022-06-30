package ui;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;
import java.util.List;

public class MainPage {

    public List<String> referenceTabs = List.of("Булки", "Соусы", "Начинки");

    //кнопка Войти
    @FindBy(how = How.XPATH, using= ".//*[text()='Войти в аккаунт']")
    private SelenideElement buttonLogin;

    //кнопка Личный кабинет
    @FindBy(how = How.LINK_TEXT, using = "Личный Кабинет")
    private SelenideElement buttonAccount;

    //кнопка Оформить заказ
    @FindBy(how = How.XPATH, using = ".//*[text() = 'Оформить заказ']")
    private SelenideElement createOrderButton;

    //доступные вкладки
    @FindBy(how = How.CSS, using = ".tab_tab__1SPyG")
    private ElementsCollection tabs;

    //выбранная вкладка
    @FindBy(how = How.CSS, using = ".tab_tab_type_current__2BEPc")
    private SelenideElement selectedTab;


    public LoginPage clickLoginButton(){
        buttonLogin.click();
        return page(LoginPage.class);
    }

    //нажать на кнопку Личный кабинет и получить страницу входа в аккаунт
    public LoginPage clickMyAccountAndLogin(){
        buttonAccount.shouldBe(visible, Duration.ofSeconds(5)).click();
        return page(LoginPage.class);
    }

    //Нажать на кнопку Личный кабинет и перейти в свой аккаунт
    public AccountPage clickMyAccount() {
        buttonAccount.shouldBe(visible, Duration.ofSeconds(5)).click();
        return page(AccountPage.class);
    }

    //проверка, является ли эта страница Главной страницей
    public boolean isMainPage(){
        return createOrderButton.isEnabled();
    }

    //выбрать вкладку Соусы
    public void clickSauces(){
        tabs.shouldHave(size(referenceTabs.size())).find(text(referenceTabs.get(1))).should(enabled).should(visible).click();
    }

    //выбрать вклдаку Булки
    public void clickBuns(){
        tabs.shouldHave(size(referenceTabs.size())).find(text(referenceTabs.get(2))).should(enabled).should(visible).click();
        tabs.shouldHave(size(referenceTabs.size())).find(text(referenceTabs.get(0))).click();
    }

    //выбрать вкладку Начинки
    public void clickFillings(){
        tabs.shouldHave(size(referenceTabs.size())).find(text(referenceTabs.get(2))).should(enabled).should(visible).click();
    }

    //определить, какая именно вклдака выбрана
    public String whichTabSelected() {
        return selectedTab.should(visible).getText();
    }

}

