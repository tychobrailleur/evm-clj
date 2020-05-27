(ns evm-clj.stack-test
  (:require [evm-clj.stack :as sut]
            [clojure.test :as t]))


(t/deftest push-stack-test
  (t/testing "push stack item"
    (t/is (= (sut/push-stack '() 1) '(1)))
    (t/is (= (sut/push-stack '(1 2 3) 4) '(4 1 2 3)))
    (t/is (= (sut/push-stack '(1 2 3) '(4 5)) '((4 5) 1 2 3))))
  (t/testing "stack limit reached"
    (t/is (thrown? RuntimeException
                   (sut/push-stack (range 1 (inc sut/MAX-STACK-SIZE)) 42)))))

(t/deftest pop-stack-test
  (t/testing "pop stack item"
    (t/is (= (list 2 3) (sut/pop-stack (list 1 2 3))))))
