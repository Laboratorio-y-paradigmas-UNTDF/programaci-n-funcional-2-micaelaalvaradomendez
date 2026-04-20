(ns tp04.ej16
  "Ejercicio 16 — DSL data-driven (5 pts). Trazabilidad: F-31"
  (:require [clojure.string :as str]))

;; Vector de reglas: {:field :name, :pred fn, :msg "..."}
(def user-rules
  [{:field :name :pred #(not (empty? (str/trim (str %))))
  :msg "nombre es obligatorio,"}
  {:field :email :pred #(str/includes? (str %) "@")
  :msg "email invalido"}
  {:field :age :pred #(>= % 18)
  :msg "debe ser mayor de 18"}])

;; Aplica todas las reglas a data. Retorna vector de {:field :error} (vacío si ok).
(defn validate [rules data]
  (filter identity (for [rule rules] (let [])))
  )

;; true si no hay errores.
(defn valid? [rules data]
  ;; TODO: implementar
  )
