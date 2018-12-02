(ns aoc2018.day02
  (:require [clojure.string :as str]))

(def box-ids (->> "resources/day02.txt" slurp str/split-lines (map str/trim)))

;; Part 1

(defn count-chars [s]
  (let [update-count (fn [x] (if (some? x) (inc x) 1))]
    (reduce #(update %1 %2 update-count) {} s)))

(defn has-dupes? [n s]
  (->> s
       count-chars
       vals
       (some #(= % n))))

(defn count-dupes [n input]
  (count (filter (partial has-dupes? n) input)))

(defn solve1 []
  (let [twos (count-dupes 2 box-ids)
        threes (count-dupes 3 box-ids)]
    (* twos threes)))

;; Part 2

(defn dist [s1 s2] (- (count s1) (count (filter true? (map #(= %1 %2) s1 s2)))))

(defn solve2 []
  (let [find-least-dist (fn [[id & ids]]
                          (let [dists (map (fn [curr-id] [curr-id (dist id curr-id)]) ids)
                                match (first (filter #(= (second %) 1) dists))]
                            (if (some? match)
                              (apply str (map #(if (= %1 %2) %1 "") (first match) id))
                              (recur ids))))]
    (find-least-dist box-ids)))
