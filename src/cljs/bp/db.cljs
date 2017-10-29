(ns bp.db
  (:require [bp.chords]))

(def default-db
  {:video-link (:video-link (second (first bp.chords/chord-db)))
   :time 0
   :jump-seq 0
   :playing 0
   :cur-chords (first (first bp.chords/chord-db))})
