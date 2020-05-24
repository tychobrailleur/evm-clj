(ns evm-clj.core
  (:require [evm-clj.stack :as stack]))

(defn bytes->int [bytes]
  (->> bytes
       (map (partial format "%02x"))
       (apply (partial str "0x"))
       read-string))

(defn push-opcode [num code stack]
  (if (> num 0)
    {:code (drop num code) :stack (stack/push-stack stack (byte-array (take num code)))}
    {:stack stack :code []}))


(defn add-opcode [context]
  (let [stack (:stack context)
        num1 (bytes->int (stack/pop-stack stack))
        num2 (bytes->int (stack/pop-stack stack))]
    {:stack (stack/push-stack (drop 2 stack) (+ num1 num2)) :code (rest (:code context))}))

(defn execute [context]
  (if (empty? (:code context))
    context
    (let [code (:code context)
          [opcode _r] code]
      (case opcode
        0x01 (execute (add-opcode context))
        0x60 (execute (push-opcode 1 (rest (:code context)) (:stack context)))))))
