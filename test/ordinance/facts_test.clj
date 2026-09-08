(ns ordinance.facts-test
  (:require [kotoba.lang.text :as str]
            [clojure.test :refer [deftest is]]
            [ordinance.facts :as facts]))

(deftest kuala-lumpur-has-spec-basis
  (let [sb (facts/spec-basis "kuala-lumpur")]
    (is (= 2 (count sb)))
    (is (every? #(str/starts-with? (:ordinance/url %) "https://") sb))
    (is (every? :ordinance/number sb))))

(deftest unknown-municipality-has-no-spec-basis
  (is (nil? (facts/spec-basis "putrajaya")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["kuala-lumpur" "putrajaya"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["putrajaya"] (:missing-municipalities c)))))

(deftest by-topic-filters
  (is (= 2 (count (facts/by-topic "kuala-lumpur" :governance))))
  (is (empty? (facts/by-topic "kuala-lumpur" :labor)))
  (is (empty? (facts/by-topic "putrajaya" :governance))))
