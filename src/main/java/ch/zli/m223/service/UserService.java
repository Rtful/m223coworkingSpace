package ch.zli.m223.service;

import ch.zli.m223.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserService {
  @Inject
  EntityManager entityManager;
  @Transactional
  public User createUser(User user) {
    entityManager.persist(user);
    return user;
  }

  public List<User> findAll() {
    TypedQuery<User> query = entityManager.createQuery("FROM User", User.class);
    return query.getResultList();
  }

  public Optional<User> findByEmail(String email) {
    return entityManager
      .createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
      .setParameter("email", email)
      .getResultStream()
      .findFirst();
  }

  public User getUser(Long id) {
    return entityManager.find(User.class, id);
  }

  @Transactional
  public void deleteUser(Long id) {
    User user = entityManager.find(User.class, id);
    entityManager.remove(user);
  }

  @Transactional
  public User updateUser(User user) {
    return entityManager.merge(user);
  }
}
