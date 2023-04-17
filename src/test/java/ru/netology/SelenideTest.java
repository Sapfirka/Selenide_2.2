package ru.netology;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;


import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class SelenideTest {
    public String LocalDate(int day) {
        LocalDate date = LocalDate.now();
        String plusDays = String.valueOf(LocalDate.now());
        return LocalDate.now().plusDays(day).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    @Test
    void shouldOrderCard() {
        open("http:localhost:9999");
        Configuration.holdBrowserOpen = true;
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(LocalDate(5));
        $("[data-test-id='name'] input").setValue("Васильев-Петров Иван");
        $("[data-test-id='phone'] input").setValue("+79778888888");
        $("[data-test-id='agreement']").click();
        $(".button__content").click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + LocalDate(5)), Duration.ofSeconds(20)).shouldBe(Condition.visible);
    }
}
