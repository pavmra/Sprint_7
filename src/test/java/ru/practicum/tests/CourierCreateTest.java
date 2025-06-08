package ru.practicum.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.practicum.models.Courier;
import ru.practicum.steps.CourierSteps;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class CourierCreateTest extends BaseTest {

    private Courier courier;
    private CourierSteps couriersSteps;
    private Courier lastCreatedCourier;

    @Before
    public void setUp(){
        couriersSteps = new CourierSteps();
        courier = new Courier()
                .withLogin(RandomStringUtils.randomAlphabetic(10))
                .withPassword(RandomStringUtils.randomAlphabetic(10))
                .withFirstName(RandomStringUtils.randomAlphabetic(10));

    }

    @Test
    public  void shouldCreateCourierTest(){

                couriersSteps.createCourier(courier)
                .statusCode(201)
                .body("ok", is(true));
                lastCreatedCourier = courier;
    }


    @Test        //Баг!!!
    public  void shouldNotCreateTwinsCourierTest(){
            couriersSteps.createCourier(courier)
                .statusCode(201)
                .body("ok", is(true));
            lastCreatedCourier = courier;
            couriersSteps.createCourier(courier)
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется"));
    }


    @Test
    public void createCourierWithoutLogin() {
        Courier courierWithoutLogin = new Courier()
                .withPassword("password123")
                .withFirstName("name");
        couriersSteps.createCourier(courierWithoutLogin)
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }


    @Test
    public void createCourierWithoutPassword() {
        Courier courierWithoutPassword = new Courier();
        courierWithoutPassword.withLogin("login" + RandomStringUtils.randomAlphabetic(5));
        courierWithoutPassword.withFirstName("name");
        couriersSteps.createCourier(courierWithoutPassword)
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }


    @Test
    public void createCourierWithoutFirstName() {
        Courier courierWithoutFirstName = new Courier();
        courierWithoutFirstName.withLogin("login" + RandomStringUtils.randomAlphabetic(5));
        courierWithoutFirstName.withPassword("password123");
        couriersSteps.createCourier(courierWithoutFirstName)
                .statusCode(201)
                .body("ok", is(true));
        lastCreatedCourier = courierWithoutFirstName;
    }


    @After
    public void tearDown() {
        try {
                    if (lastCreatedCourier != null &&
                    lastCreatedCourier.getLogin() != null &&
                    lastCreatedCourier.getPassword() != null) {
                Integer id = couriersSteps.login(lastCreatedCourier)
                        .statusCode(200)
                        .extract().body().path("id");
                if (id != null) {
                    couriersSteps.delete(lastCreatedCourier.withId(id))
                            .statusCode(200);
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка при удалении тестового курьера: " + e.getMessage());
        }
    }
}




