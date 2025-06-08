package ru.practicum.steps;

import io.restassured.response.ValidatableResponse;
import ru.practicum.models.Order;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class OrderSteps {


    @Step("Создание заказа")
    public ValidatableResponse createOrder(Order order) {
        return given()
                .log().all()
                .body(order)
                .when()
                .post("/api/v1/orders")
                .then()
                .log().all();
    }

    @Step("Получение списка заказов")
    public ValidatableResponse getOrdersList() {
        return given()
                .log().all()
                .when()
                .get("/api/v1/orders")
                .then()
                .log().all();
    }

}
