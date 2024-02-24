(ns allentiak.domain-modelling.schroedinger-cats.core-test
  (:require [allentiak.domain-modelling.schroedinger-cats.core :as sut]
            [clojure.test :refer [deftest testing use-fixtures]]
            [expectations.clojure.test :refer [expect]]))

(deftest whatever-test
  (testing "whatever"
    (expect (= 1 1))))
