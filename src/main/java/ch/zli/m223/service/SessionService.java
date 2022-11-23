package ch.zli.m223.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import ch.zli.m223.model.Credential;
import ch.zli.m223.model.Role;
import ch.zli.m223.model.User;
import io.smallrye.jwt.build.Jwt;

@ApplicationScoped
public class SessionService {

  @Inject
  UserService userService;

  public Response authenticate(Credential credential) {
    Optional<User> user = userService.findByEmail(credential.getEmail());

    try {
      if (user.isPresent() && user.get().getPassword().equals(credential.getPassword())) {
        String          role   = user.get().getRole().getName();
        HashSet<String> rights = new HashSet<>(List.of(role));
        String token = Jwt
          .issuer("https://zli.example.com/")
          .upn(credential.getEmail())
          .groups(rights)
          .expiresIn(Duration.ofHours(24))
          .claim("user_id", user.get().getId())
          .sign();
        return Response
          .ok(user.get())
          .cookie(new NewCookie("coworkingSpace", token))
          .header("Authorization", "Bearer " + token)
          .build();
      }
    } catch (Exception e) {
      System.err.println("Couldn't validate password.");
    }

    return Response.status(Response.Status.FORBIDDEN).build();
  }
}
