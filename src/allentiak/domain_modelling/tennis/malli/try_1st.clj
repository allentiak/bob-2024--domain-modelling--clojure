(ns allentiak.domain-modelling.tennis.malli.try-1st
  (:require [malli.core :as m]))

;; Invariants: First try

(def points
  :int)

(def player-one
  points)

(def player-two
  points)

(def score
  [:map
   [:player-one player-one]
   [:player-two player-two]])

;; First example, revisited
(def invalid-score-1
  {:player-one 1000
   :player-two -15})

(comment
  (m/validate score invalid-score-1))
  ;; => true
