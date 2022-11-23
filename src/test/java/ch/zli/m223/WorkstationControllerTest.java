package ch.zli.m223;

import ch.zli.m223.model.Booking;
import ch.zli.m223.model.User;
import ch.zli.m223.model.Workstation;
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
public class WorkstationControllerTest {

  @Inject
  UserService userService;

  @Test
  public void testIndexEndpoint() {
    given()
      .when().get("/workstation")
      .then()
      .statusCode(200)
      .body(is("[]"));
  }

  @Test
  public void testCreateEndPoint() {
    Workstation workstation = new Workstation();
    workstation.setFloor(5);
    given()
      .contentType(ContentType.JSON)
      .body(workstation)
      .when().post("/user")
      .then().statusCode(200);
  }

  @Test
  public void testDeleteEndpoint() {
    Workstation workstation = new Workstation();
    workstation.setFloor(5);
    var createResponse = given()
      .contentType(ContentType.JSON)
      .body(workstation)
      .when().post("/user");

    given()
      .when().delete("/user/" + createResponse.jsonPath().get("id"))
      .then()
      .statusCode(204);
  }

  @Test
  public void testUpdateEndpoint() {
    Workstation workstation = new Workstation();
    workstation.setFloor(5);
    var createResponse = given()
      .contentType(ContentType.JSON)
      .body(workstation)
      .when().post("/user");

    Workstation updatedWorkstation = new Workstation();
    workstation.setFloor(6);

    given()
      .contentType(ContentType.JSON)
      .body(updatedWorkstation)
      .when().put("/entries/" + createResponse.jsonPath().get("id"))
      .then()
      .statusCode(200)
      .body("checkIn", is("2023-05-01T01:01:01"));
  }

}
