package ru.practicum.tests;

import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import ru.practicum.models.Order;
import ru.practicum.steps.OrderSteps;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class OrderListTest extends BaseTest {

    private OrderSteps orderSteps;

    @Before
    public void setUp() {
        orderSteps = new OrderSteps();
    }

    @Test
    public void shouldReturnListOfOrders() {
        ValidatableResponse response = orderSteps.getOrdersList()
                .statusCode(200);
        List<Order> orders = response.extract().jsonPath().getList("orders", Order.class);
        assertThat("Orders list should not be null", orders, notNullValue());
        assertThat("Orders list should not be empty", orders.size(), greaterThan(0));
                Order firstOrder = orders.get(0);
        assertThat("Order should have id", firstOrder.getId(), notNullValue());
        assertThat("Order should have courierId (can be null)",
                firstOrder.getCourierId(), anyOf(nullValue(), instanceOf(Integer.class)));
        assertThat("Order should have firstName", firstOrder.getFirstName(), notNullValue());
        assertThat("Order should have lastName", firstOrder.getLastName(), notNullValue());
        assertThat("Order should have address", firstOrder.getAddress(), notNullValue());
        assertThat("Order should have metroStation", firstOrder.getMetroStation(), notNullValue());
        assertThat("Order should have phone", firstOrder.getPhone(), notNullValue());
        assertThat("Order should have rentTime", firstOrder.getRentTime(), greaterThan(0));
        assertThat("Order should have deliveryDate", firstOrder.getDeliveryDate(), notNullValue());
        assertThat("Order should have trackNumber", firstOrder.getTrack(), notNullValue());
    }

    @Test
    public void shouldReturnCorrectOrder() {
        orderSteps.getOrdersList()
                .statusCode(200)
                .body("orders[0].id", notNullValue())
                .body("orders[0].firstName", notNullValue())
                .body("orders[0].lastName", notNullValue())
                .body("orders[0].address", notNullValue())
                .body("orders[0].metroStation", notNullValue())
                .body("orders[0].phone", notNullValue())
                .body("orders[0].rentTime", greaterThan(0))
                .body("orders[0].deliveryDate", notNullValue())
                .body("orders[0].track", notNullValue());
    }
}