import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CheckingPhoneTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldFillPhoneFromTenNumbers() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван");
        $("[data-test-id=\"phone\"] .input__control").setValue("+7894561230");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=\"phone\"] .input__sub").shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldFillPhoneFromTwelveNumbers() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван");
        $("[data-test-id=\"phone\"] .input__control").setValue("+789456123078");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=\"phone\"] .input__sub").shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldFillPhoneFromElevenNumbersWithoutPlus() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван");
        $("[data-test-id=\"phone\"] .input__control").setValue("78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=\"phone\"] .input__sub").shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldFillPhoneFromElevenNumbersWithSeveralPluses() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612+307+");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=\"phone\"] .input__sub").shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldFillPhoneFromElevenNumbersWithAnotherSymbols() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван");
        $("[data-test-id=\"phone\"] .input__control").setValue("+7-894-561-2307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=\"phone\"] .input__sub").shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldFillPhoneFromElevenNumbersWithSpaces() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван");
        $("[data-test-id=\"phone\"] .input__control").setValue("+7 894 561 2307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=\"phone\"] .input__sub").shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldFillPhoneFromElevenNumbersWithLetters() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван");
        $("[data-test-id=\"phone\"] .input__control").setValue("plus78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=\"phone\"] .input__sub").shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}
