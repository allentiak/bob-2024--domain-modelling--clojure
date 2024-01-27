(ns allentiak.domain-modelling.tennis.spec.try-2nd
  (:require [clojure.spec.alpha :as s]))

;; Enforcing Invariants: Second try

(s/def :tennis/points
  #{:love :fifteen :thirty :forty})

(s/def :tennis/player-one :tennis/points)
(s/def :tennis/player-two :tennis/points)

(s/def :tennis/score
  (s/keys :req [:tennis/player-one
                :tennis/player-two]))

;; First Example, revisited
(def invalid-score-1
  {:tennis/player-one 1000
   :tennis/player-two -15})

(comment
  (s/valid? :tennis/score invalid-score-1)
  ;; => false
  (s/explain :tennis/score invalid-score-1)
  ;; for some strange reason, I get nil.
  ;; I should get the explanation...
  ,)

(def valid-score1
  {:tennis/player-one :fifteen
   :tennis/player-two :love})

(def invalid-score2
  {:tennis/player-one :forty
   :tennis/player-two :forty})

(comment
  (s/valid? :tennis/score valid-score1)
  ;; => true
  (s/valid? :tennis/score invalid-score2)
  ;; => true
  ,)
