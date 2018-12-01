(ns aoc2018.day1
  (:require [clojure.string :as str]))

(defn solve []
  (->> (slurp "resources/day1.txt")
       str/split-lines
       (map #(Integer/parseInt %))
       (reduce +)))
