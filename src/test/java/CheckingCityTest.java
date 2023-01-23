import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CheckingCityTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldFillAllFieldsCorrectWithCityInOneWordLowCase() {
        $("[data-test-id=\"city\"] .input__control").setValue("москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван Иванов");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $(byText("Успешно!")).should(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldFillAllFieldsCorrectWithCityInOneWordUpperCase() {
        $("[data-test-id=\"city\"] .input__control").setValue("МОСКВА");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван Иванов");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $(byText("Успешно!")).should(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldFillAllFieldsCorrectWithCityInTwoWordsWithSpace() {
        $("[data-test-id=\"city\"] .input__control").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван Иванов");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $(byText("Успешно!")).should(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldFillAllFieldsCorrectWithCityInTwoWordsWithDash() {
        $("[data-test-id=\"city\"] .input__control").setValue("Горно-Алтайск");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван Иванов");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $(byText("Успешно!")).should(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldFillAllFieldsCorrectWithCityInThreeWordsWithTwoDashes() {
        $("[data-test-id=\"city\"] .input__control").setValue("Ростов-на-Дону");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван Иванов");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $(byText("Успешно!")).should(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldFillAllFieldsCorrectButCityInFourWordsWithThreeDashes() {
        $("[data-test-id=\"city\"] .input__control").setValue("Ростов-на-глубоком-Дону");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван Иванов");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=\"city\"] .input__sub").shouldHave(Condition.text("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldFillAllFieldsCorrectButCityInThreeWordsWithTwoSpaces() {
        $("[data-test-id=\"city\"] .input__control").setValue("Ростов на Дону");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван Иванов");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=\"city\"] .input__sub").shouldHave(Condition.text("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldFillAllFieldsCorrectButCityInOneWordButNotFromList() {
        $("[data-test-id=\"city\"] .input__control").setValue("Сочи");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван Иванов");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=\"city\"] .input__sub").shouldHave(Condition.text("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldFillAllFieldsCorrectButCityWithNumbers() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва12");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван Иванов");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=\"city\"] .input__sub").shouldHave(Condition.text("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldFillAllFieldsCorrectButCityWithLatinLetters() {
        $("[data-test-id=\"city\"] .input__control").setValue("Moskwa");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван Иванов");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=\"city\"] .input__sub").shouldHave(Condition.text("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldFillAllFieldsCorrectButCityWithSymbols() {
        $("[data-test-id=\"city\"] .input__control").setValue("/Мо?ск.ва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван Иванов");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=\"city\"] .input__sub").shouldHave(Condition.text("Доставка в выбранный город недоступна"));
    }
}

