<?php
/**
转换url
*/
function smarty_modifier_url($string)
{
    return str_replace('lovemusic.cc','music.libmill.com',$string);
} 

?>
