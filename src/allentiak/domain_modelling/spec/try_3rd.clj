(ns allentiak.domain-modelling.spec.try-3rd
  (:require [clojure.spec.alpha :as s]))

;; Enforcing Invariants: Third try

(s/def :tennis/points
  ;; now, without :forty
  ;; to prevent ":forty :forty"
  #{:love :fifteen :thirty})

(s/def :tennis/player-one
  :tennis/points)

(s/def :tennis/player-two
  :tennis/points)

(s/def :tennis/player
  #{:player-one :player-two})

(comment
  (s/valid? :tennis/player :player-one)
  ;; => true
  (s/valid? :tennis/player :player-two)
  ;; => true
  ,)


;; Kind 1/5: Points

(s/def :tennis.kind/points
  (s/keys :req [:tennis/player-one
                :tennis/player-two]))

(def valid-kind--points
  {:tennis/player-one :thirty
   :tennis/player-two :thirty})

(def invalid-kind--points
  {:tennis/player-one :forty
   :tennis/player-two :forty})

(comment
  (s/valid? :tennis.kind/points valid-kind--points)
  ;; => true
  (s/valid? :tennis.kind/points invalid-kind--points)
  ;; => false
  ,)


;; Kind 2/5: Forty

(s/def :tennis/forty-player
  :tennis/player)

(s/def :tennis/points-other-player
  :tennis/points)

(s/def :tennis.kind/forty
  (s/keys :req [:tennis/forty-player
                :tennis/points-other-player]))

(def valid-kind--forty
  {:tennis/forty-player :player-one
   :tennis/points-other-player :thirty})

;; the 'draw' :forty :forty is now irrepresentable

(comment
  (s/valid? :tennis.kind/forty valid-kind--forty)
  ;; => true
  ,)


;; Kind 3/5: Advantage

(s/def :tennis/advantage-player
  :tennis/player)

(s/def :tennis.kind/advantage
  (s/keys :req [:tennis/advantage-player]))

(def valid-kind--advantage
  {:tennis/advantage-player :player-two})

(comment
  (s/valid? :tennis.kind/advantage valid-kind--advantage))
  ;; => true


;; Kind 4/5: Deuce

(s/def :tennis.kind/deuce
  #{:deuce})

(def valid-kind--deuce
  :deuce)

(comment
  (s/valid? :tennis.kind/deuce valid-kind--deuce))
  ;; => true


;; Kind 5/5: Set

(s/def :tennis/set-winner
  :tennis/player)

(s/def :tennis.kind/set
  (s/keys :req [:tennis/set-winner]))

(def valid-kind--set
  {:tennis/set-winner :player-one})

(comment
  (s/valid? :tennis.kind/set valid-kind--set)
  ;; => true
  ,)


;; With all kinds defined,
;; we only have to group them into score

(s/def :tennis/score
  (s/or :points    :tennis.kind/points
        :forty     :tennis.kind/forty
        :advantage :tennis.kind/advantage
        :deuce     :tennis.kind/deuce
        :set       :tennis.kind/set))


(comment
  (s/valid? :tennis/score valid-kind--points)
  ;; => true

  (s/valid? :tennis/score valid-kind--forty)
  ;; => true

  (s/valid? :tennis/score valid-kind--advantage)
  ;; => true

  (s/valid? :tennis/score valid-kind--deuce)
  ;; => true

  (s/valid? :tennis/score valid-kind--set)
  ;; => true
  ,)
