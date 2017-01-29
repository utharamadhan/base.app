$(function(){
	loadNotifications();
	connect();
});

function loadNotifications() {
	var sURL = '/Web/do/notification/showLastFiveNotifications?skipDecorator';
	$.get(sURL, function(data){
		$('#notification-five-list').replaceWith(data);
	});
}

function appendNotificationHeader(obj) {
	$('#notification-header-list').prepend(constructNotif(obj));
}

function constructNotif(obj) {
	var html = "<li>" + obj.email_from + " " + obj.notification_description + " " + obj.action_age + "</li>";
	return html;
}
           
var stompClient = null; 
function connect() {
	var socket = new SockJS('/Web/do/webSocket');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		stompClient.subscribe('/topic/listenNotification', function(result){
			showResult(JSON.parse(result.body));
			appendNotificationHeader(JSON.parse(result.body));
		});
	});
}
 
function disconnect() {
	stompClient.disconnect();
	setConnected(false);
}
 
function sendMessage() {
	var message = document.getElementById('message').value;
	stompClient.send("/app/webSocket", {}, message);
}
 
function showResult(notification) {
	$.notify({
		message : constructHTMLNotif(notification)
	}, {
		type : "info"
	});
}

function constructHTMLNotif(notif) {
	var html = notif.name_from + " " + notif.email_from + " just submit new contact us form " + notif.action_age;
	return html;
}