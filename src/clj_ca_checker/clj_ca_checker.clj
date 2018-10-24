(ns clj-ca-checker.clj-ca-checker
  (:require [hickory.core :as hic]
            [hickory.select :as s])
  (:gen-class))

(defn user-table [hickory]
  (s/select (s/descendant (s/tag :html)
                          (s/tag :body)
                          (s/and (s/tag :div)
                                 (s/nth-child 3))
                          (s/tag :div)
                          (s/and (s/tag :div)
                                 (s/nth-child 2))
                          (s/and (s/tag :div)
                                 (s/nth-child 3))
                          (s/tag :div)
                          (s/tag :table)
                          (s/tag :tbody)) hickory))

(defn user [tr]
  (let [user-name (get-in tr [:content 3 :content 0 :content 0])
        name (get-in tr [:content 1 :content 0 :content 0])]
    [name user-name]))

(defn users [html]
  (->> html
       hic/parse
       hic/as-hickory
       user-table
       first
       :content
       (remove string?)
       (map user)))

(defn find-user [user]
  (->> (slurp "https://www.clojure.org/community/contributors")
       users
       (map second)
       (filter (partial = user))
       first))

(defn -main
  "I don't do a whole lot ... yet."
  [github-user]
  (if (find-user github-user)
    (System/exit 0)
    (System/exit 1)))

