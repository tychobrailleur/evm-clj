(ns evm-clj.stack-test
  (:require [evm-clj.stack :as sut]
            [clojure.test :as t]))


(t/deftest push-stack-test
  (t/testing "push stack item"
    (t/is (= (sut/push-stack [] 1) [1]))
    (t/is (= (sut/push-stack [1 2 3] 4) [1 2 3 4]))
    (t/is (= (sut/push-stack [1 2 3] '(4 5)) [1 2 3 '(4 5)])))
  (t/testing "stack limit reached"
    (t/is (thrown? RuntimeException
                   (sut/push-stack (into [] (range 1 (inc sut/MAX-STACK-SIZE))) 42)))))
