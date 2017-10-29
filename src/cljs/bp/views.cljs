(ns bp.views
  (:require
   [bp.chords]
   [re-frame.core :as re-frame]))

(defn jump-point [time link]
  (fn []
    [:a {:href "#"
         :on-click (fn [e]
                     (.preventDefault e)
                     (re-frame/dispatch [:jump-time time]))}
     link]))

(defn parse-jump-point [line]
  (if (and (string? line)
           (clojure.string/includes? line "|"))
    (let [[time line] (clojure.string/split line "|")]
      [jump-point time line])
    line))

(defn interactive-chords [chords]
  (let [;; split lines
        chords (clojure.string/split-lines chords)
        ;; replace "\n" with <br/>
        chords (interpose [:br] chords)
        ;; add jump points
        chords (map parse-jump-point chords)]
    [:pre
     chords]))

(defn select-songs [chord-db]
  (let [cur-chords (re-frame/subscribe [:cur-chords])]
    [:div
     [:select
      {:value @cur-chords
       :on-change (fn [e] (re-frame/dispatch [:update-chords (.. e -currentTarget -value)]))}
      (for [[k _] chord-db]
        [:option {:value k
                  :key k} k])]]))

(defn main-panel []
  (let [video-link (re-frame/subscribe [:video-link])
        cur-chords (re-frame/subscribe [:cur-chords])]
    (fn []
      [:div
       [select-songs bp.chords/chord-db]
       [:iframe
        {:width 560 :height 315
         :src @video-link
         :frameBorder 0
         :allowFullScreen true}]
       [interactive-chords (:chords (bp.chords/chord-db @cur-chords))]])))
