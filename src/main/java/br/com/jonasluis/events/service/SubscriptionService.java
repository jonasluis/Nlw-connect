package br.com.jonasluis.events.service;

import br.com.jonasluis.events.exception.EventNotFoundException;
import br.com.jonasluis.events.model.Event;
import br.com.jonasluis.events.model.Subscription;
import br.com.jonasluis.events.model.User;
import br.com.jonasluis.events.repository.EventRepository;
import br.com.jonasluis.events.repository.SubscriptionRepository;
import br.com.jonasluis.events.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

  @Autowired
  private EventRepository eventRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private SubscriptionRepository subscriptionRepository;

  public Subscription createNewSubscription(String eventName, User user){
    //recuperar o evento pelo nome
    Event event = eventRepository.findByPrettyName(eventName);
    if (event == null){
      throw  new EventNotFoundException("Evento " + eventName + " não existe");
    }
    User userRec = userRepository.findByEmail(user.getEmail());
    if (userRec == null){
      userRec = userRepository.save(user);
    }

    Subscription subscription = new Subscription();
    subscription.setEvent(event);
    subscription.setSubscriber(userRec);

    Subscription res = subscriptionRepository.save(subscription);

    return res;
  }
}
