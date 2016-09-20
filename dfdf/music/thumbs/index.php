<link rel="stylesheet" href="http://music.libmill.com/admin/templates/template/css.css" type="text/css" />
<?php
$thumb=$_GET['thumb']; 
$w=$_GET['w'];$h=$_GET['h'];$q=$_GET['q']; 
$images=$_SERVER['DOCUMENT_ROOT'].'/thumbs/thumbs/cache/w'.$w.'h'.$h.'/'.$thumb;
if(file_exists($images))
{
	header('Location:/thumbs/thumbs/cache/w'.$w.'h'.$h.'/'.$thumb);
	exit();      
}
else
{
    include 'phpThumb/phpthumb.class.php'; 
	include '../lib/function.php'; 
    $phpThumb=new phpThumb();
	$image=$_SERVER['DOCUMENT_ROOT'].'/'.$thumb;
    $phpThumb->setSourceFilename($image);   
    $phpThumb->setParameter('w',$w);   
    $phpThumb->setParameter('h',$h);  
	$phpThumb->setParameter('q',$q); 
    if($phpThumb->GenerateThumbnail()) 
	{
        @mkdir(dirname('thumbs/cache/w'.$w.'h'.$h.'/'.$thumb),0777,true);   
        if($phpThumb->RenderToFile('thumbs/cache/w'.$w.'h'.$h.'/'.$thumb)) 
		{
			//header('Location:/thumbs/thumbs/cache/w'.$w.'h'.$h.'/'.$thumb);
			//exit();
			tips('成功！');
		}
	}   
}
?>