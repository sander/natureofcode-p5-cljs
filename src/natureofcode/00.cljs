(ns natureofcode.zero
  (:use [natureofcode.core :only [init]]))

(def width 640)
(def height 360)

(defn create-walker []
  {:x (/ width 2) :y (/ height 2)})

(defn display [walker]
  (js/stroke 0)
  (js/point (:x walker) (:y walker)))

(defn step-old [walker]
  (let [choice (int (js/random 4))]
    (case choice
      0 (update-in walker [:x] inc)
      1 (update-in walker [:x] dec)
      2 (update-in walker [:y] inc)
      (update-in walker [:y] dec))))

(defn step [walker]
  (let [step-x (- (rand-int 3) 1)
        step-y (- (rand-int 3) 1)]
    (-> walker
        (update-in [:x] + step-x)
        (update-in [:y] + step-y))))

(def w (atom (create-walker)))

(defn setup []
  (js/createCanvas width height)
  (js/background 255))

(defn draw []
  (swap! w step)
  (display @w))

(init setup draw)
