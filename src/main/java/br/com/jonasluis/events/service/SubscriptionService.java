package br.com.jonasluis.events.service;

import br.com.jonasluis.events.dto.SubscriptionRankingItem;
import br.com.jonasluis.events.dto.SubscriptionResponse;
import br.com.jonasluis.events.exception.EventNotFoundException;
import br.com.jonasluis.events.exception.SubscripitionConclictException;
import br.com.jonasluis.events.exception.UserIndicadorNotFoundException;
import br.com.jonasluis.events.model.Event;
import br.com.jonasluis.events.model.Subscription;
import br.com.jonasluis.events.model.User;
import br.com.jonasluis.events.repository.EventRepository;
import br.com.jonasluis.events.repository.SubscriptionRepository;
import br.com.jonasluis.events.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {

  @Autowired
  private EventRepository eventRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private SubscriptionRepository subscriptionRepository;

  public SubscriptionResponse createNewSubscription(String eventName, User user, Integer userId ){
    //recuperar o evento pelo nome
    Event event = eventRepository.findByPrettyName(eventName);
    if (event == null){
      throw  new EventNotFoundException("Evento " + eventName + " não existe");
    }
    User userRec = userRepository.findByEmail(user.getEmail());
    if (userRec == null){
      userRec = userRepository.save(user);
    }
    User indicador = null;
    if (userId != null){
      indicador = userRepository.findById(userId).orElse(null);
      if(indicador == null){
        throw  new UserIndicadorNotFoundException("Usuario"+userId+" indicador não existe");
      }
    }
    Subscription subscription = new Subscription();
    subscription.setEvent(event);
    subscription.setSubscriber(userRec);
    subscription.setIndication(indicador);


    Subscription tmpSub = subscriptionRepository.findByEventAndSubscriber(event, userRec);
    if (tmpSub != null){
      throw new SubscripitionConclictException("Ja existe inscrição para o usuário "
              + userRec.getName() + " no evento "
              + eventName);
    }


    Subscription res = subscriptionRepository.save(subscription);

    return new SubscriptionResponse(res.getSubscriptionNumber(), "http://codecraft.com/subscription"+ res.getEvent().getPrettyName()+"/"+ res.getSubscriber().getId());
  }

  public List<SubscriptionRankingItem> getCompleteRanking(String prettyName){
    Event event = eventRepository.findByPrettyName(prettyName);
    if (event == null) {
      throw new EventNotFoundException("Ranking do evento " + prettyName + " não existe!");

    }
    return  subscriptionRepository.generateRanking(event.getEventId());
  }
}
