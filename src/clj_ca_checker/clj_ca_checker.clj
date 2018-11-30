(ns clj-ca-checker.clj-ca-checker
  (:require [clojure.string :as str])
  (:gen-class))

(def contributors-source
  "https://raw.githubusercontent.com/clojure/clojure-site/master/content/community/contributors.csv")
(defn fetch-contributors []
  (->> contributors-source
       slurp
       str/split-lines
       (map #(str/split % #","))))

(defn contributor? [contributors github-user]
  (->> contributors
       (map second)
       (filter (partial = github-user))
       first))

(defn -main
  [github-user]
  (if (or (= github-user "richhickey") ;; you never know
          (contributor? (fetch-contributors) github-user))
    (System/exit 0)
    (System/exit 1)))

