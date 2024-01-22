(ns allentiak.domain-modelling.spec.tuples-fn-and-colls
  (:require [clojure.spec.alpha :as s]))

(s/def three-numbers
  (s/coll-of int?
             :count 3))

(s/def :geo/coords
  (s/tuple double?
           double?))

(s/conform :geo/coords
           [20.5 -12.5])
;; => {:lat 20.5, :long -12.5}

(s/def :geo/coords
  (s/cat :lat double?
         :long double?))


(s/def :whatever/whatever
  (s/and string?
         #(re-matches #"whatever" %)))

(s/valid? :whatever/whatever "whatever")
;; => true


(s/def ::length
  nat-int?)

(s/def ::area
  nat-int?)

;; Length --> Area

(s/fdef area-of-square
  :args (:len ::length)
  :ret ::area
  :fn #(>= (:ret %)
           (:len %)))
