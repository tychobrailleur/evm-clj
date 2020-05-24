(ns evm-clj.stack)


(def MAX-STACK-SIZE 1024)

(defn push-stack [stack items]
  (if (> (count stack) (dec MAX-STACK-SIZE))
    (throw (RuntimeException. "Stack limit reached"))
    (conj stack items)))

(defn push-int [stack val]
  (push-stack stack (list :int val)))

(defn pop-stack [stack]
  (first stack))
