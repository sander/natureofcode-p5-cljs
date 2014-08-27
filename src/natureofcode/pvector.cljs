(ns natureofcode.pvector)

(defn add [v & args] (apply map + v args))
(defn sub [v & args] (apply map - v args))

(defn mult [v & scalars] (map #(apply * % scalars) v))
(defn div [v & scalars] (map #(apply / % scalars) v))

(defn mag [v] (.sqrt js/Math (apply + (map #(* % %) v))))
(defn normalize [v] (let [m (mag v)] (if (= m 0) v (div v m))))
(defn limit [v max]
  (if (<= (mag v) max)
    v
    (mult (normalize v) max)))

(defn mouse [] [(.-mouseX js/window) (.-mouseY js/window)])
(defn random2d []
  (letfn [(comp [] (- (rand) 0.5))]
    (normalize [(comp) (comp)])))
(defn noise2d [t] ; not so useful
  (letfn [(comp [t0] (- (js/noise (+ t0 t)) 0.5))]
    (normalize [(comp t) (comp (+ t 1000000))])))
