(ns evm-clj.types)

(defn bytes->int
  "Takes a sequence of BYTES and converts into an integer"
  [bytes]
  (->> bytes
       (map (partial format "%02x"))
       (apply (partial str "0x"))
       read-string))
