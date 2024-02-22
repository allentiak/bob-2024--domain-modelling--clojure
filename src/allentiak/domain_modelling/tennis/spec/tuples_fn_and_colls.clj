(ns allentiak.domain-modelling.tennis.spec.tuples-fn-and-colls
  (:require
   [clojure.spec.alpha :as s]
   [clojure.spec.test.alpha :as st]))


;; Functions

(s/def :square/length
  nat-int?)

(s/def :square/area
  nat-int?)

(defn area-of-square
  "length --> area"
  [len]
  (* len len))

(comment
  (require '[clojure.repl :as repl :refer [doc]])

  (doc area-of-square)
  ;; -------------------------
  ;; allentiak.domain-modelling.spec.tuples-fn-and-colls/area-of-square
  ;; ([len])

  (area-of-square -1))
  ;; => 1

(s/fdef area-of-square
  :args (s/cat :len :square/length)
  :ret :square/area
  :fn #(>= (:ret %)
           (:len %)))

(comment
  (require '[clojure.repl :as repl :refer [doc]])

  (require '[clojure.spec.test.alpha :as st])

  (st/instrument `area-of-square)

  ;; enhanced docs
  (doc area-of-square)
  ;; -------------------------
  ;; allentiak.domain-modelling.spec.tuples-fn-and-colls/area-of-square
  ;; ([len])
  ;; Spec
  ;;   args: (cat :len :square/length)
  ;;   ret: nat-int?
  ;;   fn: (>= (:ret %) (:len %)))
  ;; nil

  ;; improved error messages
  (area-of-square -1)
  ;; Execution error - invalid arguments to
  ;; allentiak.domain-modelling.spec.tuples-fn-and-colls/area-of-square
  ;; at (REPL:1).
  ;; -1 - failed: nat-int? at: [:len] spec: :square/length
  ,)


;; ADT (sum & product)

(s/def :tennis.kind/points
  (s/keys :req [:tennis/player-one
                :tennis/player-two]))
;; ...
(s/def :tennis.kind/deuce
  #{:deuce})

(s/def :tennis/score
  (s/or :points    :tennis.kind/points
        :forty     :tennis.kind/forty
        :advantage :tennis.kind/advantage
        :deuce     :tennis.kind/deuce
        :set       :tennis.kind/set))


;; Optional Values

;; In general, specs are open by default
;; Additionally, maps can have optional keys

(s/def :tennis.kind/points
  (s/keys :req [:tennis/player-one
                :tennis/player-two]
          :opt [:tennis/location]))


;; Other collections

;; Sets
(s/def :some-keywords
  #{:high :low})

;; Arbitrary colls
(s/def :some-numbers
  (s/coll-of int?))

;; Tuples
(s/def :geo/coords1
  (s/tuple double?
           double?))

(s/conform :geo/coords1
           [20.5 -12.5])
;; => [20.5 -12.5]

;; Sequences conform to maps
(s/def :geo/coords2
  (s/cat :lat double?
         :long double?))

(s/conform :geo/coords2 [20.5 -12.5])
;; => {:lat 20.5, :long -12.5}


;; Regular expressions

(s/def :whatever/whatever
  (s/and string?
         #(re-matches #"whatever" %)))

(s/valid? :whatever/whatever "whatever")
;; => true
