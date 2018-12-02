(ns aoc2018.day01
  (:require [clojure.string :as str]))

(def freq-changes (->> (slurp "resources/day1.txt")
                       str/split-lines
                       (map #(Integer/parseInt %))))

(defn solve [] (reduce + freq-changes))

(defn solve2 []
  (let [iter-fn (fn [seen-freqs current-freq next-freq-changes]
                  (let [next-freq (+ current-freq (first next-freq-changes))]
                    (if (contains? seen-freqs next-freq)
                      next-freq
                      (recur (conj seen-freqs next-freq)
                             next-freq
                             (rest next-freq-changes)))))]
    (iter-fn #{0} 0 (cycle freq-changes))))
