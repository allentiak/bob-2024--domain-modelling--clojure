(ns allentiak.domain-modelling.schroedinger-cats.entities
  "Notes:
  - At this point, there is no point in differentiating between `entities` and `aggregates`."
  (:require
   [clojure.spec.alpha :as s]))

(s/def :entities/box-card
  #{:dead :alive :empty})

(s/def :entities/quantum-card
  #{:quantum})

(s/def :entities/card
  #{:entities/box-card :entities/quantum-card})

(s/def :entities/research-deck
  (s/coll-of :entities/card))

(s/def :entities/unshuffled-deck
  :entities/research-deck)

(s/def :entities/shuffled-deck
  :entities/research-deck)

(s/def :entities/research
  (s/coll-of :entities/card))

(s/def :player/name
  string?)

(s/def :entities/player
  (s/keys :req-un [:player/name :entities/research]))


(s/def :entities/world
  (s/keys :req-un [(s/coll-of :entities/player)
                   :entities/research-deck]))


(s/def :entities/lab-clipboard-entry
  (s/cat :entities/box-card pos-int?))

(s/def :entities/lab-clipboard
  (s/+ :entities/lab-clipboard-entry))

(s/def :entities/hypothesis
  (s/keys :req-un [:entities/box-card]))
