(ns bp.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :video-link
 (fn [db]
   (let [{:keys [video-link time jump-seq playing]} db]
    (str video-link
         "?start=" time
         "&autoplay=" playing
         "&jump-seq=" jump-seq))))

