package br.com.jonasluis.events.repository;

import br.com.jonasluis.events.model.Subscription;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {

}
