(ns allentiak.domain-modelling.malli.tuples-fn-and-colls
  (:require
   [malli.core :as m]
   [malli.instrument :as mi]))

(def three-numbers
  [:sequencial
   {:size 3}
   :int])

(def coords
  [:tuple double? double?])

(m/validate coords
           [20.5 -12.5])
;; => true

(def coords2
  [:map
   [:lat double?]
   [:long double?]])

(m/validate coords2 {:lat 38.0 :long 38.3})
;; => true


(def whatever
  #"whatever")

(m/validate whatever "whatever")
;; => true


(def length
  nat-int?)

(def area
  nat-int?)

(comment
  (m/validate length -1)
  ;; => false
  ,)

;; Length --> Area

(defn area-of-square-with-schema
  {:malli/schema [:=> [:cat nat-int?] nat-int?]}
  [len]
  (* len len))

[:=> [:cat int?] nat-int?]

(comment
  (area-of-square-with-schema -1)
;; => 1

  (mi/collect!)
  (mi/instrument!)
  (area-of-square-with-schema -1)
  (mi/unstrument!)
  ,)
