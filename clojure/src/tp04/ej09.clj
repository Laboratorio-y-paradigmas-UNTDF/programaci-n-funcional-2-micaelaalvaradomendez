(ns tp04.ej09
  "Ejercicio 9 — Validadores con currying (5 pts). Trazabilidad: F-18"
  (:require [clojure.string :as str]))

;; Retorna fn que valida value con pred. Ok → {:status :ok :value val}, error → {:status :error :error msg}.
(defn make-validator [pred error-msg]
  (fn [val] (if (pred val)
      {:status :ok :value val}
      {:status :error :error error-msg})))

;; Aplica validators en secuencia; para en el primer error.
(defn validate-field [value & validators]
  (reduce (fn [result validator]
      (if (= :error (:status result))
        result (validator (:value result))))
    {:status :ok :value value} validators))

(def validate-not-empty
  (make-validator
    (fn [val] (not (empty? (str/trim (str val)))))
    "campo vacío"))

(def validate-email-format
  (make-validator
    (fn [val] (re-matches #".+@.+\..+" (str val)))
    "email inválido"))
