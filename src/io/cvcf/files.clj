(ns io.cvcf.files
  (:require
   [clojure.java.io :as io]))

(defn slurp-resource
  [path]
  (-> (io/resource path)
      io/input-stream
      slurp))

(defn spit-resource
  [path content]
  (let [resource-path (str "resources/" path)]
    (io/make-parents resource-path)
    (spit resource-path content)))
