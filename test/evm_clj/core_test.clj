(ns evm-clj.core-test
  (:require [clojure.test :refer :all]
            [evm-clj.stack :as stack]
            [evm-clj.core :refer :all]))

(deftest a-test
  (testing "push 1"
    (is (= 42 (stack/peek-as-int (:stack (execute {:code [0x60 0x2a] :stack '()}))))))
  (testing "push 2"
    (is (= 42 (stack/peek-as-int (:stack (execute {:code [0x60 0x2a 0x60 0x2b 0x50] :stack '()})))))
    (is (= 84 (peek (:stack (execute {:code [0x60 0x2a 0x60 0x2a 0x01] :stack '()})))))))
