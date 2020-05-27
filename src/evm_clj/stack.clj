(ns evm-clj.stack
  (:require [evm-clj.types :as t]))


(def MAX-STACK-SIZE 1024)

(defn push-stack [stack items]
  (if (> (count stack) (dec MAX-STACK-SIZE))
    (throw (RuntimeException. "Stack limit reached"))
    (conj stack items)))

(defn push-int [stack val]
  (push-stack stack (list :int val)))

(defn pop-stack [stack]
  (pop stack))

(defn peek-as-int [stack]
  (t/bytes->int (first stack)))
