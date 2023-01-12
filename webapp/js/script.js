$(document).ready(function(){
	var obj = $(".removeUser");
	var obj2 = $(".addUser");
	var obj3 = $(".removeGroup");
	var obj4 = $(".addGroup");
	var obj5 = $(".globalRemoveUser");
	var obj6 = $(".globalAddUser");
	var obj7 = $(".readTips");
	obj.on("click", function() {
		$(".addRemoveUser").append("<div class='form__row'><textarea class='flyout' placeholder='Bitte fügen Sie den Namen des zu löschenden Users ein'></textarea></div><div class='form__row'><input type='button' class='finalRemove submit__btn' name='User entfernen' value='User aus Gruppe entfernen'/></div>");
		$(".finalRemove").on("click", function(){
			var uname = $(".flyout").val();
			var gn = getUrlParameter("gn");
			$.post("groupPage.jsp", {usr: uname, act: "remove", gn: gn}, function(data){
				$(".addRemoveUser").empty();
				$(".addRemoveUser").append("<p> Der User "+uname+" wurde aus der Gruppe entfernt.");
				$("ul").find("#"+uname).remove();
			})
		});
	});
	obj2.on("click", function() {
		$(".addRemoveUser").append("<div class='form__row'><textarea class='flyout' placeholder='Bitte fügen Sie den Namen des Users ein, den Sie hinzufügen möchten'></textarea></div><div class='form__row'><input type='button' class='manAdd submit__btn' name='User hinzufuegen' value='User zu Gruppe hinzufügen'/></div>");
		$(".manAdd").on("click", function(){
			var uname = $(".flyout").val();
			var gn = getUrlParameter("gn");
			$.post("groupPage.jsp", {usr: uname, act: "add", gn: gn}, function(data){
				$(".addRemoveUser").empty();
				$(".addRemoveUser").append("<p> Der User "+uname+" wurde der Gruppe hinzugefügt.");
				$("ul").append("<li id='"+uname+"'>"+uname+"</li>");
			})
		});
	});
	obj3.on("click", function() {
		$(".addRemoveGroup").append("<div class='form__row'><textarea class='flyout' placeholder='Bitte fügen Sie den Namen der zu löschenden Gruppe ein'></textarea></div><div class='form__row'><input type='button' class='finalRemoveGroup submit__btn' name='Gruppe entfernen' value='Gruppe entfernen'/></div>");
		$(".finalRemoveGroup").on("click", function(){
			var gname = $(".flyout").val();
			$.post("adminPage.jsp", {act: "remove", gn: gname}, function(data){
				$(".addRemoveGroup").empty();
				$(".addRemoveGroup").append("<p> Die Gruppe "+gname+" wurde aus der Gruppe entfernt.");
				$("ul").find("#"+gname).remove();
			})
		});
	});
	obj4.on("click", function() {
		$(".addRemoveGroup").append("<div class='form__row'><textarea class='pw' placeholder='Bitte fügen Sie den Namen und das Passwort der Gruppe ein, die Sie hinzufügen möchten'></textarea></div><div class='form__row'><textarea class='flyout' placeholder='Musteruser'></textarea></div><div class='form__row'><input type='button' class='manAddGroup submit__btn' name='Gruppe hinzufuegen' value='Gruppe hinzufügen'/></div>");
		$(".manAddGroup").on("click", function(){
			var gname = $(".flyout").val();
			var password = $(".pw").val();
			$.post("adminPage.jsp", {act: "add", gn: gname, gpwd: password}, function(data){
				$(".addRemoveGroup").empty();
				$(".addRemoveGroup").append("<p> Die Gruppe "+gname+" wurde hinzugefügt.");
				$("ul:first").append("<li id='"+gname+"'>"+gname+"</li>");
			})
		});
	});
	obj5.on("click", function() {
		$(".addRemoveUser").append("<div class='form__row'><textarea class='flyout' placeholder='Bitte fügen Sie den Namen des zu löschenden Users ein'></textarea></div><div class='form__row'><input type='button' class='finalRemove submit__btn' name='User entfernen' value='User aus Gruppe entfernen'/></div>");
		$(".finalRemove").on("click", function(){
			var uname = $(".flyout").val();
			$.post("adminPage.jsp", {uAct: "remove", un: uname}, function(data){
				$(".addRemoveUser").empty();
				$(".addRemoveUser").append("<p> Der User "+uname+" wurde entfernt.");
				$("ul").find("#"+uname).remove();
			})
		});
	});
	obj6.on("click", function() {
		$(".addRemoveUser").append("<div class='form__row'><textarea class='flyout' placeholder='Bitte fügen Sie den Namen des Users ein, den Sie hinzufügen möchten'></textarea></div><div class='form__row'><input type='button' class='manAdd submit__btn' name='User hinzufuegen' value='User hinzufügen'/></div>");
		$(".manAdd").on("click", function(){
			var uname = $(".flyout").val();
			console.log("Uname "+uname);
			$.post("adminPage.jsp", {uAct: "add", un: uname}, function(data){
				$(".addRemoveUser").empty();
				$(".addRemoveUser").append("<p> Der User "+uname+" wurde mit dem Passwort 12345 hinzugefügt.");
				$("ul:last").append("<li id='"+uname+"'>"+uname+"</li>");
			})
		});
	});
	obj7.on("click", function(){
		$.post("adminPage.jsp", {final: "readTips"}, function(data){
			//$(".ranking").css(visiblity, "visible");
			$(".form__row:first").append("<p>Die Tipps wurden ausgelesen</p>");
		})
	});
});

function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
        }
    }
};
