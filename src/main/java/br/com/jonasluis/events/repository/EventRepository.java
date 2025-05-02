package br.com.jonasluis.events.repository;

import br.com.jonasluis.events.model.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Integer> {
  public Event findByPrettyName(String prettyName);
}
