package br.com.jonasluis.events.exception;

import br.com.jonasluis.events.dto.SubscriptionRankingItem;

public record SubscriptionRankingByUser(SubscriptionRankingItem item, Integer position) {
}
