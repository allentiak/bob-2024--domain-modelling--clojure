(ns allentiak.domain-modelling.schroedinger-cats.events-test
  (:require
   [allentiak.domain-modelling.schroedinger-cats.builders :as builders]
   [allentiak.domain-modelling.schroedinger-cats.commands :as commands]
   [allentiak.domain-modelling.schroedinger-cats.entities :refer :all]
   [clojure.test :refer [use-fixtures]]
   [expectations.clojure.test :refer [defexpect expect expecting]]
   [clojure.spec.alpha :as s]))

(defexpect deck-shuffled-event-should
  (expecting "a shuffled deck should be different from the original deck"
    (let [original-deck (builders/make-random-deck 9)
          shuffled-deck (commands/shuffle-deck original-deck)]
      (expect (not= original-deck
                    shuffled-deck))
      (expect (= (count original-deck)
                 (count shuffled-deck))))))

(defexpect cards-dealt-event-should
  (expecting "when cards have been dealt, each player should have the corresponding amount of cards, and those cards should not be in the deck"
    (let [original-deck (builders/make-random-deck 16)
          shuffled-deck (commands/shuffle-deck original-deck)
          players (list (builders/make-player "p1") (builders/make-player "p2"))
          original-world (builders/make-world players shuffled-deck)
          new-world (commands/deal-cards original-world)
          new-deck-size (count (:research-deck new-world))
          original-deck-size (count original-deck)
          quantity-of-dealt-cards (reduce + (map
                                              #(count (:research %))
                                              (:players new-world)))]
      (expect (not= original-world
                    new-world))
      (expect (= original-deck-size
                 (+ new-deck-size
                    quantity-of-dealt-cards))))))

(defexpect hypothesis-stated-event-should
  (expecting "when the first scientist states his or her hypothesis, it should be registered in the game"
    (let [players (list (builders/make-player "p1") (builders/make-player "p2"))
          new-players (commands/state-hypothesis players)]
      (expect (s/valid? :entities/hypothesis (:hypothesis (first players)))))))
