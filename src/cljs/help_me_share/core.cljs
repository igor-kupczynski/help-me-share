(ns help-me-share.core
  (:require-macros [hiccups.core :as h])
  (:require [hiccups.runtime :as hiccupsrt]
            [domina :as dom]))


;; Utils
(defn js-log [item]
  (.log js/console (pr-str item)))


(defn extract-opt [param default-opts user-opts]
  (if-let [user-opt (aget user-opts (name param))]
    user-opt
    (get default-opts param)))


(defn extract-opts [default-opts user-opts]
  (let [params (keys default-opts)
        values (doall (map #(extract-opt % default-opts user-opts) params))]
    (zipmap params values)))


;; Common
(defmulti build-dom-button identity)


;; Twitter
(defmethod build-dom-button :twitter [opts]
  (h/html [:div [:a {:href "https://twitter.com/share"
                     :class "twitter-share-button"
                     :data-via (:twitter-username opts)
                     :data-size "large"
                     :data-count "none"
                     :data-dnt "true"} "Tweet"]]))



;; API
(def default-opts {:twitter-username "twitter-username"
                   :plugins ["twitter"]})

(defn append-plugin! [parent plugin]
  (dom/append! parent (build-dom-button plugin)))



(defn ^:export init [user-opts]
  (if (and js/document
        (aget js/document "getElementById"))
    (let [opts (extract-opts default-opts user-opts)
          container (dom/by-id "hms-container")]
      (doseq [plugin (:plugins opts)]
        (append-plugin! container (keyword plugin))))))


