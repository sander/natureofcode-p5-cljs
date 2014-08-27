(ns natureofcode.core)

(enable-console-print!)

(defn init [setup draw]
  (doto js/window
    (aset "setup" setup)
    (aset "draw" draw)))
