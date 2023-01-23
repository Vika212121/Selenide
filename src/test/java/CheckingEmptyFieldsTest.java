import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CheckingEmptyFieldsTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldBeWithoutCity() {
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван Иванов");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=\"city\"] .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldBeWithoutDate() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван Иванов");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=\"date\"] .input__sub").shouldHave(Condition.text("Неверно введена дата"));
    }

    @Test
    void shouldBeWithoutName() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=\"name\"] .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldBeWithoutPhone() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван Иванов");
        $(byText("Забронировать")).click();
        $("[data-test-id=\"phone\"] .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldBeWithoutSubmittingAgreement() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван Иванов");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $(byText("Забронировать")).click();
        $("[data-test-id=\"agreement\"]").shouldHave(cssClass("input_invalid"));
    }
}
