package ch.zli.m223;

import ch.zli.m223.model.Booking;
import ch.zli.m223.model.User;
import ch.zli.m223.service.UserService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.gradle.internal.impldep.javax.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class BookingControllerTest {

  @Inject
  UserService userService;

  @Test
  public void testIndexBooking() {
    given()
      .when().get("/booking")
      .then()
      .statusCode(200)
      .body(is("[]"));
  }

  @Test
  public void testCreateBooking() {
    given()
      .contentType(ContentType.JSON)
      .body("{\"start\":\"2022-07-12T12:00:00.000\",\"end\":\"2022-07-12T18:00:00.000\",\"user\":{\"id\":1},\"workstation\":{\"id\":1}}")
      .when().post("/user")
      .then().statusCode(200);
  }

  @Test
  public void testCreateInvalidBooking() {
    given()
      .contentType(ContentType.JSON)
      .body("{\"start\":\"2022-07-12T12:00:00.sdfe000\",\"end\":\"2022-07-12T18:00:00.000\",\"user\":{\"id\":1},\"workstation\":{\"id\":1}}")
      .when().post("/user")
      .then().statusCode(500);
  }

  @Test
  public void testDeleteBooking() {
    var createResponse = given()
      .contentType(ContentType.JSON)
      .body("{\"start\":\"2022-07-12T12:00:00.000\",\"end\":\"2022-07-12T18:00:00.000\",\"user\":{\"id\":1},\"workstation\":{\"id\":1}}")
      .when().post("/entries");

    given()
      .when().delete("/entries/" + createResponse.jsonPath().get("id"))
      .then()
      .statusCode(204);
  }

}
