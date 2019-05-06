(ns akad.download
  (:require [clj-http.client :as http]
            [akad.config :as conf]
            [akad.time :as tm]
            [akad.db :as db]))

(def cfg (conf/read-config))

(defn gen-url [zdt-str]
  (str (:prefix cfg)
         (:api-key cfg) "/"
         (:lat cfg) ","
         (:lon cfg) ","
         zdt-str
         "/?units=" (:units cfg)))

(defn -main []
  (with-open [db (db/open-storage)]
    (let [m (db/get-map db)]
      (doseq [zdt-str (tm/gen-zdt-strs)]
        (let [url (gen-url zdt-str)
              forecast-res (http/get url)]
          (println zdt-str)
          (if (= 200 (:status forecast-res))
            (.put m zdt-str (:body forecast-res))
            (println "Cannot get data")))))))
