package br.com.jonasluis.events.service;

import br.com.jonasluis.events.model.Event;
import br.com.jonasluis.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class EventService {
  @Autowired
  private EventRepository eventRepository;

  public Event addNewEvent(Event event){
    // gerando o preety name
    event.setPrettyName(event.getPrettyName()
            .toLowerCase(Locale.ROOT)
            .replaceAll(" ", "-"));
    return eventRepository.save(event);
  }

  public List<Event> getAllEvents(){
    return (List<Event>) eventRepository.findAll();
  }

  public Event getByPrettyName(String prettyName){
    return eventRepository.findByPrettyName(prettyName);
  }



}
