(ns io.cvcf.2024.core
  (:require
   [io.cvcf.days :as days]
   [io.cvcf.2024.solve :refer [solve]]))

(defonce year 2024)

(defn -main [& args]
  (let [[day & _] args]
    (printf "\nDay %02d\n" day)
    (let [input (days/get-puzzle-input year day)]
      (println "=>" (solve day input)))))
