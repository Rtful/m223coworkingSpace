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
  public void testIndexEndpoint() {
    given()
      .when().get("/booking")
      .then()
      .statusCode(200)
      .body(is("[]"));
  }

//  @Test
//  public void testDeleteEndpoint() {
//    var booking = new Booking();
//    booking.setStart(LocalDateTime.of(2022, 11, 23, 12, 0, 0));
//    booking.setEnd(LocalDateTime.of(2022, 11, 23, 18, 0, 0));
//    booking.setWorkstation();
//    booking.setUser(user);
//
//    var createResponse = given()
//      .contentType(ContentType.JSON)
//      .body(booking)
//      .when().post("/entries");
//
//    given()
//      .when().delete("/entries/" + createResponse.jsonPath().get("id"))
//      .then()
//      .statusCode(204);
//  }
//
//  @Test
//  public void testUpdateEndpoint() {
//    var entry = new Entry();
//    entry.setCheckIn(LocalDateTime.now());
//    entry.setCheckOut(LocalDateTime.now());
//
//    var createResponse = given()
//      .contentType(ContentType.JSON)
//      .body(entry)
//      .when().post("/entries");
//
//
//    var updatedEntry = new Entry();
//    updatedEntry.setCheckIn(LocalDateTime.of(2023, 05, 1, 1, 1, 1));
//    updatedEntry.setCheckOut(LocalDateTime.of(2023, 05, 1, 1, 1, 2));
//
//    given()
//      .contentType(ContentType.JSON)
//      .body(updatedEntry)
//      .when().put("/entries/" + createResponse.jsonPath().get("id"))
//      .then()
//      .statusCode(200)
//      .body("checkIn", is("2023-05-01T01:01:01"));
//  }

}
