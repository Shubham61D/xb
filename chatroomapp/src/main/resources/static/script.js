var stompClient=null

function sendMessage(){

let jsonOb={


	name:localStorage.getItem("name"),
	contents:$("#message-value").val()
}


stompClient.send("/app/message",{},JSON.stringify(jsonOb));

}



function connect(){

	let socket=new SockJS("/server1")

	stompClient=Stomp.over(socket)

	stompClient.connect({},function(frame){

		console.log("Connected:" +frame)

		$("#name-from").addClass('d-none')
		  $("#chat-room").removeClass('d-none')


	stompClient.subscribe("/topic/return-to",function(response){
		showMessage(JSON.parse(response.body))
	})


	})
}

function showMessage(message)
{

	$("#message-container-table").prepend(`<tr><td><b>${message.name}:</b>${message.contents}</td></tr>`)
}






$(document).ready((e)=>{


$("#login").click(()=>{
		connect();

		let name=$("#name-value").val()
		localStorage.setItem("name",name);

		connect();


	})


	$("#send-btn").click(()=>{
	sendMessage()

	})



})