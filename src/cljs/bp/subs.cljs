(ns bp.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :cur-chords
 (fn [db]
   (:cur-chords db)))

(defn time-to-sec [time]
  (let [ts (clojure.string/split (str time) ":")
        ts (map js/parseInt ts)]
    (case (count ts)
      1 time
      2 (let [[min sec] ts] (+ (* min 60) sec))
      3 (let [[hr min sec] ts] (+ (* hr 3600) (* min 60) sec)))))

(re-frame/reg-sub
 :video-link
 (fn [db]
   (let [{:keys [video-link time jump-seq playing]} db]
    (str video-link
         "?start=" (time-to-sec time)
         "&autoplay=" playing
         "&jump-seq=" jump-seq))))

