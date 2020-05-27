(ns evm-clj.core
  (:require [evm-clj.stack :as stack]
            [evm-clj.types :as t]))

(defn push-opcode
  "Push the NUM following bytes from CODE onto STACK as a byte array"
  [num code stack]
  (if (> num 0)
    {:code (drop num code) :stack (->> (take num code)
                                       (map byte)
                                       (stack/push-stack stack))}
    {:stack stack :code code}))

(defn pop-opcode
  "Pop an item off the stack and discard it"
  [{:keys [code stack] :as context}]
  (-> context
      (update :stack (fn [_] (drop 1 stack)))
      (update :code (fn [_] (drop 1 code)))))

(defn add-opcode
  "Add the two topmost items on STACK and push the result on the stack"
  [{code :code stack :stack}]
  (let [num1 (stack/peek-as-int stack)
        num2 (stack/peek-as-int (stack/pop-stack stack))]
    {:stack (-> (drop 2 stack)
                (stack/push-stack (+ num1 num2)))
     :code (drop 1 code)}))

(defn execute [{code :code stack :stack :as context}]
  (if (empty? code)
    context
    (let [[opcode & r] code]
      (case opcode
        0x01 (recur (add-opcode context))
        0x50 (recur (pop-opcode context))
        0x60 (recur (push-opcode 1 r stack)))))) ;; for now hardcoded to push1.
