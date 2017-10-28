(ns bp.views
  (:require [re-frame.core :as re-frame]))

(def set-me-free-chords "[Intro]
0|Am

[Verse]
           D          C
16|You made a man out of me
              Am        
But you're not satisfied
         D                C
You make believe what you see
       Am
But never leave an eye
      D       C
Got a million things 
      Am
On my mind cause you..
C       D               Am
I don't know what we'll do


       D         C           Am
33|I only wanted to love you
         D        C          Am
You only wanted a taste
   D           C             Am
My ambition to woo you
            C            D
Was all the time that we wa...
Am                    C      Am
48|Time that we wasted

[Chorus]
              D      Am           C     Am
51|Why don't you set me free? yeah
              D      Am           C     Am
Why don't you set me free?
              D      Am           C     Am
Why don't you set me free? yeah
              D      Am           C     Am
Why don't you set me free?

[Break]
67|Am


[Verse]
          D           C
75|And every time that I think 
        Am
About the better days
         D            C
Remember how we would kiss
           Am
When we were high on haze
D        C
We never knew 
          Am               C
'bout the way things work
        D           Am
I still need to forget


               D         C          Am
92|Cause when you wanted protection
            D           C           Am
You used to call out my name
             D          C           Am
And all this changing direction
             C           D
Ain't making me feel the same...
Am                     C      Am
Me feel the same about you


[Chorus]
              D      Am           C     Am
111|Why don't you set me free? yeah
              D      Am           C     Am
Why don't you set me free?
              D      Am           C     Am
Why don't you set me free? yeah
              D      Am   
Why don't you set me free?


C              Am    D      Am 
125|All you got to do is set me free
C              Am    D      Am 
All you got to do is set me free
C              Am    D      Am 
All you got to do is set me free
C              Am    D      Am 
All you got to do is set me free


[Break]   ***down strums (same pattern for latter part of the break)
143|C   C   Am  Am    D   D  Am   Am
C   C   Am  Am    D   D  Am   Am
C   C   Am  Am    D   D  Am   Am
C   C   Am  Am    D   D  Am   Am
 
C              Am    D      Am 
159|All you got to do is set me free
C              Am    D      Am 
All you got to do is set me free
C              Am    D      Am 
All you got to do is set me free
C              Am    D      Am 
All you got to do is set me free

[End Break]

C              Am    D      Am 
177|All you got to do is set me free
C              Am    D      Am 
All you got to do is set me free
C              Am    D      Am 
All you got to do is set me free
C              Am    D      Am 
All you got to do is set me free

C   Am   D  Am ('til fade)")

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
  (fn []
    (let [;; split lines
          chords (clojure.string/split-lines chords)
          ;; replace "\n" with <br/>
          chords (interpose [:br] chords)
          ;; add jump points
          chords (map parse-jump-point chords)]
      [:pre
       chords])))

(defn main-panel []
  (let [video-link (re-frame/subscribe [:video-link])]
    (fn []
      [:div
       [:iframe
        {:width 560 :height 315
         :src @video-link
         :frameborder 0
         :allowfullscreen true}]
       [interactive-chords set-me-free-chords]])))
