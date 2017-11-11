$(document).ready(function(){
		$("#page1 .welcome").delay(500).fadeIn();
		$("#page1 img").delay("slow").fadeIn();
		$("#page1 .subtitle").delay("slow").fadeIn();
		setTimeout(function(){ start(); }, 5000);
	}); 
	
	function launchIntoFullscreen(element) {
	  if(element.requestFullscreen) {
		element.requestFullscreen();
	  } else if(element.mozRequestFullScreen) {
		element.mozRequestFullScreen();
	  } else if(element.webkitRequestFullscreen) {
		element.webkitRequestFullscreen();
	  } else if(element.msRequestFullscreen) {
		element.msRequestFullscreen();
	  }
	}
	
	function start()
	{
		var message;
	    d = new Date();
	    h = d.getHours();
	    
	    if(h>=5 && h<13) message = "Good Morning";
	    if(h>=12 && h<19) message = "Good afternoon";
	    if(h>=18 && h<23) message = "Good evening";
	    if(h>22 && h<5) message = "Good evening";    

		$("#page1").hide();
		var elem = document.documentElement;
		//launchIntoFullscreen(elem); 
		$("#page2").show();
		responsiveVoice.speak(message+", I am your virtual assistant. How can I help you.");
	}	