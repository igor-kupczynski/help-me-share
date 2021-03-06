
# Description

*Help Me Share* is a small javascript utility which adds *Share* buttons for
various services, i.e. *twitter* and *facebook*.

It embeds the native twitter and facebook buttons and does not use any
intermediary. As a result the sharing statistics are not given away to any
third-party except for the service that user clicked.

## Features

-   Each supported sharing service has a *plugin*.
    
    -   Currently there are only twitter and facebook plugins.

-   You can decide which plugins to use.

-   You can specify various configuration options for the plugins.

# Rationale

*Help Me Share* is intended as a facility to simplify embedding and
configuring third party sharing services on your website.

There is a lot of sharing services one may what to use. It is troublesome to
include all the buttons you may want manually. This is why the project was
born. To install *Help Me Share* you need only include one `<div>`, the
javascript source and then call a function creating buttons you want.

It is worth noting that there are some alternatives, e.g.
[Share This](http://en.wikipedia.org/wiki/ShareThis). What
differentiates *Help Me Share* is that it connects with the sharing
services directly. For instance, if a user clicks on the `Like`
button, the request will go directly to facebook. There are no other
intermediaries to gather (meta)data on your visitors.

Please see an [example](#example) or go straight to the [quickstart](#quickstart).

# <a id="example"></a>Example

![Example 1. Custom style (taken from <http://igor.kupczynski.info/>).](./docs/img/ex1.png)

An example of a custom style, live demo at <http://igor.kupczynski.info/>.

# <a id="quickstart"></a>Quickstart

## Grab the mimified code

Download if from: 
<https://raw.github.com/puszczyk/help-me-share/master/resources/stable/hms.min.js>

## Insert the container div

Put the following tag in the place where you want the buttons to show.

    <div id="hms-container"></div>

## Invoke the script

Include the script near the end of your page, e.g. just before the closing
`</body>` tag and configure the options.

    <script src="js/hms.js"></script>
    <script>
    help_me_share.core.init(
           'hms-container',
           {'twitter-via': 'test-user'}
    );
    </script>

The function `help_me_share.code.init` creates the buttons. It needs two
arguments:

-   Id of the container,

-   Map of configuration options. For quickstart it is sufficient to give
    your twitter account name.

## Style the container

You need either to provide your own style or use a default. The defaults
can be downloaded from the following locations:

-   <https://raw.github.com/puszczyk/help-me-share/master/resources/public/css/hms.css>

-   <https://raw.github.com/puszczyk/help-me-share/master/resources/public/css/hms-fixed.css>

And then included on the page within the meta tag:

    <link rel="stylesheet" type="text/css" href="css/hms.css"/>

## Enjoy!

![Share on Twitter](./docs/img/twitter-clicked.png)

Twitter button clicked.

![Share on Facebook](./docs/img/facebook-clicked.png)

Facebook button clicked.

# Configuration

You can specify various configuration options in a map passed to
`help_me_share.core.init`. The options are described below.

<table border="2" cellspacing="0" cellpadding="6" rules="groups" frame="hsides">


<colgroup>
<col class="left"/>

<col class="left"/>

<col class="left"/>
</colgroup>
<thead>
<tr>
<th scope="col" class="left">Option</th>
<th scope="col" class="left">Default</th>
<th scope="col" class="left">Description</th>
</tr>
</thead>

<tbody>
<tr>
<td class="left">'twitter-via'</td>
<td class="left">'twitter-username'</td>
<td class="left">Username to append to tweet. See <code>via</code> in <a href="https://dev.twitter.com/docs/tweet-button#properties">Twitter docs</a>.</td>
</tr>


<tr>
<td class="left">'twitter-size'</td>
<td class="left">'medium'</td>
<td class="left">Size of the tweeter button. See <code>size</code> in <a href="https://dev.twitter.com/docs/tweet-button#properties">Twitter docs</a>.</td>
</tr>


<tr>
<td class="left">'twitter-count'</td>
<td class="left">'horizontal'</td>
<td class="left">Count box position. See <code>count</code> in <a href="https://dev.twitter.com/docs/tweet-button#properties">Twitter docs</a>.</td>
</tr>


<tr>
<td class="left">'twitter-dnt'</td>
<td class="left">'false'</td>
<td class="left">Opt out of tailored tweeter tracking. See  <a href="https://dev.twitter.com/docs/tweet-button#optout">Twitter docs</a>.</td>
</tr>


<tr>
<td class="left">'facebook-send'</td>
<td class="left">'false'</td>
<td class="left">If the 'send' button should be included together with the 'like' button. See <code>send</code> in <a href="https://developers.facebook.com/docs/reference/plugins/like">Facebook docs</a>.</td>
</tr>


<tr>
<td class="left">'facebook-layout'</td>
<td class="left">'button count'</td>
<td class="left">Layout of the button, i.e. where to put the count box. See <code>layout</code> in <a href="https://developers.facebook.com/docs/reference/plugins/like">Facebook docs</a>.</td>
</tr>


<tr>
<td class="left">'facebook-width'</td>
<td class="left">'100'</td>
<td class="left">The width of the 'like' button. See <code>width</code> in <a href="https://developers.facebook.com/docs/reference/plugins/like">Facebook docs</a>.</td>
</tr>


<tr>
<td class="left">'facebook-locale'</td>
<td class="left">'en US'</td>
<td class="left">Language of the 'like' button. See <a href="https://developers.facebook.com/docs/reference/plugins/like">Facebook docs</a>.</td>
</tr>


<tr>
<td class="left">'facebook-show-faces'</td>
<td class="left">'false'</td>
<td class="left">Whether to display profile photos in 'standard' layout. See <code>show faces</code> in <a href="https://developers.facebook.com/docs/reference/plugins/like">Facebook docs</a>.</td>
</tr>


<tr>
<td class="left">'plugins'</td>
<td class="left">['twitter', 'facebook']</td>
<td class="left">Plugins to use.</td>
</tr>
</tbody>
</table>

# Development

Feel free to hack or contribute. The code is written in [clojurescript](https://github.com/emezeske/clojurescript) and
can be build via [leiningen](https://github.com/technomancy/leiningen). Leiningen and JDK are the only perquisites, make
sure to have them installed.

Steps to build *Help Me Share* from source.

1.  Clone the code
    
        $ git clone https://github.com/puszczyk/help-me-share.git help-me-share

2.  Build it
    
        $ cd help-me-share
        $ lein cljsbuild clean
        $ lein cljsbuild once

As a result you will have to files: `resources/public/js/hms.js` and
`resources/public/js/hms.min.js`. The former is a readable javascript for
development and debugging, the latter is mimified, [google closure](https://developers.google.com/closure/compiler/) compiled
and optimized version for a production use.

You can easily test the code by putting the `resources/public` directory
under a DOCUMENT ROOT of a web server or even use a simple python web
server.

    resources/public$ python -m SimpleHTTPServer 8888

Assuming that your webserver is at localhost, just go to
<http://localhost:8888/index-dev.html> to visit the development version of the
code. Please note that there are no stylesheets applied. To see the default
styles in action go to the production version at
<http://localhost:8888/index.html> or <http://localhost:8888/index-fixed.html>.

# Default Styles

## Example A

![Example 2. Default style - embedded on a page.](./docs/img/ex2.png)

Default style - button are embedded on a fixed position within the site
content.

## Example B

![Example 3. Default style - floating on the left.](./docs/img/ex3.png)

Alternative style - buttons are on the a fixed position in relation to
the browser frame and appear to be floating over the site content.

# Contributions

*Help Me Share* is released under the EPL licence, please feel free to fork
this repository and contribute. You can also raise any issues or suggest
improvements though the *issues* interface
<https://github.com/puszczyk/help-me-share/issues>.

Your feedback is appreciated!
