(ns io.cvcf.2024.01
  (:require
   [clojure.string :as s]
   [io.cvcf.2024.solve :refer [solve]]
   [io.cvcf.files :as files]))

(defn parse-input
  [input]
  (let [data (map (comp #(map parse-long %)
                        #(s/split % #" +"))
                  (s/split-lines input))]
    [(mapv first data)
     (mapv second data)]))

(defmethod solve 1
  [_ input]
  (let [[d1 d2] (parse-input input)]
    (str "p1: " (reduce + (map #(abs (- %1 %2)) (sort d1) (sort d2)))
         "\n"
         "p2: " (reduce + (map (fn [d] (* d (count (filter #(= d %) d2)))) d1)))))

(comment
  (println (solve 1 (files/slurp-resource "inputs/2024/01-test")))

  ::end)
