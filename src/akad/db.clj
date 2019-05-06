(ns akad.db
  (:require [akad.config :as conf])
  (:import org.mapdb.DB
           org.mapdb.DBMaker))

(def cfg (conf/read-config))

(defn open-storage []
  (.make (DBMaker/fileDB (:db-path cfg))))

(defn get-map [db]
  (.createOrOpen (.hashMap db (:map-name cfg))))
