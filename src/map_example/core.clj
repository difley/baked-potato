(ns map-example.core
  (:gen-class))



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

 (println "Hello, World!"))
