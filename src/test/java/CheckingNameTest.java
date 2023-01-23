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

public class CheckingNameTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldFillNameInOneWord() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $(byText("Успешно!")).should(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldFillNameInOneWordLowCase() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("иван");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $(byText("Успешно!")).should(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldFillNameInOneWordUpperCase() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("ИВАН");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $(byText("Успешно!")).should(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldFillNameInThreeWordsWithSpaces() {
        $("[data-test-id=\"city\"] .input__control").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван Иванов Иваныч");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $(byText("Успешно!")).should(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldFillNameInToMuchWordsWithSpaces() {
        $("[data-test-id=\"city\"] .input__control").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван Иванов Иваныч но это не точно может быть");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $(byText("Успешно!")).should(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldFillNameWithSeveralSpaces() {
        $("[data-test-id=\"city\"] .input__control").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("И в а н     И в а н о в     И в а н ы ч");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $(byText("Успешно!")).should(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldFillNameFromOnlyOneLetter() {
        $("[data-test-id=\"city\"] .input__control").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("И");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $(byText("Успешно!")).should(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldFillNameInTwoWordsWithDash() {
        $("[data-test-id=\"city\"] .input__control").setValue("Горно-Алтайск");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван-Иванов");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $(byText("Успешно!")).should(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldFillNameInTwoWordsWithSeveralDashes() {
        $("[data-test-id=\"city\"] .input__control").setValue("Горно-Алтайск");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("И-в-а-н---И-в-а-н-о-в");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $(byText("Успешно!")).should(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldFillNameInTwoWordsWithSlash() {
        $("[data-test-id=\"city\"] .input__control").setValue("Горно-Алтайск");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван/Иванов");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=\"name\"] .input__sub").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
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
    void shouldFillNameWithSpacesAndDashes() {
        $("[data-test-id=\"city\"] .input__control").setValue("Ростов-на-Дону");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("И-в а-н И-в а-н о-в");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $(byText("Успешно!")).should(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldFillNameWithNumbers() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("7Иван 125 Иванов7");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=\"name\"] .input__sub").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNameWithLatinLetters() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Ivan Иванов");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=\"name\"] .input__sub").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNameWithSymbols() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("И,в?ан Ива&нов");
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=\"name\"] .input__sub").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNameBeEmpty() {
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=\"phone\"] .input__control").setValue("+78945612307");
        $("[data-test-id=\"agreement\"] .checkbox__box").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=\"name\"] .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }
}


