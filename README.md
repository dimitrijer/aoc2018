My solutions to [Advent of Code 2018](https://adventofcode.com) puzzles.

# Stuff I learned

I learn by comparing my solutions with other peoples' code after submitting.
* Day 01:
  * Use `clojure.core/reduced` to break out and return immediate value when
    reducing.
* Day 02:
  * Use `clojure.core/frequencies` to get number of appearances of distinct
    item in coll.
  * Another idea is to use `(clojure.core/group-by identity)` and then perform
    count.
  * Get a difference in two collections with `clojure.data/diff` - it returns
    items only in A, items only in B and items in both.
  * `for` list comprehension is usually more readable than nested loops/recurs.
    Also great for doing combinations, e.g. `(for [i xs j xs :when i < j] ...)`
  * Use destructuring, it can greatly improve readability.
