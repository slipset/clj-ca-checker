(ns clj-ca-checker.clj-ca-checker
  (:gen-class))

(defn find-user [user]
  (->> (slurp "https://www.clojure.org/community/contributors")
       (re-find (re-pattern user))))

(defn -main
  "I don't do a whole lot ... yet."
  [github-user]
  (if (find-user github-user)
    (System/exit 0)
    (System/exit 1)))
