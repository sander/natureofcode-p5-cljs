(ns natureofcode.one
  (:require [natureofcode.pvector :as v])
  (:use [natureofcode.core :only [init]]))

(def width 640)
(def height 360)
(def topspeed 10)
(def center [(/ width 2) (/ height 2)])

(defn create-mover []
  {:location [(rand width) (rand height)]
   :velocity [(- (rand 4) 2) (- (rand 4) 2)]
   :acceleration [0 0]})
(defn check-edges [m]
  (update-in
    m [:location]
    (fn [location] (map #(cond (> %1 %3) %2 (< %1 %2) %3 :else %1)
                        location [0 0] [width height]))))
(defn update [m]
  (-> m
      (assoc-in [:acceleration]
                (let [diff (v/sub (v/mouse) (:location m))]
                  (v/mult (v/normalize diff)
                          0.5)))
                          ;(* 0.5 (v/mag diff)))))
      (update-in [:velocity] v/add (:acceleration m))
      (update-in [:velocity] v/limit topspeed)
      (update-in [:location] v/add (:velocity m))))
(defn display [m]
  (js/stroke 0)
  (js/fill 175)
  (let [[x y] (:location m)]
    (js/ellipse x y 16 16)))

(def n 20)
(def movers (atom (vec (repeatedly n create-mover))))

(defn setup []
  (js/createCanvas width height)
  (js/background 255))

(defn draw []
  (js/background 255)
  (swap! movers #(map update %))
  (swap! movers #(map check-edges %))
  (doseq [m @movers] (display m))

  (apply js/translate center)
  (apply js/line 0 0 (v/mult (v/normalize
                               (v/sub (v/mouse) center))
                             50)))

(init setup draw)
