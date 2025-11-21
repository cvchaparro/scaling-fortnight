(ns io.cvcf.days
  (:require
   [babashka.http-client :as http]
   [clojure.java.io :as io]
   [io.cvcf.files :as f]))

(defonce base-url "https://adventofcode.com")
(def ^:dynamic *session-id* (f/slurp-resource "session-id"))

(defn date-based-input-file
  [year day]
  (format "inputs/%04d/%02d" year day))

(defn date-based-url
  [year day]
  (format "%s/%d/day/%d/input" base-url year day))

(defn get-puzzle-input
  [year day]
  (when-not *session-id*
    (throw (RuntimeException. "No session id is set")))

  (let [input-file (date-based-input-file year day)
        url (date-based-url year day)
        session-cookie (format "session=%s" *session-id*)]
    (when-not (io/resource input-file)
      (print input-file "not found: downloading...")
      (->> (http/get url {:headers {"cookie" session-cookie}})
           :body
           (f/spit-resource input-file))
      (println "[done]"))
    (f/slurp-resource input-file)))

(comment

  (get-puzzle-input 2024 1)

  ::end)
