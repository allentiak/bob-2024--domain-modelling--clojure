(ns allentiak.domain-modelling.spec.try-3rd
  (:require [clojure.spec.alpha :as s]))

;; Enforcing Invariants: Third try

(s/def :tennis/points2
  #{:love :fifteen :thirty})

(s/def :tennis/player
  #{:player-one :player-two})

(comment
  (s/valid? :tennis/player :player-one)
  ;; => true
  (s/valid? :tennis/player :player-two)
  ;; => true
  ,)


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
  (s/or :points    :tennis.kind/points
        :forty     :tennis.kind/forty
        :deuce     :tennis.kind/deuce
        :advantage :tennis.kind/advantage
        :game      :tennis.kind/game))

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
  ;; => true

  (s/valid? :tennis/score3 invalid-score2)
  ;; => false

  (s/valid? :tennis.kind/forty valid-score2-forty)
  ;; => true

  (s/valid? :tennis/kind valid-score2-forty)
  ;; => true

  (s/valid? :tennis.kind/deuce valid-score3-deuce)
  ;; => true

  (s/valid? :tennis.kind/advantage valid-score4-advantage)
  ;; => false
  ;; FIXME! this should be true
  ;; either the example or the spec are wrong
  ,)
