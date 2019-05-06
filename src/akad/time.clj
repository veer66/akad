(ns akad.time
  (:require [java-time :as jt]
            [akad.config :as conf])
  (:import java.time.temporal.ChronoField
           java.time.format.DateTimeFormatter))

(def cfg (conf/read-config))

(def dt-format (DateTimeFormatter/ofPattern "yyyy-MM-dd'T'HH:mm:ssZ"))

(defn format-zdt [zdt]
  (.format zdt dt-format))

(defn gen-zdt [dt]
  (let [d (.get dt ChronoField/DAY_OF_MONTH)
        m (.get dt ChronoField/MONTH_OF_YEAR)
        y (.get dt ChronoField/YEAR)
        zdt (jt/zoned-date-time y m d (:default-hour cfg) 0)
        zone (:zone cfg)
        zdt (jt/with-zone zdt zone)]
    zdt))

(defn today []
  (jt/local-date))

(defn gen-date [today day-num]
  (let [offset (jt/days day-num)
        date (jt/minus today offset)]
    date))

(defn gen-dates []
  (let [today (today)
        days (range 0 (:days-back cfg))]
    (->> days
         (map #(gen-date today %)))))

(defn gen-zdt-strs []
  (->> (gen-dates)
       (map gen-zdt)
       (map format-zdt)))
