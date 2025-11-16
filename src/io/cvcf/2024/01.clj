(ns io.cvcf.2024.01
  (:require
   [io.cvcf.2024.solve :refer [solve]]
   [io.cvcf.files :as files]
   [clojure.string :as str]))

(defn parse-input
  [input]
  (let [data (map (comp #(map parse-long %)
                        #(str/split % #" +"))
                  (str/split-lines input))]
    [(mapv first data)
     (mapv second data)]))

(defmethod solve 1
  [_ input]
  (let [[d1 d2] (parse-input input)]
    (reduce + (map #(abs (- %1 %2)) (sort d1) (sort d2)))))
