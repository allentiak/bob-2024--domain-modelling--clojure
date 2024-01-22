(ns allentiak.domain-modelling.spec.try-1st
  (:require [clojure.spec.alpha :as s]))

;; Invariants: First try

(s/def :tennis/points0 int?)

(s/def :tennis/player-one1 :tennis/points0)
(s/def :tennis/player-two1 :tennis/points0)

(s/def :tennis/score1
  (s/keys :req [:tennis/player-one1
                :tennis/player-two1]))

(def invalid-score1-1
  {:tennis/player-one1 1000
   :tennis/player-two1 -15})

(comment
  (s/valid? :tennis/score1 invalid-score1-1))
  ;; => true
