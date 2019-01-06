(ns aoc2018.day03
  (:require [clojure.string :as str]
            [clojure.core.matrix :as mat]))

(mat/set-current-implementation :ndarray)

(def box-regex #"#(\d+) @ (\d+),(\d+): (\d+)x(\d+)")

(defn parse-box [s]
  (if-let [[_ & data] (re-matches box-regex s)]
    (zipmap [:id :x :y :w :h] (map #(Integer/parseInt %) data))))

(def boxes (->> (slurp "resources/day03.txt")
                str/split-lines
                (map (comp parse-box str/trim))
                (filter identity)))

(defn solve12 []
  (let [adder (fn [m {:keys [x w y h]}]
                (mat/emap! inc (mat/submatrix m x w y h))
                m)
        canvas (reduce adder (mat/zero-matrix 1000 1000) boxes)
        subtractor (fn [{:keys [x w y h] :as box}]
                     [box (mat/sub (mat/submatrix canvas x w y h) 1)])]
    (println "Overlapping pieces: " (mat/non-zero-count (mat/gt canvas 1)))
    (println "Non-overlapping piece ID: " (->> (map subtractor boxes)
                                               (filter #(mat/zero-matrix? (second %)))
                                               first
                                               first
                                               :id))))
