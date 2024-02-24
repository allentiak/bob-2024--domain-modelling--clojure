(ns allentiak.domain-modelling.schroedinger-cats.builders
  (:require
     [clojure.spec.alpha :as s]
     [clojure.spec.gen.alpha :as g]))

(defn make-player
  ([] (make-player "Player"))
  ([name] (make-player name ()))
  ([name research] {:name name
                    :research research}))

(defn make-random-deck
  [size]
  (g/sample (s/gen :entities/card) size))

(defn make-world
  ([] (make-world (list (make-player "p1") (make-player "p2")) (make-random-deck 8)))
  ([players deck]
   {:players players
    :research-deck deck}))

(comment
  (require '[clojure.spec.test.alpha :as st])
  (require '[clojure.spec.alpha :as s])
  (require '[allentiak.domain-modelling.schroedinger-cats.entities :refer :all])

  (s/valid? :entities/player (make-player))
  ;; => true
  (st/instrument)
  (make-player)
  (g/generate (s/gen :entities/card))
  ;; => :quantum-card
  ;; => :box-card
  ;; => :quantum-card
  (g/sample (s/gen :entities/card) 10)
  ;; => (:entities/box-card :quantum-card :entities/box-card :quantum-card :entities/box-card :quantum-card :quantum-card :quantum-card :quantum-card :quantum-card)
  (take 10 (g/sample (s/gen :entities/card) 10))
  (def deck (make-random-deck 30))
  deck
  (s/valid? :entities/research-deck deck)
  ;; => true
  (def p1 (make-player "p1"))
  p1
  (def p2 (make-player "p2"))
  p2
  ;; => {:name "p2", :research ()}
  (count (:research p2))
  (def players (list p1 p2))
  players
  ;; => ({:name "p1", :research ()} {:name "p2", :research ()})
  (s/valid? (s/coll-of :entities/player) players)
  ;; => true
  (s/explain (s/coll-of :entities/player) players)
  (def world (make-world (list p1 p2) deck))
  world
  (s/valid? :entities/research-deck (:research-deck world))
  ;; => true
  (s/valid? (s/coll-of :entities/player) (:players world))
  ;; => true
  (s/valid? :entities/world world)
  ;; => false
  (s/explain-data :entities/world world)
  (make-world)
;; => {:players ({:name "p1", :research ()} {:name "p2", :research ()}), :research-deck (:entities/quantum-card :entities/box-card :entities/quantum-card :entities/box-card :entities/box-card :entities/quantum-card :entities/box-card :entities/quantum-card)}
  (s/valid? :entities/world (make-world))
  (clojure.pprint/pprint (s/explain-data :entities/world (make-world)))
  (st/instrument)
  ,)
