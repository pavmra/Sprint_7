package ru.practicum.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.practicum.models.Courier;

import static io.restassured.RestAssured.given;

;

public class CourierSteps {

    private static final String COURIER_CREATE = "/api/v1/courier";
    private static final String COURIER_LOGIN = "/api/v1/courier/login";
    private static final String COURIER_DELETE = "/api/v1/courier/{id}";


    @Step("Создание курьера")
    public ValidatableResponse createCourier(Courier courier){
       return given()
                .log().all()
                .body(courier)
                .when()
                .log().all()
                .post(COURIER_CREATE)
                .then();
    }

    @Step("Логин курьера")
    public ValidatableResponse login(Courier courier) {

        return given()
                .log().all()
                .body(courier)
                .when()
                .log().all()
                .post(COURIER_LOGIN)
                .then();
    }

    @Step("Удаление курьера")
    public ValidatableResponse delete(Courier courier) {
        return given()
                .log().all()
                .pathParams("id", courier.getId())
                .when()
                .log().all()
                .delete(COURIER_DELETE)
                .then();


    }
}
