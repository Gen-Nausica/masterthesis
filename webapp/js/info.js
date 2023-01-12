$(document).ready(function(){
	/*
	 * create nodes
	 */
	var image = findImage();
	if(image !== undefined){
		$(".image_container").append(image);
	}

	var h2 = findHeadline();
	if(h2 !== undefined){
		$(".h2_container").append(h2);
	}

	
	/*
	 * Create nodes for movie-page
	 */
	var plot = findPlot();
	if(plot !== undefined){
		$(".plot").append(plot);
	}
	
	var director = findDirector();
	if(director !== undefined){
		$(".firstNode").append(director);
	}
	
	var cast = findCast();
	if(cast !== undefined){
		$(".secondNode").append(cast);
	}
	
	var awards = findAwards();
	if(awards !== undefined){
		$(".thirdNode").append(awards);
	}
	
	var rating = findRating();
	if(rating !== undefined){
		$(".forthNode").append(rating);
	}
	
	/*
	 * create nodes for actor-page
	 */
	var primaryProf = findPrimaryProf();
	if(primaryProf !== undefined){
		$(".plot").append(primaryProf);
	}
	
	var knownForTitles = findKnownForTitles();
	console.log(knownForTitles);
	if(knownForTitles !== undefined){
		$(".firstNode").append(knownForTitles);
	}
	
	var nomCount = findNomCount();
	if(nomCount !== undefined){
		$(".secondNode").append(nomCount);
	}
/*	
	var awards = findAwards();
	if(awards !== undefined){
		$(".secondNode").append(awards);
	}
*/	
	//ToDo Oscar Nominations of movie or similar
	
	/*
	 * React to clicks on generated nodes
	 * 1st level, 2nd level and 3rd level exists, chaanges to 2nd and 3rd level must be made
	 */
	
	//Node erster Ebene
	$(".expand").on("click", function(){
		var t = $(this).next(".secondLevel");
		var c = $(this).parent();
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
		var c = $(this).parent();
		//check if node is expanded or not
		if($(t).is(":hidden")){
			//not expanded, show
			var w = $(this).width();
			var p = $(this).parent().parent().parent().width();
			w = w+2*(p*0.1);
			w = w+(0.15*p);
			$(t).show();

			$(t).css("display", "inline-block");
			$(t).css("text-align", "center");
			$(this).parent().css("margin-top","10%");
			//$(this).parent().css("overflow","auto");
			$(this).parent().css("width",w);
			$(t).find(".subCat").css("float","none");

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
			console.log("TMP: ");
			console.log(tmp);
			$(tmp).each(function(index, element){
				//change background-color of circles
				console.log("in background-color changer");
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

function findImage(){
	var url = $("#img").html();
	if(url !== undefined){
		var markup = "<img class='poster_img' src='"+url+"'/>";
	}
	return markup;
}

function findHeadline() {
	var headline = $("#title").html();
	if(headline !== undefined){
		var markup = "<h2>"+headline+"</h2>";
	}
	return markup;
}

function findPlot() {
	var plot = $("#plot").html();
	if(plot !== undefined){
		var markup="<p>"+plot+"</p>";
	}
	return markup;
}

function findDirector() {
	var director = $("#director").html();
	if(director !== undefined){
		var url = "https://de.wikipedia.org/wiki/"+director.replace(" ","_");
		var markup = "<div class='smallNode expand'>";
		markup += "<p>Regisseur (1)</p>";
		markup += "</div>";
		markup += "<div class='secondLevel'>";
		markup += "<a class='odd subcat--small link' href='"+url+"'>"+director+"</a>";
		markup += "</div>";
	}
	return markup;
}

function findCast() {
	var cast = $("#actors").html();
	console.log(cast);
	if(cast !== undefined){
		cast = cast.split(", ");
		var nodeSize = findNodeSize(cast.length*10);
		var subNodeSize = findSubNodeSize(nodeSize);
		var markup = "<div class='"+nodeSize+" expand'>";
			markup += "<p>Cast("+cast.length+")</p>";
			markup += "</div>";
			markup += "<div class='secondLevel'>";
			var even;
		for(i=0; i<cast.length; i++){
			if(i & 0){
				even ="odd";
			}
			else {
				even = "even";
			}
			markup += "<a class='"+even+" "+subNodeSize+" link' href='https://de.wikipedia.org/wiki/"+cast[i].replace(" ","_")+"'>"+cast[i]+"</a>";
		}
			markup += "</div>";
	}
		return markup;
}

function findNodeSize(length) {
	var nodeSize = "";
	console.log("Length: "+length);
	if(length > 0 && length <= 10){
		nodeSize="smallNode";
		console.log("smallNode");
	}
	else if (length > 10 && length <= 30) {
		nodeSize = "middleNode";
		console.log("middleNode");
	}
	else if (length > 30 && length <= 50) {
		nodeSize = "biggerNode";
		console.log("biggerNode");
	}
	else {
		nodeSize = "bigNode";
		console.log("bigNode");
	}
	return nodeSize;
}

function findSubNodeSize(nodeSize) {
	var subNodeSize = "";
	console.log("in findSubNodeSize");
	if(nodeSize == "smallNode") {
		subNodeSize = "subcat--small";
	}
	else if (nodeSize == "middleNode") {
		subNodeSize = "subcat--middle";
	}
	else if (nodeSize == "biggerNode") {
		subNodeSize = "subcat--bigger";
	}
	else if (nodeSize == "bigNode") {
		console.log("Gott sei dAnk hier");
		console.log("subNodeSize: "+subNodeSize);
		subNodeSize = "subcat--big";
		console.log("Hier ist: "+subNodeSize);
	}
	console.log("Endergebnis: "+subNodeSize);
	return subNodeSize;
}

function findAwards() {
	var awards = $(".awards");
	console.log("Awards length: "+awards.length);
	if(awards.length !== 0){
		var nodeSize = findNodeSize(awards.length*10);
		console.log("Nodesize: "+nodeSize);
		var subNodeSize = findSubNodeSize(nodeSize);
		console.log("Subnodesize: "+subNodeSize);
		var markup = "<div class='"+nodeSize+" expand'>";
		markup += "<p>Preise("+awards.length+")</p>";
		markup += "</div>";
		markup += "<div class='secondLevel'>";
		var even;
		for(i=0; i<awards.length; i++){
			even = oddOrEven(i);
			var a = awards[i].textContent;
			markup += "<a class='"+even+" "+subNodeSize+" link' href='https://de.wikipedia.org/wiki/"+a.replace(" ", "_")+"'>"+awards[i].textContent+"</a>";
		}
		markup += "</div>";
	}
	return markup;
}

function findRating() {
	var rating = $(".ratingName");
	if(rating.length !== 0){
		var nodeSize = findNodeSize(rating.length*10);
		var subNodeSize = findSubNodeSize(nodeSize);
		var markup = "<div class='"+nodeSize+" expand'>";
		markup += "<p>Ratings("+rating.length+")</p>";
		markup += "</div>";
		markup += "<div class='secondLevel'>";
		var even;
		for(i=0; i<rating.length; i++){
			var r = rating[i];
			console.log(i);
			console.log("RatingName: "+r.textContent);
			console.log("RatingVal: "+r.nextSibling.textContent);
			even = oddOrEven(i);
			markup += "<div class='element'>"
			markup += "<div class='"+even+" "+subNodeSize+" subNodeExpand smallNode'><p>"+r.textContent+"</p></div>";
			markup += "<div class='thirdLevel'>";
			markup += "<p class='subCat subcat--small link'>"+r.nextSibling.textContent+"</p>";
			markup += "</div>";
			markup += "</div>"
		}
		markup += "</div>";
	}
	return markup;
}

function oddOrEven(x) {
	  return ( x & 1 ) ? "odd" : "even";
}

function findPrimaryProf(){
	var primaryProf = $("#primaryProf").html();
	if(primaryProf !== undefined){
		var markup="<p>"+primaryProf+"</p>";
	}
	return markup;
}

function findKnownForTitles(){
	var knownForTitles = $(".knownForTitles");
	console.log(knownForTitles);
	console.log(knownForTitles.length);
	if( knownForTitles.length !== 0){
		var nodeSize = findNodeSize(knownForTitles.length*10);
		var subNodeSize = findSubNodeSize(nodeSize);
		var markup = "<div class='"+nodeSize+" expand'>";
			markup += "<p>Bekannt für("+knownForTitles.length+")</p>";
			markup += "</div>";
			markup += "<div class='secondLevel'>";
			var even;
			var k;
		for(i=0; i<knownForTitles.length; i++){
			if(i & 0){
				even ="odd";
			}
			else {
				even = "even";
			}
			k = knownForTitles[i].textContent;
			console.log(k.textContent);
			markup += "<a class='"+even+" "+subNodeSize+" link' href='https://de.wikipedia.org/wiki/"+k.replace(" ", "_")+"'>"+k+"</a>";
		}
			markup += "</div>";
	}
	return markup;
}

function findNomCount() {
	var nomCount = $("#movieNom").html();
	var noms = $(".nominations");
	var movie = $("#movieName").html();
	console.log(noms);
	console.log(noms.length);
	if(nomCount !== undefined){
		var nodeSize = findNodeSize(noms.length*10);
		var subNodeSize = findSubNodeSize(nodeSize);
		var markup = "<div class='"+nodeSize+" expand'>";
		markup += "<p>Nominierungen des Films \""+movie+"\"("+noms.length+")</p>";
		markup += "</div>";
		markup += "<div class='secondLevel'>";
		var even;
		for(i=0; i<noms.length; i++){
			even = oddOrEven(i);
			markup += "<a class='"+even+" "+subNodeSize+" link' href='https://de.wikipedia.org/wiki/"+noms[i].textContent.replace(" ", "_")+"'>"+noms[i].textContent+"</a>";
		}
		markup += "</div>";
	}
	return markup;
}
