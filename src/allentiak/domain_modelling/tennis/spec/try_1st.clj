(ns allentiak.domain-modelling.tennis.spec.try-1st
  (:require [clojure.spec.alpha :as s]))

;; Invariants: First try

(s/def :tennis/points int?)

(s/def :tennis/player-one :tennis/points)
(s/def :tennis/player-two :tennis/points)

(s/def :tennis/score
  (s/keys :req [:tennis/player-one
                :tennis/player-two]))

;; First example, revisited
(def invalid-score-1
  {:tennis/player-one 1000
   :tennis/player-two -15})

(comment
  (s/valid? :tennis/score invalid-score-1))
  ;; => true
