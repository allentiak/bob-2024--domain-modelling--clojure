(ns allentiak.domain-modelling.tennis.malli.try-3rd
  (:require [malli.core :as m]))

;; Enforcing Invariants: Third try

(def points
  ;; now, without :forty
  ;; to prevent ":forty :forty"
  [:enum :love :fifteen :thirty])

(def player-one
  points)

(def player-two
  points)

(def player
  [:enum :player-one :player-two])

(comment
  (m/validate player :player-one)
  ;; => true
  (m/validate player :player-two)
  ;; => true
  ,)


;; Kind 1/5: Points

(def points-kind
  [:map
   [:player-one player-one]
   [:player-two player-two]])

(def valid-points-kind
  {:player-one :thirty
   :player-two :thirty})

(def invalid-points-kind
  {:player-one :forty
   :player-two :forty})

(comment
  (m/validate points-kind valid-points-kind)
  ;; => true
  (m/validate points-kind invalid-points-kind)
  ;; => false
  ,)


;; Kind 2/5: Forty

(def forty-player
  player)

(def points-other-player
  points)

(def forty-kind
  [:map
   [:forty-player forty-player]
   [:points-other-player points-other-player]])

(def valid-kind--forty
  {:forty-player :player-one
   :points-other-player :thirty})

;; the 'draw' :forty :forty is now irrepresentable

(comment
  (m/validate forty-kind valid-kind--forty)
  ;; => true
  ,)


;; Kind 3/5: Advantage

(def advantage-player
  player)

(def advantage-kind
  [:map
   [:advantage-player advantage-player]])

(def valid-kind--advantage
  {:advantage-player :player-two})

(comment
  (m/validate advantage-kind valid-kind--advantage))
  ;; => true


;; Kind 4/5: Deuce

(def deuce-kind
  [:enum :deuce])

(def valid-kind--deuce
  :deuce)

(comment
  (m/validate deuce-kind valid-kind--deuce))
  ;; => true


;; Kind 5/5: Set

(def set-winner
  player)

(def set-kind
  [:map
   [:set-winner set-winner]])

(def valid-kind--set
  {:set-winner :player-one})

(comment
  (m/validate set-kind valid-kind--set)
  ;; => true
  ,)


;; With all kinds defined,
;; we only have to group them into score

(def score
  [:or points-kind
        forty-kind
        advantage-kind
        deuce-kind
        set-kind])


(comment
  (m/validate score valid-points-kind)
  ;; => true

  (m/validate score valid-kind--forty)
  ;; => true

  (m/validate score valid-kind--advantage)
  ;; => true

  (m/validate score valid-kind--deuce)
  ;; => true

  (m/validate score valid-kind--set)
  ;; => true
  ,)
