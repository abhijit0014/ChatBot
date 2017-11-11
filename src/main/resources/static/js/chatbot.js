$(document).ready(function(){
	var counter = 0;
	var speaker = 'on';
	$( "#chatscreen" ).append(GetBotMsg("I am your virtual assistant. How can I help you."));
	
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
	
	$( "#userMsg" ).focus(function() {
		document.getElementById( 'userMsg' ).value = "";
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
					$( "#chatscreen" ).append(GetUserMsg(final_transcript));
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
			$("#youtube").empty();
			$("#youtube").css({"padding-top":"25%"});
			$("#youtube").append('<center><img src="img/loading.gif" width="150px"/></center>');
		}
		if(flag==0) {
			counter = 0;
		}
	}
/*	
	$("#userForm").submit(function(){
		$("#botMsg").empty();
		$("#botMsg").css({"padding-top":"10%"});
		$("#botMsg").append('<img src="img/loading.gif" width="150px"/>');
		var msg = document.getElementById( 'userMsg' ).value;
		$( "#chatscreen" ).append(GetUserMsg(msg));
		doAjaxCall("userResponse="+msg,"html");
	});	
*/
	document.getElementById('userMsg').onkeypress = function(e){
	    if (!e) e = window.event;
	    var keyCode = e.keyCode || e.which;
	    if (keyCode == '13'){
			$("#youtube").empty();
			$("#youtube").css({"padding-top":"10%","text-align":"center"});
			$("#youtube").append('<img src="img/loading.gif" width="150px"/>');
			var msg = document.getElementById( 'userMsg' ).value;
			document.getElementById( 'userMsg' ).value = "";
			$( "#chatscreen" ).append(GetUserMsg(msg));
			doAjaxCall("userResponse="+msg,"html");
	    }
	  }
	
// do ajax call ------------------------------------------------------
	function doAjaxCall(urlParameter,dataType)
	{
		var responseData = null;
		$.ajax({
			method: "POST",
			async: false,
			url: '/chatbot/api/?id=2',
			data: urlParameter,
			dataType: dataType,
			success:function(data){
				//alert(data);
				responseData = data; // return student data
				
			}
		});
		GenerateMessage(responseData);
	}
// generatemessage-----------------------------------------------------------------
	function GenerateMessage(responseData)
	{
		$("#botMsg").empty();
		words = responseData.split("&&");
		if(words[1]=="youtube")
		{
			$("#botMsg").css({"font-size":"25px","line-height":"30px","padding-top":"2%","text-align": "center"});
			document.getElementById("youtube").innerHTML = GetYoutubeCode(words[0]);
		}
		else
		{
			if(window.innerWidth<400)
			{
				$("#botMsg").css({"font-size":"16px","line-height":"18px","padding-top":"10%","text-align": "justify"});
			}
			setTimeout(function() {}, 5000);
			$("#youtube").empty();
			if(speaker=="on")
				responsiveVoice.speak(responseData);
			setTimeout(function() {}, 10000);
			$( "#chatscreen" ).append(GetBotMsg(responseData));
		}		
	}
// getYoutubeCode----------------------------------------------------------------
	function GetYoutubeCode(str)
	{
		return '<iframe src="https://www.youtube.com/embed/'+str+'?autoplay=1&rel=0" frameborder="0" allowfullscreen></iframe>';
	}
// 
	function GetUserMsg(str)
	{
		return '<li class="other">'+
				'<a class="user" href="#"><img alt="" src="img/user.png" /></a>'+
				'<div class="message">'+
				'<p>'+str+'</p>'+
				'</div></li>';
	}
	function GetBotMsg(str)
	{	  
		return '<li class="you">'+
		'<a class="user" href="#"><img alt="" src="img/bot.jpg" /></a>'+
		'<div class="message">'+
		'<p>'+str+'</p>'+
		'</div></li>';
	}
	
	
	
	
});
 