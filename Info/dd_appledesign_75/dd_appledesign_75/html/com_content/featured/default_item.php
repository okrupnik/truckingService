<?php
defined('_JEXEC') or die;
?>

<?php
Designer::load("Designer_Content");

$component = new DesignerContent($this, $this->params);
$article = $component->article('featured', $this->item, $this->item->params);
$params = array();
if (strlen($article->title)) {
    $params['header-text'] = $this->escape($article->title);
    if (strlen($article->titleLink))
        $params['header-link'] = $article->titleLink;
}
// Change the order of ""if"" statements to change the order of article metadata header items.
if (strlen($article->created))
    $params['date-icons'][] = $article->createdDateInfo($article->created);
if (strlen($article->modified))
    $params['date-icons'][] = $article->modifiedDateInfo($article->modified);
if (strlen($article->published))
    $params['date-icons'][] = $article->publishedDateInfo($article->published);

if (strlen($article->author))
    $params['author-icon'] = $article->authorInfo($article->author, $article->authorLink);
if ($article->printIconVisible)
    $params['print-icon'] = $article->printIconInfo();
if ($article->emailIconVisible)
    $params['email-icon'] = $article->emailIconInfo();
if ($article->editIconVisible)
    $params['edit-icon'] = $article->editIconInfo();
if (strlen($article->hits)) {
    $params['hits-icons'] = $article->hitsInfo($article->hits);
}

// Build article content
$content = '';
if (!$article->introVisible)
    $content .= $article->event('afterDisplayTitle');
$content .= $article->event('beforeDisplayContent');
if (strlen($article->images['intro']['image']))
    $params['data-image'] = $article->images['intro'];

$content .= $article->intro(funcBalanceTags($article->intro));
if (strlen($article->readmore)) {
    $params['readmore-text'] = $article->readmore;
    $params['readmore-link'] = $article->readmoreLink;
}
    //$content .= $article->readmore($article->readmore, $article->readmoreLink);
$content .= $article->event('afterDisplayContent');
$params['content'] = $content;

// Change the order of ""if"" statements to change the order of article metadata footer items.
if (count(($article->tags)) > 0)
    $params['tags-icon'] = $article->tags;
if (strlen($article->category))
    $params['category-icon'] = $article->categories($article->parentCategory, $article->parentCategoryLink, $article->category, $article->categoryLink);
// Render article
echo renderTemplateFromIncludes($this->articleTemplate, array($params));
?>
