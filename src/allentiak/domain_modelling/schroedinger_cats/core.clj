(ns allentiak.domain-modelling.schroedinger-cats.core
  "Notes:
  - At this point, we are focusing on modeling the `entities` and `commands`.
  - At this point, there is no point in differentiating between `entities` and `aggregates`.
  - At this point, since we are focusing on only one bounded context (this is, the actual game logic), the events themselves don't need to be modeled as functions, nor they need to be passed as parameters.
  - Notwithstanding the above point, it is important to know when game `events` happen: since we are modeling everything in one file, they are being added as comments."
  (:require [clojure.spec.alpha :as s]))

(s/def :entities/scientist-name
  string?)

(s/def :entities/card
  #{:box-card :quantum-card})

(s/def :entities/card-hand
  s/coll-of :entities/card)

(s/def :entities/scientist
  (s/tuple :entities/scientist-name :entities/card-hand :last-date-of-documentary-watch))

(s/def :entities/participating-scientists
  (s/coll-of :entities/scientist))

(s/def :entities/first-scientist
  :entities/scientist)

(s/fdef commands/select-first-scientist
  :args :entities/participating-scientists
  :ret :entities/first-scientist)

;; events/first-scientist-selected

;; events/first-experiment-started

(s/def :entities/unshuffled-deck
  (s/coll-of :entities/card))

(s/def :entities/shuffled-deck
  (s/coll-of :entities/card))

(s/fdef commands/shuffle-deck
  :args :entities/unshuffled-deck
  :ret :entities/shuffled-deck)

;; events/deck-shuffled

(s/fdef commands/deal-cards
  :args (s/cat :entities/participating-scientists :entities/shuffled-deck)
  :ret (s/cat :entities/participating-scientists :entities/shuffled-deck))

;; events/cards-dealt

;; events/research-deck-created
