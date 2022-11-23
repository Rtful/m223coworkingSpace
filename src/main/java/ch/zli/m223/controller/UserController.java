package ch.zli.m223.controller;

import ch.zli.m223.model.User;
import ch.zli.m223.service.UserService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/user")
@Tag(name = "Users", description = "Handling of user")
public class UserController {
    @Inject
    UserService UserService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all users.", description = "Returns a list of all users.")
    @RolesAllowed({"Admin"})
    public List<User> index() {
        return UserService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new user.", description = "Creates a new user and returns the newly added user.")
    @PermitAll
    public User create(User user) {
       return UserService.createUser(user);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deletes a user.", description = "Deletes a user by its id.")
    @RolesAllowed({"Admin"})
    public void delete(@PathParam("id") Long id) {
       UserService.deleteUser(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Updates a user",
        description = "Updates a user"
    )
    @RolesAllowed({"Admin", "Member"})
    public User updateUser(User user) {// TODO: fallunterscheidung admin und user (user = eingeloggt?)
        return UserService.updateUser(user);
    }
}
