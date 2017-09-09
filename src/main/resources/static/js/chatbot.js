$(document).ready(function(){
	var counter = 0;
	var speaker = 'on';
	
	$("#speaker-switch").click(function(){
		$switch = $("#speaker-switch img").attr("src");
		if($switch=="img/speaker-on.png"){
			$("#speaker-switch img").attr("src","img/speaker-off.png");
			speaker = 'off';
		}
		else{
			$("#speaker-switch img").attr("src","img/speaker-on.png");
			speaker = 'on';
		}
	});
	

//voice recognation ------------------------------------

	if ('webkitSpeechRecognition' in window) 
	{
		var recognition = new webkitSpeechRecognition();
		recognition.continuous = true;
		recognition.interimResults = true;
		
		var final_transcript = '';
		recognition.onresult = function( event ) 
		{
			microphoneCSS(1);
			var final_transcript = '';
			for (var i = event.resultIndex; i < event.results.length; ++i) 
			{
				//console.log(event.results[i][0].transcript);
				if (event.results[i].isFinal) {
					microphoneCSS(0);
					final_transcript += event.results[i][0].transcript;
					document.getElementById( 'userMsg' ).value = final_transcript;
					doAjaxCall("userResponse="+final_transcript,"html");
					//doAjaxCall(final_transcript);
				} 
			}
		};
		recognition.start();
	}
	recognition.onend = function() {
		recognition.start();
	}
	
	function microphoneCSS(flag)
	{
		if(flag>0) counter++;
		if(counter==1){
			$("#botMsg").empty();
			$("#botMsg").append('<img src="img/loading.gif" width="150px"/>');
		}
		if(flag==0) {
			counter = 0;
		}
	}
	
	
// do ajax call ------------------------------------------------------
	function doAjaxCall(urlParameter,dataType)
	{
		var responseData = null;
		$.ajax({
			method: "POST",
			async: false,
			url: 'http://localhost:8181/chatbot/api/',
			data: urlParameter,
			dataType: dataType,
			success:function(data){
				//alert(data);
				responseData = data; // return student data
				
			}
		});
		$("#botMsg").empty();
		document.getElementById("botMsg").innerHTML = responseData;
		if(speaker=="on")
			responsiveVoice.speak(responseData);
	}	
});
 