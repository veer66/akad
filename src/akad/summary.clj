(ns akad.summary
  (:require [clj-http.client :as http]
            [akad.config :as conf]
            [akad.time :as tm]
            [akad.db :as db]
            [clojure.data.json :as json]))

(def cfg (conf/read-config))

(defn -main []
  (println (str "DATE" "," "TEMPERATURE-HIGH"))
  (with-open [db (db/open-storage)]
    (let [m (db/get-map db)]
      (doseq [date (tm/gen-dates)]
        (let [zdt (tm/gen-zdt date)
              zdt-str (tm/format-zdt zdt)
              weather (-> (.get m zdt-str)
                          (json/read-str :key-fn keyword))
              daily (first (:data (:daily weather)))]
          (println (str date "," (:temperatureHigh daily))))))))
