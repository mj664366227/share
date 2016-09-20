<?php
//随机专辑
$random=get_my_array($conn,'select id,title,picture,artist from `somethingmusic_album` order by rand() limit 0,9');
$smarty->assign('random',$random);

//热门文章
$hot=get_my_array($conn,'select id,title,click from `somethingmusic_music_introduce` order by `click` desc limit 0,8');
$smarty->assign('hot',$hot);

//最近留言
$read_comment=get_my_array($conn,'select id,content_id,name,text from `somethingmusic_read_comment` order by `id` desc limit 0,8');
$smarty->assign('read_comment',$read_comment);
?>