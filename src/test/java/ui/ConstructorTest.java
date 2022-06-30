package ui;

import steps.Steps;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.*;

public class ConstructorTest {

    private MainPage mainPage;
    private Steps steps = new Steps();

    //для запуска в edge
   /*@BeforeClass
   public static void setProperties() {
        Configuration.browser = "edge";
   }*/

    @Before
    public void setUp(){
        mainPage = steps.openMainPage();
    }

    @Test
    @DisplayName("Кликнуть по вкладке Булки")
    @Description("Тест проверяет переход в раздел Булки конструктора")
    public void checkBunsClick() {
        mainPage.clickBuns();
        Assert.assertEquals(mainPage.referenceTabs.get(0), mainPage.whichTabSelected());
    }

    @Test
    @DisplayName("Кликнуть по вкладке Соусы")
    @Description("Тест проверяет переход в раздел Соусы конструктора")
    public void checkSaucesClick() {
        mainPage.clickSauces();
        Assert.assertEquals(mainPage.referenceTabs.get(1), mainPage.whichTabSelected());
    }

    @Test
    @DisplayName("Кликнуть по вкладке Начинки")
    @Description("Тест проверяет переход в раздел Начинкиконструктора")
    public void checkFillingsClick() {
        mainPage.clickFillings();
        Assert.assertEquals(mainPage.referenceTabs.get(2), mainPage.whichTabSelected());
    }

}
