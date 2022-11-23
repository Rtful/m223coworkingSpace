package ch.zli.m223.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.zli.m223.model.Workstation;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.model.Booking;
import ch.zli.m223.service.BookingService;

@Path("/booking")
@Tag(name = "Bookings", description = "Handling of bookings")
public class BookingController {

  @Inject
  BookingService bookingService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(summary = "Index all bookings.", description = "Returns a list of all bookings.")
  @RolesAllowed({"Admin"})
  public List<Booking> index() {
    return bookingService.findAll();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @RolesAllowed({"Admin", "Member"})
  @Operation(summary = "Creates a new booking.", description = "Creates a new booking and returns the newly added booking.")
  public Booking create(Booking booking) {
    return bookingService.createBooking(booking);
  }

  @DELETE
  @Path("/{id}")
  @RolesAllowed({"Admin"})
  @Operation(summary = "Deletes a booking.", description = "Deletes an booking by its id.")
  public void delete(@PathParam("id") Long id) {
    bookingService.deleteBooking(id);
  }

  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @RolesAllowed({"Admin", "Member"})
  @Operation(
    summary = "Updates an booking",
    description = "Updates an booking"
  )
  public Booking updateBooking(Booking booking) { //TODO: fallunterscheidung
    return bookingService.updateBooking(booking);
  }
}
