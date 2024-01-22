(ns allentiak.domain-modelling.spec.try-0th
  (:require [clojure.spec.alpha :as s]))

;; First Example
(def invalid-score1-0 {:tennis/player-one0 1000
                       :tennis/player-two0 -15})

;; Invariants - 0th try

(s/def :tennis/player-one0 int?)
(s/def :tennis/player-two0 int?)


(s/def :tennis/score0
  (s/keys :req [:tennis/player-one0
                :tennis/player-two0]))

(comment
  (s/valid? :tennis/score0 invalid-score1-0)
  ;; => true
  ,)
