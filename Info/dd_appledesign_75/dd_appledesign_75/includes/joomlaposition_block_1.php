<?php
function joomlaposition_block_1($caption, $content, $classes = '', $id = '')
{
    $hasCaption = (null !== $caption && strlen(trim($caption)) > 0);
    $hasContent = (null !== $content && strlen(trim($content)) > 0);
    $isPreview  = $GLOBALS['designer_settings']['is_preview'];
    if (!$hasCaption && !$hasContent)
        return '';
    if (!empty($id))
        $id = $isPreview ? (' data-block-id="' . $id . '"') : '';
    ob_start();
    ?>
    <div class=" bd-block-9 <?php echo $classes; ?>" <?php echo $id; ?>>
<div class="bd-container-inner">
    <?php if ($hasCaption) : ?>
    
    <div class=" bd-container-70 bd-tagstyles">
        <h4><?php echo $caption; ?></h4>
    </div>
    
<?php endif; ?>
    <?php if ($hasContent) : ?>
    
    <div class=" bd-container-71 bd-tagstyles">
        <?php echo funcPostprocessBlockContent($content); ?>
    </div>
    
<?php endif; ?>
</div>
</div>
    <?php
    return ob_get_clean();
}