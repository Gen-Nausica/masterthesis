/**
 * 
 */
$(document).ready(function(){
	/*
	 * React to clicks on generated nodes
	 * 1st level, 2nd level and 3rd level exists, chaanges to 2nd and 3rd level must be made
	 */
	
	//Node erster Ebene
	$(".expand").on("click", function(){
		var t = $(this).next(".secondLevel");
		var c = $(this).parent();
		console.log("C:");
		console.log(c);
		//check if node is expanded or not
		if($(t).is(":hidden")){
			//not expanded, show
			$(t).show();
			//change colors
			var tmp = $(c).find(".bigNode");
			console.log("tmp");
			console.log($(tmp));
			$(tmp).each(function(index, element){
				//change background-color of circles
				$(element).css("background-color","#6699ff");
				//change font-color of links
				var links = $(element).next(".secondLevel").find(".link");
				console.log(links);
				$(links).each(function(ind, el){
					console.log("in each");
					$(el).css("color","#6699ff");
				});
			});
			tmp = $(c).find(".middleNode");
			$(tmp).each(function(index, element){
				//change background-color of circles
				$(element).css("background-color","#6699ff");
				var links = $(element).next(".secondLevel").find(".link");
				console.log(links);
				$(links).each(function(ind, el){
					console.log("in each");
					$(el).css("color","#6699ff");
				});
			});
			tmp = $(c).find(".smallNode");
			$(tmp).each(function(index, element){
				//change background-color of circles
				$(element).css("background-color","#6699ff");
				var links = $(element).next(".secondLevel").find(".link");
				console.log(links);
				$(links).each(function(ind, el){
					console.log("in each");
					$(el).css("color","#6699ff");
				});
			});
			tmp = $(c).find(".biggerNode");
			$(tmp).each(function(index, element){
				//change background-color of circles
				$(element).css("background-color","#6699ff");
				var links = $(element).next(".secondLevel").find(".link");
				console.log(links);
				$(links).each(function(ind, el){
					console.log("in each");
					$(el).css("color","#6699ff");
				});
			});
			tmp = $(c).find(".smallestNode");
			$(tmp).each(function(index, element){
				//change background-color of circles
				$(element).css("background-color","#6699ff");
				var links = $(element).next(".secondLevel").find(".link");
				console.log(links);
				$(links).each(function(ind, el){
					console.log("in each");
					$(el).css("color","#6699ff");
				});
			});
			
			console.log("was hidden");
		}
		else {
			//expanded, hide
			$(t).hide();
			var tmp = $(c).find(".bigNode");
			$(tmp).each(function(index, element){
				//change background-color of circles
				$(element).css("background-color","#bbb");
				//change font-color of links
				var links = $(element).next(".secondLevel").find(".link");
				console.log(links);
				$(links).each(function(ind, el){
					console.log("in each");
					$(el).css("color","#bbb");
				});
			});
			tmp = $(c).find(".middleNode");
			$(tmp).each(function(index, element){
				//change background-color of circles
				$(element).css("background-color","#bbb");
				//change font-color of links
				var links = $(element).next(".secondLevel").find(".link");
				console.log(links);
				$(links).each(function(ind, el){
					console.log("in each");
					$(el).css("color","#bbb");
				});
			});
			tmp = $(c).find(".smallNode");
			$(tmp).each(function(index, element){
				//change background-color of circles
				$(element).css("background-color","#bbb");
				//change font-color of links
				var links = $(element).next(".secondLevel").find(".link");
				console.log(links);
				$(links).each(function(ind, el){
					console.log("in each");
					$(el).css("color","#bbb");
				});
			});
			tmp = $(c).find(".biggerNode");
			$(tmp).each(function(index, element){
				//change background-color of circles
				$(element).css("background-color","#bbb");
				//change font-color of links
				var links = $(element).next(".secondLevel").find(".link");
				console.log(links);
				$(links).each(function(ind, el){
					console.log("in each");
					$(el).css("color","#bbb");
				});
			});
			tmp = $(c).find(".smallestNode");
			$(tmp).each(function(index, element){
				//change background-color of circles
				$(element).css("background-color","#bbb");
				//change font-color of links
				var links = $(element).next(".secondLevel").find(".link");
				console.log(links);
				$(links).each(function(ind, el){
					console.log("in each");
					$(el).css("color","#bbb");
				});
			});
			console.log("was shown");
		}
	});
	
	//Node untere Ebenen
	$(".subNodeExpand").on("click", function(){
		var t = $(this).next(".thirdLevel");
		//check if node is expanded or not
		if($(t).is(":hidden")){
			//not expanded, show
			var w = $(this).width();
			console.log("Width of kreis: "+w);
			var p = $(this).parent().parent().parent().width();
			w = w+2*(p*0.1);
			console.log("Zwischenergebnis: "+w);
			w = w+(0.15*p);
			console.log("gesamtwidth: "+w);
			$(t).show();
			console.log("was hidden");
			$(t).css("display", "inline-block");
			$(t).css("text-align", "center");
			$(this).parent().css("margin-top","10%");
			$(this).parent().css("overflow","auto");
			$(this).parent().css("width",w);
			$(t).find(".subCat").css("float","none");
			var c = $(this).parent();
			var tmp = $(c).find(".bigNode");
			$(tmp).each(function(index, element){
				//change background-color of circles
				$(element).css("background-color","#ff6666");
				//change font-color of links
				var links = $(element).next(".thirdLevel").find(".link");
				console.log(links);
				$(links).each(function(ind, el){
					console.log("in each");
					$(el).css("color","#ff6666");
				});
			});
			tmp = $(c).find(".middleNode");
			$(tmp).each(function(index, element){
				//change background-color of circles
				$(element).css("background-color","#ff6666");
				//change font-color of links
				var links = $(element).next(".thirdLevel").find(".link");
				console.log(links);
				$(links).each(function(ind, el){
					console.log("in each");
					$(el).css("color","#ff6666");
				});
			});
			tmp = $(c).find(".smallNode");
			$(tmp).each(function(index, element){
				//change background-color of circles
				$(element).css("background-color","#ff6666");
				//change font-color of links
				var links = $(element).next(".thirdLevel").find(".link");
				console.log(links);
				$(links).each(function(ind, el){
					console.log("in each");
					$(el).css("color","#ff6666");
				});
			});
			tmp = $(c).find(".biggerNode");
			$(tmp).each(function(index, element){
				//change background-color of circles
				$(element).css("background-color","#ff6666");
				//change font-color of links
				var links = $(element).next(".thirdLevel").find(".link");
				console.log(links);
				$(links).each(function(ind, el){
					console.log("in each");
					$(el).css("color","#ff6666");
				});
			});
			tmp = $(c).find(".smallestNode");
			$(tmp).each(function(index, element){
				//change background-color of circles
				$(element).css("background-color","#ff6666");
				//change font-color of links
				var links = $(element).next(".thirdLevel").find(".link");
				console.log(links);
				$(links).each(function(ind, el){
					console.log("in each");
					$(el).css("color","#ff6666");
				});
			});
		}
		else {
			//expanded, hide
			$(t).hide();
			$(t).removeAttr("display");
			$(t).removeAttr("text-align");
			$(this).parent().css("margin-top","");
			console.log($(this).parent());
			$(this).parent().css("overflow","");
			$(this).parent().css("width","");
			$(t).find(".subCat").css("float","right");
			
			var c = $(this).parent();
			var tmp = $(c).find(".bigNode");
			$(tmp).each(function(index, element){
				//change background-color of circles
				$(element).css("background-color","#6699ff");
				//change font-color of links
				var links = $(element).next(".thirdLevel").find(".link");
				console.log(links);
				$(links).each(function(ind, el){
					console.log("in each");
					$(el).css("color","#6699ff");
				});
			});
			tmp = $(c).find(".middleNode");
			$(tmp).each(function(index, element){
				//change background-color of circles
				$(element).css("background-color","#6699ff");
				//change font-color of links
				var links = $(element).next(".thirdLevel").find(".link");
				console.log(links);
				$(links).each(function(ind, el){
					console.log("in each");
					$(el).css("color","#6699ff");
				});
			});
			tmp = $(c).find(".smallNode");
			$(tmp).each(function(index, element){
				//change background-color of circles
				$(element).css("background-color","#6699ff");
				//change font-color of links
				var links = $(element).next(".thirdLevel").find(".link");
				console.log(links);
				$(links).each(function(ind, el){
					console.log("in each");
					$(el).css("color","#6699ff");
				});
			});
			tmp = $(c).find(".biggerNode");
			$(tmp).each(function(index, element){
				//change background-color of circles
				$(element).css("background-color","#6699ff");
				//change font-color of links
				var links = $(element).next(".thirdLevel").find(".link");
				console.log(links);
				$(links).each(function(ind, el){
					console.log("in each");
					$(el).css("color","#6699ff");
				});
			});
			tmp = $(c).find(".smallestNode");
			$(tmp).each(function(index, element){
				//change background-color of circles
				$(element).css("background-color","#6699ff");
				//change font-color of links
				var links = $(element).next(".thirdLevel").find(".link");
				console.log(links);
				$(links).each(function(ind, el){
					console.log("in each");
					$(el).css("color","#6699ff");
				});
			});
			
			console.log("was shown");
		}
	})
})