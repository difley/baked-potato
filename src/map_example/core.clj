(ns map-example.core
  (:gen-class))

(defn exception-handling []
    (defn collection? [obj]
        (println "obj is a" (class obj))
        (or (coll? obj)
            (instance? java.util.Collection obj)))

    (defn average [coll]
        (when-not (collection? coll)
            (throw (IllegalArgumentException. "expected a collection")))
        (when (empty? coll)
            (throw (IllegalArgumentException. "collection is empty")))
        (let [sum (apply + coll)]
            (/ sum (count coll))))

    (try
        (println "list average =" (average '(2 3)))
        (println "vector average =" (average [2 3]))
        (println "set average =" (average #{2 3}))
        (let [a1 (java.util.ArrayList.)]
            (doto a1 (.add 2) (.add 3))
            (println "ArrayList average =" (average a1)))
        (println "string average =" (average "1 2 3 4"))
        (catch IllegalArgumentException e
            (println e)
            (.printStackTrace e)
        )
        (finally
            (println "in finally"))))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
    (def popsicle-map
        (hash-map :red :cherry, :green :apple, :purple :grape))

    (println popsicle-map)

    (def popsicle-map
        {:red :cherry, :green :apple, :purple :grape})

    (println popsicle-map)
    (def popsicle-map
        (sorted-map :red :cherry, :green :apple, :purple :grape))

    (println popsicle-map)

    (println (get popsicle-map :green))
    (println (popsicle-map :green))
    (println (:green popsicle-map))


    (println (contains? popsicle-map :green))
    (println (keys popsicle-map))
    (println (vals popsicle-map))

    (println (assoc popsicle-map :green :lime :blue :blueberry))

    (println (dissoc popsicle-map :green :blue))

    (doseq [[color flavor] popsicle-map]
        (println (str "The flavor of " (name color)
            " popsicles is " (name flavor) ".")))

    (println
        (select-keys popsicle-map [:red :green :blue]))

    (def person {
        :name "Seth Difley"
        :address {
            :street "666 Nowhere Blvd"
            :city "Crankshaft"
            :state "Zinc"
            :zip "66666"}
        :employer {
            :name "Plastic WhatNots, Inc"
            :address {
                :street "999 Hundred Acre Rd"
                :city "Rotor Motor"
                :state "Zinc"
                :zip "66667"
                }}})
    (println person)

    (println "get-in:" (get-in person [:employer :address :city]))
        
    (println (-> person :employer :address :city))
    (println
        (reduce get person [:employer :address :city]))

    (println
        (assoc-in person [:employer :address :city] "Muffler"))

    (println
        (update-in person [:employer :address :zip] str "-1234"))


    (defn parting
        "the only thing better than meeting is parting"
        [name]
        (str "G'day to ya ", name))

    (println (parting "Seth"))

    (defn power [base & exponents]
        (reduce #(Math/pow %1 %2) base exponents))

    (println (power 2 3 4))

    (defn parting
        "automate the best part of meeting someone"
        ([] (parting "World"))
        ([name] (parting name "en"))
        ([name language]
            (condp = language
                "en" (str "Goodbye, " name)
                "es" (str "Adios, " name)
                (throw (IllegalArgumentException.
                    (str "unsupported language " language))))))

    (println (parting))
    (println (parting "Seth"))
    (println (parting "Seth" "es"))
    ;;(println (parting "Seth" "xy"))

    (def years [1940 1944 1961 1985 1987])
    (println (filter (fn [year] (even? year)) years))
    (println (filter #(even? %) years))


    (defn pair-test [test-fn n1 n2]
        (if (test-fn n1 n2) "pass" "fail"))

    (println (pair-test #(even? (+ %1 %2)) 3 5))

    (defn- polynomial
        "computes a point value of a polynomial"
        [coefs x]
        (let [exponents (reverse (range (count coefs)))]
            (apply + (map #(* %1 (Math/pow x %2)) coefs exponents))))

    (defn- derivative
        [coefs x]
        (let [exponents (reverse (range (count coefs)))
            derivative-coefs (map #(* %1 %2) (butlast coefs) exponents)]
            (polynomial derivative-coefs x)))


    (def f (partial polynomial [2 1 3]))
    (def f-prime (partial derivative [2 1 3]))

    (println "f(2) =" (f 2))
    (println "f'(2) =" (f-prime 2))
    (def memo-f (memoize f))

    (println "priming call")
    (time (f 2))

    (println "without memoization")
    (dotimes [_ 3] (time (f 2)))

    (println "with memoization")
    (dotimes [_ 3] (time (memo-f 2)))

    (exception-handling)
)
