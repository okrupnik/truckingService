<?php
function product_desc_3($productItems) {
?>
    <?php if (isset($productItems->productDesc)) : ?>
<div class=" bd-productdesc-13">
    <?php if (property_exists($productItems->productDesc, 'isFull')) :
        echo $productItems->productDesc->desc;
    else :
        echo shopFunctionsF::limitStringByWord($productItems->productDesc->desc, 40, '...');
    ?>
    <?php endif; ?>
</div>
<?php endif; ?>
<?php
}