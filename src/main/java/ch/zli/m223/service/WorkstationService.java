package ch.zli.m223.service;

import ch.zli.m223.model.Workstation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@ApplicationScoped
public class WorkstationService {
  @Inject
  EntityManager entityManager;
  @Transactional
  public Workstation createWorkspace(Workstation workstation) {
    entityManager.persist(workstation);
    return workstation;
  }

  public List<Workstation> findAll() {
    TypedQuery<Workstation> query = entityManager.createQuery("FROM Workstation", Workstation.class);
    return query.getResultList();
  }

  public Workstation getWorkstation(Long id) {
    return entityManager.find(Workstation.class, id);
  }

  @Transactional
  public void deleteWorkstation(Long id) {
    Workstation workstation = entityManager.find(Workstation.class, id);
    entityManager.remove(workstation);
  }

  @Transactional
  public Workstation updateWorkstation(Workstation workstation) {
    return entityManager.merge(workstation);
  }
  public List<Workstation> getAvailableWorkstations(LocalDate date) {
    LocalDateTime startOfDay = date.atStartOfDay();
    LocalDateTime endOfDay   = date.atTime(LocalTime.MAX);
    List<Workstation> workstations = entityManager
      .createQuery("SELECT " +
        "w " +
        "FROM Workstation w " +
        "LEFT JOIN Booking b ON b.workstation = w.id " +
        "WHERE b IS NULL " +
        "OR (b.cancellation IS NULL " +
        "AND b.start BETWEEN :startOfDay AND :endOfDay " +
        "AND b.end BETWEEN :startOfDay AND :endOfDay)", Workstation.class)
      .setParameter("startOfDay", startOfDay)
      .setParameter("endOfDay", endOfDay)
      .getResultList();
    return workstations;
  }

}
