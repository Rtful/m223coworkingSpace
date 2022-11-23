package ch.zli.m223;

import ch.zli.m223.model.Workstation;
import ch.zli.m223.service.UserService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.gradle.internal.impldep.javax.inject.Inject;
import org.junit.jupiter.api.Test;

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
  public void testCreateWorkstation() {
    Workstation workstation = new Workstation();
    workstation.setFloor(5);
    given()
      .contentType(ContentType.JSON)
      .body(workstation)
      .when().post("/user")
      .then().statusCode(200);
  }

  @Test
  public void testDeleteWorkstation() {
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
  public void testDeleteNonExistentWorkstation() {
    given()
      .when().delete("/user/9483875797")
      .then()
      .statusCode(500);
  }

  @Test
  public void testUpdateWorkstation() {
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

  @Test
  public void testAvailability() {
    given()
      .when().get("/availability/2022-11-23")
      .then()
      .statusCode(200)
      .body(is("true"));
  }
  @Test
  public void testAvailableWorkstations() {
    given()
      .when().get("/availability/2022-11-23")
      .then()
      .statusCode(200);
  }

}
