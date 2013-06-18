(defproject help-me-share "0.1.0"
  :description "A small javascript utility which adds 'Share' buttons for various services, i.e. twitter and facebook."
  :url "https://github.com/puszczyk/help-me-share"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [domina "1.0.1"]
                 [hiccups "0.2.0"]]


  :plugins [[lein-cljsbuild "0.3.0"]]

  :cljsbuild {
               :builds {
                         :dev {
                                :source-paths ["src/brepl" "src/cljs"]
                                :compiler {:output-to "resources/public/js/hms.js"
                                           :optimizations :whitespace
                                           :pretty-print true}}

                         :prod {
                                 :source-paths ["src/cljs"]
                                 :compiler {:output-to "resources/public/js/hms.min.js"
                                            :optimizations :advanced}}}})
