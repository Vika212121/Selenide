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

public class CheckingDateTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldChooseDateToday() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(0);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван Иванов");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=\"date\"] .input__sub")
                .shouldHave(Condition.text("Заказ на выбранную дату невозможен"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldChooseDateLessThenPlusThreeDays() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(2);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван Иванов");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=\"date\"] .input__sub")
                .shouldHave(Condition.text("Заказ на выбранную дату невозможен"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldChooseDateInThePast() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[placeholder=\"Дата встречи\"]").setValue("01.01.2022");
        $("[data-test-id=\"name\"] .input__control").setValue("Иван Иванов");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $(byText("Заказ на выбранную дату невозможен")).should(visible);
    }

}

