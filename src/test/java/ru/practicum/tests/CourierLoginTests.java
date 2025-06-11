package ru.practicum.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.practicum.models.Courier;
import ru.practicum.steps.CourierSteps;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;

public class CourierLoginTests extends BaseTest {

    private Courier courier;
    private CourierSteps courierSteps;
    private Integer courierId;

    @Before
    public void setUp() {
        courierSteps = new CourierSteps();
                courier = new Courier()
                .withLogin(RandomStringUtils.randomAlphabetic(10))
                .withPassword(RandomStringUtils.randomAlphabetic(10))
                .withFirstName(RandomStringUtils.randomAlphabetic(10));
        courierSteps.createCourier(courier)
                .statusCode(201);
        courierId = courierSteps.login(courier)
                .statusCode(200)
                .extract().path("id");
    }


    @Test
    public void shouldLoginCourierTest() {
                courierId = courierSteps.login(courier)
                .statusCode(200)
                .extract().path("id");
        assertNotNull("ID курьера не должен быть null", courierId);
    }


    @Test
    public void shouldNotLoginWithWrongDataTest() {
        Courier wrongCourier = new Courier()
                .withLogin("nonexistent_login")
                .withPassword("nonexistent_password");

        courierSteps.login(wrongCourier)
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }


    @Test
    public void shouldNotLoginWithWrongPasswordTest() {
                Courier wrongPasswordCourier = new Courier()
                .withLogin(courier.getLogin())
                .withPassword("wrong_password");
        courierSteps.login(wrongPasswordCourier)
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
        courierId = courierSteps.login(courier).extract().path("id");
    }



    @Test
    public void shouldNotLoginWithoutLoginPoleTest() {
        Courier courierWithoutLogin = new Courier()
                .withPassword(courier.getPassword());
        courierSteps.login(courierWithoutLogin)
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test         //Баг!!!
    public void shouldNotLoginWithoutPasswordPoleTest() {
        courierId = courierSteps.login(courier)
                .statusCode(200)
                .extract().path("id");
        Courier courierWithoutPassword = new Courier()
                .withLogin(courier.getLogin());
        courierSteps.login(courierWithoutPassword)
                .statusCode(400)  // Ожидаем 400, но сервер может возвращать 504 при проблемах
                .body("message", equalTo("Недостаточно данных для входа"));
    }


    @Test
    public void goodLoginReturnsIdTest() {
        courierId = courierSteps.login(courier)
                .statusCode(200)
                .body("id", Matchers.notNullValue())
                .extract().path("id");
    }


    @After
    public void tearDown() {
        if (courierId != null) {
            courierSteps.delete(new Courier().withId(courierId))
                    .statusCode(200);
        }
    }
}