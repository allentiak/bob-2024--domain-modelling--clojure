(ns allentiak.domain-modelling.spec.try-0th
  (:require [clojure.spec.alpha :as s]))

;; Invariants - 0th try

(s/def :tennis/player-one int?)
(s/def :tennis/player-two int?)


(s/def :tennis/score
  (s/keys :req [:tennis/player-one
                :tennis/player-two]))

;; First Example (initial iteration)
(def invalid-score-1 {:tennis/player-one 1000
                      :tennis/player-two -15})

(comment
  (s/valid? :tennis/score invalid-score-1)
  ;; => true
  ,)
