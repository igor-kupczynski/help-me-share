(ns help-me-share.core
  (:require-macros [hiccups.core :as h])
  (:require [hiccups.runtime :as hiccupsrt]
            [domina :as dom]
            [domina.css :as cdom]))


;; Utils
(defn js-log
  "Logs the passed object to js console. Use only during the development."
  [item]
  (.log js/console (pr-str item)))


(defn extract-opt
  "Extracts a specific option. If not specified by user use the defaults."
  [param default-opts user-opts]
  (if-let [user-opt (aget user-opts (name param))]
    user-opt
    (get default-opts param)))


(defn extract-opts
  "Parses the options specified by the user merging them with the defaults."
  [default-opts user-opts]
  (let [params (keys default-opts)
        values (doall (map #(extract-opt % default-opts user-opts) params))]
    (zipmap params values)))



;;;;  Common
(defmulti build-plugin-button
  "Builds a button for given plugin"
  identity)


(defmulti pre-button-appended
  "A handler invoked before a plugin button is built"
  identity)


(defmulti post-button-appended
  "A handler invoked after a plugin button is built"
  identity)


;; defaults
(defmethod pre-button-appended :default [plugin opts])
(defmethod post-button-appended :default [plugin opts])


;;;; Twitter
(defmethod build-plugin-button :twitter [plugin opts]
  (h/html [:div [:a {:href "https://twitter.com/share"
                     :class "twitter-share-button"
                     :data-via (:twitter-via opts)
                     :data-size (:twitter-size opts)
                     :data-count (:twitter-count opts)
                     :data-dnt (:twitter-dnt opts)} "Tweet"]]))


;; This is more-or-less a direct translation from twitter docs. Not very clojurish, but does the job.
(defn twitter-internal [d, s, id]
  (let [fjs (dom/single-node (cdom/sel s))
        proto (if (re-find #"http:" (aget d "location")) "http" "https")
        twitter-src (str proto "://platform.twitter.com/widgets.js")]
    (if-not (dom/by-id id)
      (let [js (.createElement d s)
            fjs-parent (aget fjs "parentNode")]
        (aset js "id" id)
        (aset js "src" twitter-src)
        (.insertBefore fjs-parent js fjs)))))


(defmethod post-button-appended :twitter [plugin opts]
  (twitter-internal js/document "script" "twitter-wjs"))



;;;; Facebook
(defmethod pre-button-appended :facebook [plugin opts]
  (let [fb-root (h/html [:div {:id "fb-root"}])
        body (cdom/sel "body")]
    (dom/prepend! body fb-root)))


(defmethod build-plugin-button :facebook [plugin opts]
  (h/html [:div {:class "fb-like"
                 :data-send (:facebook-send opts)
                 :data-layout (:facebook-layout opts)
                 :data-width (:facebook-width opts)
                 :data-show-faces (:facebook-show-faces opts)}]))



(defn facebook-internal [d, s, id, locale]
  (let [fjs (dom/single-node (cdom/sel s))]
    (if-not (dom/by-id id)
      (let [js (.createElement d s)
            fjs-parent (aget fjs "parentNode")
            facebook-src (str "//connect.facebook.net/" locale "/all.js#xfbml=1")]
        (aset js "id" id)
        (aset js "src" facebook-src)
        (.insertBefore fjs-parent js fjs)))))


(defmethod post-button-appended :facebook [plugin opts]
  (facebook-internal js/document "script" "facebook-jssdk" (:facebook-locale opts)))



;;;; API
(def default-opts {:twitter-via "twitter-username"
                   :twitter-size "medium"
                   :twitter-count "horizontal"
                   :twitter-dnt "false"
                   :facebook-send "false"
                   :facebook-layout "button_count"
                   :facebook-width "100"
                   :facebook-locale "en_US"
                   :facebook-show-faces "false"
                   :plugins ["twitter" "facebook"]})


(defn wrap-button
  "Wraps button in div" [btn]
  (h/html [:li {:class "hms-plugin-button"} btn]))


(defn append-plugin!
  "Appends a plugin to the container"
  [parent plugin opts]
  (let [button (wrap-button (build-plugin-button plugin opts))]
    (pre-button-appended plugin opts)
    (dom/append! parent button)
    (post-button-appended plugin opts)))


(defn prepare-container!
  "Creates the struture for the container and returns it"
  [container-id]
  (let [container (dom/by-id container-id)
        ul-id (str "ul-" container-id)]
    (dom/add-class! container "hms-container")
    (dom/append! container (h/html [:ul {:id ul-id :class "hms-plugins"}]))
    (dom/by-id ul-id)))


(defn ^:export init
  "Initializes the plugin container"
  [container-id user-opts]
  (if (and js/document
        (aget js/document "getElementById"))
    (let [opts (extract-opts default-opts user-opts)
          container (prepare-container! container-id)]
      (doseq [plugin (:plugins opts)]
        (append-plugin! container (keyword plugin) opts)))))


