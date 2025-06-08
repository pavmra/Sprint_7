package ru.practicum.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import ru.practicum.models.Courier;

import static io.restassured.RestAssured.given;


public class CourierSteps {

    @Step("Создание курьера")
    public ValidatableResponse createCourier(Courier courier){
       return given()
                .log().all()
                .body(courier)
                .when()
                .log().all()
                .post("/api/v1/courier")
                .then();
    }

    @Step("Логин курьера")
    public ValidatableResponse login(Courier courier) {

        return given()
                .log().all()
                .body(courier)
                .when()
                .log().all()
                .post("/api/v1/courier/login")
                .then();
    }

    @Step("Удаление курьера")
    public ValidatableResponse delete(Courier courier) {
        return given()
                .log().all()
                .pathParams("id", courier.getId())
                .when()
                .log().all()
                .delete("/api/v1/courier/{id}")
                .then();


    }
}
