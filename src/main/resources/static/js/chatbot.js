var stompClient = null;

function setConnected(connected) {
    $("#disconnect").prop("disabled", !connected);
    $("#send").prop("disabled", !connected);
    if (connected) {
        $("#communicate").show();
    }
    else {
        $("#communicate").hide();
    }
    $("#msg").html("");
}

function connect() {
	//start_yn = true;
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
       setConnected(true);
       console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/public', function (message) {
          
		 console.log('/topic/public' + frame);
			var msgArray = message.body.split('",');
			
			//리스트 값으로 받아 ", 로 split한 후, 특정 문자를 제거해준다.
			for(var i=0;i<msgArray.length;i++){
				var result = msgArray[i].replace(/\"/gim,'');
				result = result.replace(/\\n/gim,'<br>');
				result = result.replace(/\[/gim,'');
				result = result.replace(/\]/gim,'');
				
				if(i==0)
					showMessage(result);
				else
					showButton(result);
			
			}
			 //서버에 메시지 전달 후 리턴받는 메시지
        });
    });
	
}
function start() {

    let message = "시작"
 	$("#communicate").append("<br>");
    stompClient.send("/app/sendMessage", {}, JSON.stringify(message)); //서버에 보낼 메시지
}


function disconnect() {
	//start_yn = false;

    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {

    let message = $("#msg").val()
    show_sendMessage(message);

    stompClient.send("/app/sendMessage", {}, JSON.stringify(message)); //서버에 보낼 메시지
}

function sendMessage2(message) {
	
	$("#msg").value = "";
    show_sendMessage(message);

    stompClient.send("/app/sendMessage", {}, JSON.stringify(message)); //서버에 보낼 메시지
}

function show_sendMessage(message) {
    $("#communicate").append("<br><br><div id = 'sendMessage'><span class = 'balloon'>" + message + "</span></div><br>");
}

function showMessage(message) {
/*<span style='font-weight:bold;'>*/
    $("#communicate").append("<span style='font-weight:bold;'>Try IT 챗봇</span><br><div id = 'receiveMessage'><span class = 'balloon'>" + message + "</span>");
}

function showButton(message) {
    $("#communicate").append("<br><button class = 'butn-style2' style = 'margin:2px'onclick = 'sendMessage2(\""+message+"\");'>"+message+"</button></div>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });

 //연결 후 환영 메시지 출력
	$(document).ready(function(){
		connect(); setTimeout(()=>{
        start();
    },1000);
		
		
	 });

   $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() {sendMessage(); });

});