(ns allentiak.domain-modelling.spec
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
  (s/valid? :tennis/score1 invalid-score1-1)
  ;; => true
  ,)


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


;; Enforcing Invariants: Third try

(s/def :tennis/points2
  #{:love :fifteen :thirty})

(s/def :tennis/player
  #{:player-one :player-two})

(s/def :tennis.kind/points
  (s/keys :req [:tennis/player-one2
                :tennis/player-two2]))

(s/def :tennis/forty-player
  :tennis/player)

(s/def :tennis/points-other-player
  :tennis/points2)

(s/def :tennis.kind/forty
  (s/keys :req [:tennis/forty-player
                :tennis/points-other-player]))

(s/def :tennis.kind/advantage
  (s/keys :req [:tennis/player]))

(s/def :tennis.kind/deuce
  #{:tennis.score/deuce})

(s/def :tennis.kind/game
  (s/keys :req [:tennis/player]))

(s/def :tennis/kind
  #{:tennis.kind/points :tennis.kind/forty :tennis.kind/deuce :tennis.kind/advantage :tennis.kind/game})

(s/def :tennis/score3
  (s/keys :req [:tennis/kind]
          :opt [:tennis/points]))

(def valid-score2-forty
  {:tennis/forty-player :player-one
   :tennis/points-other-player :thirty})

(def valid-score3-deuce
  :tennis.score/deuce)

(def valid-score4-advantage
  :tennis/player-one)

(def valid-score5-advantage
  :tennis/player-two)

(comment
  (s/valid? :tennis.kind/points valid-score1)
  ;; => true
  (s/valid? :tennis/kind valid-score1)
  ;; => false
  ;; this one should be true...

  (s/valid? :tennis/score3 invalid-score2)
  ;; => false

  (s/valid? :tennis.kind/forty valid-score2-forty)
  ;; => true
  (s/valid? :tennis/kind valid-score2-forty)
  ;; => false
  ;; this one should be true...

  (s/valid? :tennis.kind/deuce valid-score3-deuce)
  ;; => true

  (s/valid? :tennis.kind/advantage valid-score4-advantage)
  ,)
