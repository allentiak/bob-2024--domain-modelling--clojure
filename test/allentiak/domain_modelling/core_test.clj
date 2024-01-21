(ns allentiak.domain-modelling.core-test
  (:require [allentiak.domain-modelling.core :as sut]
            [clojure.test :refer [deftest testing use-fixtures]]
            [expectations.clojure.test :refer [expect]]))

(deftest whatever
  (testing "whatever"
    (expect (= 1 1))))
