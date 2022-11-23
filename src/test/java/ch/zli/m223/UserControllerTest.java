package ch.zli.m223;

import ch.zli.m223.model.Booking;
import ch.zli.m223.model.User;
import ch.zli.m223.service.UserService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.apache.http.client.utils.DateUtils;
import org.gradle.internal.impldep.javax.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class UserControllerTest {

  @Inject
  UserService userService;

  @Test
  public void testIndexEndpoint() {
    given()
      .when().get("/user")
      .then()
      .statusCode(200)
      .body(is("[]"));
  }

  @Test
  public void testCreateUser() {
    given()
      .contentType(ContentType.JSON)
      .body("{\"firstname\":\"firstname\",\"lastname\":\"lastname\",\"birthdate\":\"2004-07-12T00:00:00.000+00:00\",\"email\":\"test@test.test\",\"gender\":\"m\",\"password\":\"1234\",\"role\":{\"id\":1}}")
      .when().post("/user")
      .then().statusCode(200);
  }

  @Test
  public void testCreateInvalidUser() {
    given()
      .contentType(ContentType.JSON)
      .body("{\"sdf\":\"firstname\",\"lastname\":\"lastname\",\"birthdate\":\"2004-07-12T00:00:00.000+00:00\",\"email\":\"test@test.test\",\"gender\":\"m\",\"password\":\"1234\",\"role\":{\"id\":1}}")
      .when().post("/user")
      .then().statusCode(500);
  }

  @Test
  public void testDeleteUser() {
    var createResponse = given()
      .contentType(ContentType.JSON)
      .body("{\"firstname\":\"firstname\",\"lastname\":\"lastname\",\"birthdate\":\"2004-07-12T00:00:00.000+00:00\",\"email\":\"test@test.test\",\"gender\":\"m\",\"password\":\"1234\",\"role\":{\"id\":1}}")
      .when().post("/user");

    given()
      .when().delete("/user/" + createResponse.jsonPath().get("id"))
      .then()
      .statusCode(204);
  }

  @Test
  public void testDeleteNonExistentUser() {
    given()
      .when().delete("/user/98437259469874")
      .then()
      .statusCode(500);
  }

  @Test
  public void testUpdateUser() {
    var createResponse = given()
      .contentType(ContentType.JSON)
      .body("{\"firstname\":\"firstname\",\"lastname\":\"lastname\",\"birthdate\":\"2004-07-12T00:00:00.000+00:00\",\"email\":\"test@test.test\",\"gender\":\"m\",\"password\":\"1234\",\"role\":{\"id\":1}}")
      .when().post("/user");

    given()
      .contentType(ContentType.JSON)
      .body("{\"firstname\":\"firstName\",\"lastname\":\"lastName\",\"birthdate\":\"2000-07-12T00:00:00.000+00:00\",\"email\":\"test1@test.test\",\"gender\":\"f\",\"password\":\"43321\",\"role\":{\"id\":2}}")
      .when().put("/entries/" + createResponse.jsonPath().get("id"))
      .then()
      .statusCode(200)
      .body("checkIn", is("2023-05-01T01:01:01"));
  }

  @Test
  public void testUpdateInvalidUser() {
    var createResponse = given()
      .contentType(ContentType.JSON)
      .body("{\"firstname\":\"firstname\",\"lastname\":\"lastname\",\"birthdate\":\"2004-07-12T00:00:00.000+00:00\",\"email\":\"test@test.test\",\"gender\":\"m\",\"password\":\"1234\",\"role\":{\"id\":1}}")
      .when().post("/user");

    given()
      .contentType(ContentType.JSON)
      .body("{\"firstname\":\"firstName\",\"lastname\":\"lastName\",\"birthdate\":\"2000-07-12T0000:00:00.000+00:00\",\"email\":\"test1@test.test\",\"gender\":\"uzegwf\",\"password\":\"dsafra\",\"role\":{\"id\":2}}")
      .when().put("/entries/" + createResponse.jsonPath().get("id"))
      .then()
      .statusCode(500)
      .body("checkIn", is("2023-05-01T01:01:01"));
  }

}
