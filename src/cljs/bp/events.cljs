(ns bp.events
  (:require [re-frame.core :as re-frame]
            [bp.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame.core/reg-event-fx
  :jump-time
  (fn [coeffects event]
    (let [time (second event)
          db   (:db coeffects)]
      {:db (-> db
               (assoc :time time)
               (assoc :playing 1)
               (update-in [:jump-seq] inc))})))
