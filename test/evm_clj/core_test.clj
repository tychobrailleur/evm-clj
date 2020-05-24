(ns evm-clj.core-test
  (:require [clojure.test :refer :all]
            [evm-clj.core :refer :all]))

(deftest a-test
  (testing "push 1"
    (is (= [42] (:stack (execute {:code [0x60 0x2a] :stack []}))))
    (is (= [84] (:stack (execute {:code [0x60 0x2a 0x60 0x2a 0x01] :stack []}))))))
