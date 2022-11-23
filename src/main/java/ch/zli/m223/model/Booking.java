package ch.zli.m223.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;

@Entity
@Table(name = "Booking")
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Long id;

  @Column(nullable = false)
  private LocalDateTime start;

  @Column(nullable = false)
  private LocalDateTime end;

  @Column(nullable = true)
  private LocalDateTime cancellation;

  @ManyToOne(optional = false)
  @JsonIgnore
  @Fetch(FetchMode.JOIN)
  private Workstation workstation;

  @ManyToOne(optional = false)
  @JsonIgnore
  @Fetch(FetchMode.JOIN)
  private User user;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getStart() {
    return start;
  }

  public void setStart(LocalDateTime start) {
    this.start = start;
  }

  public LocalDateTime getEnd() {
    return end;
  }

  public void setEnd(LocalDateTime end) {
    this.end = end;
  }

  public LocalDateTime getCancellation() {
    return cancellation;
  }

  public void setCancellation(LocalDateTime cancellation) {
    this.cancellation = cancellation;
  }

  public Workstation getWorkstation() {
    return workstation;
  }

  public void setWorkstation(Workstation workstation) {
    this.workstation = workstation;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
