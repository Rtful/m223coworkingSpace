package ch.zli.m223.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import ch.zli.m223.model.Booking;
import ch.zli.m223.model.Workstation;

@ApplicationScoped
public class BookingService {
  @Inject
  EntityManager entityManager;

  @Transactional
  public Booking createBooking(Booking booking) {
    entityManager.persist(booking);
    return booking;
  }

  public List<Booking> findAll() {
    TypedQuery<Booking> query = entityManager.createQuery("FROM Booking", Booking.class);
    return query.getResultList();
  }

  public Booking getBooking(Long id) {
    return entityManager.find(Booking.class, id);
  }

  @Transactional
  public void deleteBooking(Long id) {
    Booking booking = entityManager.find(Booking.class, id);
    entityManager.remove(booking);
  }

  @Transactional
  public Booking updateBooking(Booking booking) {
    return entityManager.merge(booking);
  }
}
