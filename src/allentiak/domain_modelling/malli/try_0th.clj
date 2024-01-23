(ns allentiak.domain-modelling.malli.try-0th
  (:require
   [malli.core :as m]))

;; Invariants - 0th try

(def player-one
  (m/schema
    :int))

(def player-two
  (m/schema
    :int))

(def score
  (m/schema [:map
             [:player-one player-one]
             [:player-two player-two]]))

;; First Example (initial iteration)
(def invalid-score-1 {:player-one 1000
                      :player-two -15})

(comment
  (m/validate score invalid-score-1))
  ;; => true
