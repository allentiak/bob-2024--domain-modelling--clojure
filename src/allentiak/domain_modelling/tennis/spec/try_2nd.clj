(ns allentiak.domain-modelling.tennis.spec.try-2nd
  (:require [clojure.spec.alpha :as s]))

;; Invariant Enforcement: Second try

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
  ;; nil
  ;; 1000 - failed: #{:forty :thirty :fifteen :love} in: [:tennis/player-one] at: [:tennis/player-one] spec: :tennis/points
  ;; -15 - failed: #{:forty :thirty :fifteen :love} in: [:tennis/player-two] at: [:tennis/player-two] spec: :tennis/points
  ,)

(def valid-score-1
  {:tennis/player-one :fifteen
   :tennis/player-two :love})

(def invalid-score-2
  {:tennis/player-one :forty
   :tennis/player-two :forty})

(comment
  (s/valid? :tennis/score valid-score-1)
  ;; => true
  (s/valid? :tennis/score invalid-score-2)
  ;; => true
  ,)
