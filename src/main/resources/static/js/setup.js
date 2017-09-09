$(document).ready(function(){
		$("#page1 .welcome").delay(500).fadeIn();
		$("#page1 img").delay("slow").fadeIn();
		$("#page1 .subtitle").delay("slow").fadeIn();
		setTimeout(function(){ start(); }, 10000);
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
		$("#page1").hide();
		var elem = document.documentElement;
		//launchIntoFullscreen(elem); 
		$("#page2").show();
		responsiveVoice.speak("welcome to chat bot conversation page");
	}	