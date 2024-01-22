(ns allentiak.domain-modelling.spec.try-2nd
  (:require [clojure.spec.alpha :as s]))

;; Enforcing Invariants: Second try

(s/def :tennis/points1
  #{:love :fifteen :thirty :forty})

(s/def :tennis/player-one2 :tennis/points1)
(s/def :tennis/player-two2 :tennis/points1)

(s/def :tennis/score2
  (s/keys :req [:tennis/player-one2
                :tennis/player-two2]))

(def invalid-score1-2
  {:tennis/player-one2 1000
   :tennis/player-two2 -15})

(comment
  (s/valid? :tennis/score2 invalid-score1-2)
  ;; => false
  (s/explain :tennis/score2 invalid-score1-2)
  ;; for some strange reason, I get nil.
  ;; I should get the explanation...
  ,)

(def valid-score1
  {:tennis/player-one2 :fifteen
   :tennis/player-two2 :love})

(def invalid-score2 {:tennis/player-one2 :forty
                     :tennis/player-two2 :forty})

(comment
  (s/valid? :tennis/score2 valid-score1)
  ;; => true
  (s/valid? :tennis/score2 invalid-score2)
  ;; => true
  ,)
