(ns allentiak.domain-modelling.schroedinger-cats.commands
  "Notes:
  - At this point, we are focusing on modeling the `entities` and `commands`.
  - At this point, since we are focusing on only one bounded context (this is, the actual game logic), the events themselves don't need to be modeled as functions, nor they need to be passed as parameters.
  - Notwithstanding the above point, it is important to know when game `events` happen: for now, they are used as test names."
  (:require
   [allentiak.domain-modelling.schroedinger-cats.builders :as builders]
   [allentiak.domain-modelling.schroedinger-cats.entities :refer :all]
   [clojure.spec.alpha :as s]
   [clojure.spec.gen.alpha :as g]))

(s/fdef shuffle-deck
  :args :entities/unshuffled-deck
  :ret :entities/shuffled-deck)

(defn shuffle-deck
  [unshuffled-deck]
  (shuffle unshuffled-deck))

(s/fdef deal-cards
  :args :entities/world
  :ret :entities/world)

(defn deal-cards
  "given a world with a deck and a list of n players, deals n cards to each of those players (all in a row - not round robin)"
  [{:keys [research-deck players] :as world}]
  (let [amount-of-players (count players)
        new-research-deck (drop (* amount-of-players amount-of-players) research-deck)
        cards-to-deal (take (* amount-of-players amount-of-players) research-deck)
        researches (partition amount-of-players cards-to-deal)
        new-players (map builders/make-player players researches)]
    {:research-deck new-research-deck
     :players new-players}))

(s/fdef state-hypothesis
 :args (s/coll-of :entities/players)
 :ret (s/coll-of :entities/players))

(defn state-hypothesis
  [players]
  players)
