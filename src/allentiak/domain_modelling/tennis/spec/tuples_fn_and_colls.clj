(ns allentiak.domain-modelling.tennis.spec.tuples-fn-and-colls
  (:require
   [clojure.spec.alpha :as s]
   [clojure.spec.test.alpha :as st]))

(s/def three-numbers
  (s/coll-of int?
             :count 3))

(s/def :geo/coords1
  (s/tuple double?
           double?))

(s/conform :geo/coords1
           [20.5 -12.5])
;; => [20.5 -12.5]

(s/def :geo/coords2
  (s/cat :lat double?
         :long double?))

(s/conform :geo/coords2 [20.5 -12.5])
;; => {:lat 20.5, :long -12.5}

(s/def :whatever/whatever
  (s/and string?
         #(re-matches #"whatever" %)))

(s/valid? :whatever/whatever "whatever")
;; => true

(s/def :square/length
  nat-int?)

(s/def :square/area
  nat-int?)

;; Length --> Area

(defn area-of-square
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
  (area-of-square -1))
  ;; Execution error - invalid arguments to
  ;; allentiak.domain-modelling.spec.tuples-fn-and-colls/area-of-square
  ;; at (REPL:1).
  ;; -1 - failed: nat-int? at: [:len] spec: :square/length
