$(document).ready(function(){
	$("input").change(function(){
		cl = $(this).attr("class");
		console.log("class: "+cl);
		$("."+cl).prop('checked',false);
		console.log("this: "+$(this));
	    $(this).prop('checked',true);
	});
	
	$("form").on("submit", function(){
		alert("Vielen Dank für deine Tipps! Du kannst sie jederzeit über das Formular bearbeiten.");
	})
	
});
