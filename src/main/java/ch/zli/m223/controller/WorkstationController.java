package ch.zli.m223.controller;

import ch.zli.m223.model.Workstation;
import ch.zli.m223.service.WorkstationService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Path("/workstation")
@Tag(name = "Workstations", description = "Handling of workstation")
public class WorkstationController {
  @Inject
  WorkstationService workstationService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(summary = "Index all workstations.", description = "Returns a list of all workstations.")
  @RolesAllowed({"Admin"})
  public List<Workstation> index() {
    return workstationService.findAll();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(summary = "Creates a new workstation.", description = "Creates a new workstation and returns the newly added workstation.")
  @RolesAllowed({"Admin"})
  public Workstation create(Workstation workstation) {
    return workstationService.createWorkspace(workstation);
  }

  @DELETE
  @Path("/{id}")
  @Operation(summary = "Deletes a workstation.", description = "Deletes a workstation by its id.")
  @RolesAllowed({"Admin"})
  public void delete(@PathParam("id") Long id) {
    workstationService.deleteWorkstation(id);
  }

  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(
    summary = "Updates a workstation",
    description = "Updates a workstation"
  )
  @RolesAllowed({"Admin"})
  public Workstation updateWorkstation(Workstation workstation) {
    return workstationService.updateWorkstation(workstation);
  }

  @GET
  @Path("/available/{date}")
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(summary = "Shows available workstations.", description = "Returns all workstations available at that date")
  @RolesAllowed("Admin")
  public List<Workstation> getAvailableWorkstations(@PathParam("date") String dateString) {
    LocalDate date = LocalDate.parse(dateString);
    return workstationService.getAvailableWorkstations(date);
  }

  @GET
  @Path("/availability/{date}")//TODO: make this work
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(summary = "Shows availability.", description = "Returns boolean of whether there is at least one workstation available at that date")
  @PermitAll
  public boolean isAvailable(@PathParam("date") String dateString) {
    LocalDate date = LocalDate.parse(dateString);
    List<Workstation> workstations = workstationService.getAvailableWorkstations(date);
    return workstations.size() > 0;
  }


}
