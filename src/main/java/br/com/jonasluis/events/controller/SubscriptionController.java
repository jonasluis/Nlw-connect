package br.com.jonasluis.events.controller;

import br.com.jonasluis.events.dto.ErrorMessage;
import br.com.jonasluis.events.dto.SubscriptionResponse;
import br.com.jonasluis.events.exception.EventNotFoundException;
import br.com.jonasluis.events.exception.SubscripitionConclictException;
import br.com.jonasluis.events.exception.UserIndicadorNotFoundException;
import br.com.jonasluis.events.model.Subscription;
import br.com.jonasluis.events.model.User;
import br.com.jonasluis.events.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubscriptionController {
  @Autowired
  private SubscriptionService service;

  @PostMapping({"/subscription/{prettyName}","/subscription/{prettyName}/{userId}" })
  public ResponseEntity<?> createSubscription(@PathVariable String prettyName,
                                              @RequestBody User subscriber,
                                              @PathVariable(required = false) Integer userId) {
    try {
      SubscriptionResponse res = service.createNewSubscription(prettyName, subscriber, userId);
      if (res != null) {
        return ResponseEntity.ok(res);
      }
     } catch (EventNotFoundException ex) {
      return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
    } catch (SubscripitionConclictException ex){
      return ResponseEntity.status(409).body(new ErrorMessage(ex.getMessage()));
    } catch (UserIndicadorNotFoundException ex){
      return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
    }
      return ResponseEntity.badRequest().build();
  }

  @GetMapping({"/subscription/{prettyName}/ranking"})
  public ResponseEntity<?> generateRankingByEvent(@PathVariable String prettyName){
    try {
      return ResponseEntity.ok(service.getCompleteRanking(prettyName).subList(0, 3));
    }catch (EventNotFoundException e){
      return ResponseEntity.status(404).body(new ErrorMessage((e.getMessage())));
    }
  }

  @GetMapping({"/subscription/{prettyName}/ranking/{userId}"})
  public ResponseEntity<?> generateRankingByEventAndUser(@PathVariable String prettyName,
                                                         @PathVariable Integer userId){
    try {
      return ResponseEntity.ok(service.getRankingByUser(prettyName, userId));
    }catch (Exception ex){
      return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
    }
  }

}
