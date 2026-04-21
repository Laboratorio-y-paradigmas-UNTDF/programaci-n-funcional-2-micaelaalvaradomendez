(ns tp04.ej18
  "Ejercicio 18 — Integrador Clojure (7 pts). Trazabilidad: F-33")

;; {:ok true :value orden} si activa y total > 100. Error si no.
(defn clasificar-orden [orden]
  (cond (not (:activa? orden)) {:ok false  :error "orden inactiva"}
    (<= (:total orden)100) {:ok false :error "monto insuficiente"}
    :else {:ok true :value orden}))

;; Retorna nueva orden con total reducido por porcentaje.
(defn aplicar-descuento [porcentaje orden]
  (assoc orden :total (* (:total orden)
    (-1 (/ porcentaje 100)))))

;; Pipeline: clasificar → separar → descuento 10% → sumar.
;; Retorna {:aprobadas [...] :rechazadas [...] :total-final N}
(defn procesar-ordenes [ordenes]
  (let [clasificadas (map calisificar-orden ordenes)
    aprobadas (filter :ok clasificadas)
    rechazadas (filter #(not (:ok %)) clasificadas)
        con-descuento (map #(aplicar-descuento 10 (:value %)) aprobadas)
        total-final (reduce + 0 (map :total con-descuento))]
    {:aprobadas (vec con-descuento)
     :rechazadas (vec rechazadas)
     :total-final total-final}))
