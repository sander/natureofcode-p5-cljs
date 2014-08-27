(ns natureofcode.ecosystem
  (:require [natureofcode.pvector :as v])
  (:use [natureofcode.core :only [init]]))

(def width 640)
(def height 360)
(def topspeed 10)
(def center [(/ width 2) (/ height 2)])
(def trail true)

(def creature-types
  [:nervous-fly :swimming-fish :hopping-bunny :slithering-snake])
;todo give each an acceleration behavior, and create creatures randomly

(defmulti acceleration #(%2 :type))
(defmethod acceleration :nervous-fly [old m]
  (v/div (v/random2d) 2))
(defmethod acceleration :swimming-fish [old m]
  [1 1]
  )
(defmethod acceleration :default [old m]
  (let [diff (v/sub (v/mouse) (:location m))]
    (v/mult (v/normalize diff) 0.5)))

(defn create-creature []
  {:type (creature-types 0)
   :location [(rand width) (rand height)]
   :velocity [(- (rand 4) 2) (- (rand 4) 2)]
   :acceleration [0 0]})
(defn check-edges [m]
  (update-in
    m [:location]
    (fn [location] (map #(cond (> %1 %3) %2 (< %1 %2) %3 :else %1)
                        location [0 0] [width height]))))
(defn update [m]
  (-> m
      (update-in [:acceleration] acceleration m)
      (update-in [:velocity] v/add (:acceleration m))
      (update-in [:velocity] v/limit topspeed)
      (update-in [:location] v/add (:velocity m))))
(defn display [m]
  (js/stroke 0)
  (js/fill 175)
  (let [[x y] (:location m)]
    (js/ellipse x y 16 16)))

(def n 5)
(def creatures (atom (vec (repeatedly n create-creature))))

(defn setup []
  (js/createCanvas width height)
  (js/background 255))

(defn draw []
  (apply js/background (if trail [255 255 255 (* 255 0.1)] [255]))
  (swap! creatures #(map (comp check-edges update) %))
  (doseq [m @creatures] (display m)))

(init setup draw)
