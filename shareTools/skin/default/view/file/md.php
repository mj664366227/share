<?php require view::dir().'head.php';?>
<?php view::load_css('editormd.min');?>
<?php view::load_js('editor.md/marked.min');?>
<?php view::load_js('editor.md/prettify.min');?>
<?php view::load_js('editor.md/raphael.min');?>
<?php view::load_js('editor.md/underscore.min');?>
<?php view::load_js('editor.md/sequence-diagram.min');?>
<?php view::load_js('editor.md/jquery.flowchart.min');?>
<?php view::load_js('editor.md/editormd.min');?>
<div id="editormd"></div>
<script type="text/javascript">
$(function() {
	$.get("test.md", function(markdown) {
		editormd.markdownToHTML("editormd", {
			markdown : markdown,     // 不设置时，从<script type="text/markdown">获取markdown文档
			mathjax : true,          // 默认不解析
			flowChart: true,         // 默认不解析
			sequenceDiagram : true,  // 默认不解析
		});
	});
});
</script>
<?php require view::dir().'foot.php';?>
