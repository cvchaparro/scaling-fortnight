(ns io.cvcf.2024.02
  (:require
   [clojure.string :as s]
   [io.cvcf.2024.solve :refer [solve]]
   [io.cvcf.files :as files]))

(defn parse-input
  [input]
  (map (comp #(map parse-long %)
             #(s/split % #" +"))
       (s/split-lines input)))

(defn increasing?
  [report]
  (apply < report))

(defn decreasing?
  [report]
  (apply > report))

(defn safe?
  [report]
  (and ((some-fn increasing? decreasing?) report)
       (= (->> (partition 2 1 report)
               (map #(abs (apply - %)))
               (filter #(<= 1 % 3))
               count)
          (dec (count report)))))

(defmethod solve 2
  [_ input]
  (str "p1: " (count (filter safe? (parse-input input)))))

(comment
  (solve 2 (files/slurp-resource "inputs/2024/02-test"))

  ::end)
