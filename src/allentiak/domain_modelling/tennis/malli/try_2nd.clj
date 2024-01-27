(ns allentiak.domain-modelling.tennis.malli.try-2nd
  (:require [malli.core :as m]))

;; Enforcing Invariants: Second try

(def points
  (m/schema [:enum :love :fifteen :thirty :forty]))

(def player-one
  points)

(def player-two
  points)

(def score
  (m/schema [:map
             [:player-one player-one]
             [:player-two player-two]]))

;; First Example, revisited
(def invalid-score-1
  {:player-one 1000
   :player-two -15})

(comment
  (m/validate score invalid-score-1)
  ;; => false

  (m/explain score invalid-score-1)
;; => {:schema [:map [:player-one [:enum :love :fifteen :thirty :forty]] [:player-two [:enum :love :fifteen :thirty :forty]]], :value {:player-one 1000, :player-two -15}, :errors ({:path [:player-one], :in [:player-one], :schema [:enum :love :fifteen :thirty :forty], :value 1000} {:path [:player-two], :in [:player-two], :schema [:enum :love :fifteen :thirty :forty], :value -15})}
  ,)

(def valid-score1
  {:player-one :fifteen
   :player-two :love})

(def invalid-score2
  {:player-one :forty
   :player-two :forty})

(comment
  (m/validate score valid-score1)
  ;; => true
  (m/validate score invalid-score2)
  ;; => true
  ,)
