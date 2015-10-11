$(function(){
	autocompleteoff();
	initloginpanel();
});
function autocompleteoff(){
	$(':input').attr('autocomplete','off');
}
function initloginpanel(){
	$('#login-panel').css('margin-top',(document.body.scrollHeight/5)+'px');
}