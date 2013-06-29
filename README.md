<div id="table-of-contents">
<h2>Table of Contents</h2>
<div id="text-table-of-contents">
<ul>
<li><a href="#sec-1">1. Description</a>
<ul>
<li><a href="#sec-1-1">1.1. Features</a></li>
</ul>
</li>
<li><a href="#sec-2">2. Rationale</a></li>
<li><a href="#sec-3">3. Example</a></li>
<li><a href="#sec-4">4. Quickstart</a>
<ul>
<li><a href="#sec-4-1">4.1. Grab the mimified code</a></li>
<li><a href="#sec-4-2">4.2. Insert the container div</a></li>
<li><a href="#sec-4-3">4.3. Invoke the script</a></li>
<li><a href="#sec-4-4">4.4. Style the container</a></li>
<li><a href="#sec-4-5">4.5. Enjoy!</a></li>
</ul>
</li>
<li><a href="#sec-5">5. Configuration</a></li>
<li><a href="#sec-6">6. Development</a></li>
<li><a href="#sec-7">7. Default Styles</a>
<ul>
<li><a href="#sec-7-1">7.1. Example A</a></li>
<li><a href="#sec-7-2">7.2. Example B</a></li>
</ul>
</li>
<li><a href="#sec-8">8. Contributions</a></li>
</ul>
</div>
</div>

# Description

*Help Me Share* is a small javascript utility which adds *Share* buttons for
various services, i.e. *twitter* and *facebook*.

It embedded the native twitter and facebook buttons and does not use any
intermediary. As a result the sharing statistics are not given away to any
third-party except to the service that user clicked.

## Features

-   Each supported sharing service has a *plugin*.
    
    -   Currently there are only twitter and facebook plugins.

-   You can decide which plugins to use.

-   You can specify various configuration options for the plugins.

# Rationale

There is a lot of sharing services one may what to use. It is troublesome
to include all the buttons you may want manually. Hence, the project. You
need include one `<div>`, the javascript and call a function which will
create the buttons you want.

It is intended as a facility to simplify embedding and configuring third
party sharing services on your website.

It is worth noting that there are some alternatives, most notably
[Share This](http://en.wikipedia.org/wiki/ShareThis).
One feature that differentiate `Help Me Share` is that it uses the
sharing services directly. For instance, if your user clicks on the `Like`
button the request will go directly to facebook. There are no other
intermediaries to gather your visitors data.

Please see an example (See section ) or go straight to the quickstart (See section ).

# Example

![Example 1. Custom style (taken from <http://igor.kupczynski.info/>).](./docs/img/ex1.png)

An example of a custom style, live demo at <http://igor.kupczynski.info/>.

# Quickstart

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

The function `help_me_share.code.init` does the job of creating buttons. It
needs two arguments:

-   Id of the container,

-   Map of configuration options. For quickstart it is sufficient to give
    your twitter account name.

## Style the container

You need either to provide your own style or use one of the defaults. The
defaults can be downloaded from the following locations:

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

You can specify various configuration options in a mapped passed to
`help_me_share.core.init`. The is described below.

<table border="2" cellspacing="0" cellpadding="6" rules="groups" frame="hsides">


<colgroup>
<col class="left"/>

<col class="left"/>

<col class="left"/>

<col class="left"/>
</colgroup>
<thead>
<tr>
<th scope="col" class="left">Option</th>
<th scope="col" class="left">Default</th>
<th scope="col" class="left">Description</th>
<th scope="col" class="left">More info</th>
</tr>
</thead>

<tbody>
<tr>
<td class="left">'twitter-via'</td>
<td class="left">'twitter-username'</td>
<td class="left">Username to append to tweet.</td>
<td class="left">'via' in <https://dev.twitter.com/docs/tweet-button#properties></td>
</tr>


<tr>
<td class="left">'twitter-size'</td>
<td class="left">'medium'</td>
<td class="left">Size of the tweeter button.</td>
<td class="left">'size' in <https://dev.twitter.com/docs/tweet-button#properties></td>
</tr>


<tr>
<td class="left">'twitter-count'</td>
<td class="left">'horizontal'</td>
<td class="left">Count box position.</td>
<td class="left">'count' in <https://dev.twitter.com/docs/tweet-button#properties></td>
</tr>


<tr>
<td class="left">'twitter-dnt'</td>
<td class="left">'false'</td>
<td class="left">Opt out of tailored tweeter tracking.</td>
<td class="left"><https://dev.twitter.com/docs/tweet-button#optout></td>
</tr>


<tr>
<td class="left">'facebook-send'</td>
<td class="left">'false'</td>
<td class="left">If the 'send' button should be included together with the 'like' button.</td>
<td class="left">'send' in <https://developers.facebook.com/docs/reference/plugins/like></td>
</tr>


<tr>
<td class="left">'facebook-layout'</td>
<td class="left">'button<sub>count'</sub></td>
<td class="left">Layout of the button, i.e. where to put the count box.</td>
<td class="left">'layout' in <https://developers.facebook.com/docs/reference/plugins/like></td>
</tr>


<tr>
<td class="left">'facebook-width'</td>
<td class="left">'100'</td>
<td class="left">The width of the 'like' button.</td>
<td class="left">'width' in <https://developers.facebook.com/docs/reference/plugins/like></td>
</tr>


<tr>
<td class="left">'facebook-locale'</td>
<td class="left">'en<sub>US'</sub></td>
<td class="left">Language of the 'like' button.</td>
<td class="left"><https://developers.facebook.com/docs/reference/plugins/like></td>
</tr>


<tr>
<td class="left">'facebook-show-faces'</td>
<td class="left">'false'</td>
<td class="left">Whether to display profile photos in 'standard' layout.</td>
<td class="left">'show<sub>faces'</sub> in <https://developers.facebook.com/docs/reference/plugins/like></td>
</tr>


<tr>
<td class="left">'plugins'</td>
<td class="left">['twitter', 'facebook']</td>
<td class="left">Plugins to use.</td>
<td class="left">&#xa0;</td>
</tr>
</tbody>
</table>

# Development

Feel free to hack or contribute. The code is written in [clojurescript](https://github.com/emezeske/clojurescript) and
can be build via [leiningen](https://github.com/technomancy/leiningen). Leiningen is the only perquisite, make sure to
have it installed.

Steps to build help-me-share from source.

1.  Clone the code
    
        $ git clone https://github.com/puszczyk/help-me-share.git help-me-share

2.  Build it
    
        $ cd help-me-share
        $ lein cljsbuild clean
        $ $ lein cljsbuild once

As a result you will have to files: `resources/public/js/hms.js` and
`resources/public/js/hms.min.js`. The former is readable javascript for
development and debugging purposes and the latter is mimified,
[google closure](https://developers.google.com/closure/compiler/) compiled and optimized version for a production use.

You can easily test the code by putting the `resources/public` directory
under a DOCUMENT ROOT of a web server or even use a simple python web
server.

    resources/public$ python -m SimpleHTTPServer 8888

Assuming that you used the python webserver, just go to
<http://localhost:8888/index-dev.html> to visit the development
version of the code. Please note that there are no stylesheets applied. To
see the default styles in action on the production version go to
<http://localhost:8888/index.html> and <http://localhost:8888/index-fixed.html>.

Enjoy!

# Default Styles

## Example A

![Example 2. Default style - embedded on a page.](./docs/img/ex2.png)

Default style - button are embedded on a fixed position in relation to the
site content.

## Example B

![Example 3. Default style - floating on the left.](./docs/img/ex3.png)

Alternative style - buttons are on the a fixed position in relation to
the browser frame and appear to be floating over the site content.

# Contributions

We are on EPL, please feel free to fork this repository and contribute. You
can also raise any issues or suggest improvements though the *issues*
interface <https://github.com/puszczyk/help-me-share/issues>.

Feedback is appreciated!
