package ru.practicum.tests;

import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.practicum.models.Order;
import ru.practicum.steps.OrderSteps;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;


@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {

    private final OrderSteps orderSteps = new OrderSteps();
    private final String[] colors;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;

    public OrderTest(String[] colors) {
        this.colors = colors;
        this.firstName = RandomStringUtils.randomAlphabetic(10);
        this.lastName = RandomStringUtils.randomAlphabetic(10);
        this.address = RandomStringUtils.randomAlphabetic(10);
        this.metroStation = RandomStringUtils.randomNumeric(2);
        this.phone = "+7" + RandomStringUtils.randomNumeric(10);
        this.rentTime = Integer.parseInt(RandomStringUtils.randomNumeric(1));
        this.deliveryDate = "2025-06-02";
        this.comment = RandomStringUtils.randomAlphabetic(30);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new String[]{"BLACK"}},      // Только BLACK
                {new String[]{"GREY"}},       // Только GREY
                {new String[]{"BLACK", "GREY"}},  // Оба цвета
                {new String[]{}}              // Без цвета
        });
    }

    @Test
    public void shouldCreateOrderWithDifferentColorOptions() {
        Order order = new Order()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withAddress(address)
                .withMetroStation(metroStation)
                .withPhone(phone)
                .withRentTime(rentTime)
                .withDeliveryDate(deliveryDate)
                .withComment(comment)
                .withColors(colors);

        ValidatableResponse response = orderSteps.createOrder(order)
                .statusCode(201);

        Integer track = response.extract().path("track");
        assertThat("Track number should not be null", track, notNullValue());
    }
}