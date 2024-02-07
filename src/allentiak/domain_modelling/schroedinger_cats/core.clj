(ns allentiak.domain-modelling.schroedinger-cats.core
  (:require [clojure.spec.alpha :as s]))

(s/def :entities/scientist-name
  string?)

(s/def :aggregates/card
  #{:box-card :quantum-card})

(s/def :entities/card-hand
  ;; should this be an aggregate?
  s/coll-of :aggregates/card)

(s/def :aggregates/scientist
  (s/tuple :entities/scientist-name :entities/card-hand))

(s/def :aggregates/participating-scientists
  (s/coll-of :aggregates/scientist))

(s/def :aggregates/first-scientist
  :aggregates/scientist)

(s/fdef commands/select-first-scientist
  :args :aggregates/participating-scientists
  :ret
  ;; should I also return the "first-scientist-selected" and "first-experiment-started" events?
       :aggregates/first-scientist)

(s/fdef events/first-scientist-selected
  :args :aggregates/participating-scientists
  :ret :aggregates/scientist)

(s/fdef events/first-experiment-started
  :args (s/cat :aggregates/first-scientist :aggregates/participating-scientists)
  :ret (s/cat :aggregates/first-scientist :aggregates/participating-scientists))

(s/def :aggregates/unshuffled-deck
  (s/coll-of :aggregates/card))

(s/def :aggregates/shuffled-deck
  (s/coll-of :aggregates/card))

(s/fdef commands/shuffle-deck
  :args :aggregates/unshuffled-deck
  :ret
  ;; should this command also return the "deck-shuffled" event?
       :aggregates/shuffled-deck)

(s/fdef events/deck-shuffled
  :args :entity/unshuffled-deck
  :ret :entity/shuffled-deck)

(s/fdef commands/deal-cards
  :args (s/cat :aggregates/participating-scientists :aggregates/shuffled-deck)
  :ret
  ;; should I also return the "cards-dealt" and "research-deck-created" events?
       (s/cat :aggregates/participating-scientists :aggregates/shuffled-deck))

(s/fdef events/cards-dealt
  :args (s/cat :aggregates/participating-scientists)
  :ret :aggregates/scientist)

(s/fdef events/research-deck-created
  :args (s/cat :aggregates/participating-scientists)
  :ret :aggregates/scientist)
