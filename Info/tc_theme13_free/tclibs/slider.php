<?php
/**
* @subpackage  tc_theme13 Template
*/

defined('_JEXEC') or die;

$app = JFactory::getApplication();
$doc = JFactory::getDocument();//define path
$base_url = $this->baseurl;
$tpl_name = $this->template;

$caption         = $this->params->get ('caption');
$menu            = $this->params->get ('menu');
$slider	     = $this->params->get('slider');
$slides	     = $this->params->get('slides');
$container_height = $this->params->get('container_height');
$sliders_thumb1 	= $this->params->get('sliders_thumb1', '' );
$sliders_thumb2 	= $this->params->get('sliders_thumb2', '' );
$sliders_thumb3 	= $this->params->get('sliders_thumb3', '' );
$sliders_thumb4 	= $this->params->get('sliders_thumb4', '' );
$sliders_thumb5 	= $this->params->get('sliders_thumb5', '' );
$sliders_thumb6 	= $this->params->get('sliders_thumb6', '' );

if ($sliders_thumb1 || $sliders_thumb2 || $sliders_thumb3 || $sliders_thumb4 || $sliders_thumb5) {
// use images from template manager
} else {
// use default images
$sliders_thumb1 = $this->baseurl . '/templates/' . $this->template . '/slider/header1.jpg';
$sliders_thumb2 = $this->baseurl . '/templates/' . $this->template . '/slider/header2.jpg';
}

(($this->countModules('slider') && $slides == 2) || ($slides == 1) ?

$tcParams .= '<div id="elslide">
<div id="elasticslideshow" class="ei-slider" style="height:500px">
<ul class="ei-slider-large">'
.($sliders_thumb1 ? '<li class="slide_1"><img src="'.$sliders_thumb1.'"/>' : '')
.($sliders_thumb1 ? '</li>' : '')

.($sliders_thumb2 ? '<li class="slide_2"><img src="'.$sliders_thumb2.'"/>' : '')
.($sliders_thumb2 ? '</li>' : '')

.($sliders_thumb3 ? '<li class="slide_3"><img src="'.$sliders_thumb3.'"/>' : '')
.($sliders_thumb3 ? '</li>' : '')

.($sliders_thumb4 ? '<li class="slide_4"><img src="'.$sliders_thumb4.'"/>' : '')
.($sliders_thumb4 ? '</li>' : '')

.($sliders_thumb5 ? '<li class="slide_5"><img src="'.$sliders_thumb5.'"/>' : '')
.($sliders_thumb5 ? '</li>' : '').


'</ul>
<div class="ei_slider_thumbs">
<ul class="ei-slider-thumbs">
<li class="ei-slider-element"></li>'	
.($sliders_thumb1 ? '<li><a href="#"></a><img src="'.$sliders_thumb1.'"/></li>' : '')
.($sliders_thumb2 ? '<li><a href="#"></a><img src="'.$sliders_thumb2.'"/></li>' : '')
.($sliders_thumb3 ? '<li><a href="#"></a><img src="'.$sliders_thumb3.'"/></li>' : '')
.($sliders_thumb4 ? '<li><a href="#"></a><img src="'.$sliders_thumb4.'"/></li>' : '')
.($sliders_thumb5 ? '<li><a href="#"></a><img src="'.$sliders_thumb5.'"/></li>' : '').
'</ul> 
</div>' : '')

?>      