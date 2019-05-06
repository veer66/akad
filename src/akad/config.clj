(ns akad.config
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

(defn read-config []
  (with-open [r (io/reader "config.edn")]
    (edn/read (java.io.PushbackReader. r))))
