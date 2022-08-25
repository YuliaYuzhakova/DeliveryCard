package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import java.lang.module.Configuration;

public class DeliveryCardTest {

    @BeforeEach
    void setupAll(){
        open ("http://localhost:9999");
    }

    @Test
    void deliveryCardTest(){
        $("[data-test-id=city] input").setValue("Новосибирск");
        String s = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(s);
        $("[name='name']").setValue("Дмитрий Мамин-Сибиряк");
        $("[name='phone']").setValue("+79135554433");
        $("[data-test-id='agreement']").click();
        $("[class='button__text']").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Успешно! Встреча успешно забронирована на "+s));
    }
}
