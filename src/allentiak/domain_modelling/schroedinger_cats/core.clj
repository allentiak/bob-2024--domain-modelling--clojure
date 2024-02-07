(ns allentiak.domain-modelling.schroedinger-cats.core
  (:require [clojure.spec.alpha :as s]))

(s/def :aggregates/scientist
  string?)

(s/fdef first-scientist-selected
  :args (s/cat (s/coll-of :aggregates/scientist))
  :ret :aggregates/scientist)

(s/fdef first-experiment-started
  :args :aggregates/scientist
  :ret :aggregates/scientist)

(s/fdef deck-shuffled
  :args :entity/unshuffled-deck
  :ret :entity/shuffled-deck)

(s/fdef cards-dealt
  :args (s/cat (s/coll-of :aggregates/scientist))
  :ret :aggregates/scientist)

(s/fdef research-deck-created
  :args (s/cat (s/coll-of :aggregates/scientist))
  :ret :aggregates/scientist)
