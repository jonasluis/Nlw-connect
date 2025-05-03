package br.com.jonasluis.events.repository;

import br.com.jonasluis.events.model.Event;
import br.com.jonasluis.events.model.Subscription;
import br.com.jonasluis.events.model.User;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
  public Subscription findByEventAndSubscriber(Event evt, User user);
}
