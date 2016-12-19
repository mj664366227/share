<?php

function smarty_modifier_trim_simhei($string)
{
    return str_replace('宋体','Microsoft YaHei',str_replace('SimHei','Microsoft YaHei',$string));
} 

?>
