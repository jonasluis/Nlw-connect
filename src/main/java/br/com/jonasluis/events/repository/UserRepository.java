package br.com.jonasluis.events.repository;

import br.com.jonasluis.events.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
  public User findByEmail(String email);
}
