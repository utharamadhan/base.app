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
	iterateNewStreamCount();
	prependNewStreamPanel(obj);
	prependNewStreamSection(obj);
}

function iterateNewStreamCount() {
	var obj = $('.notification-label');
	if ($('#container-unread-count').is(':visible')) {
		obj.text(parseInt(obj.text()) + 1);
	} else {
		obj.text('1');
	}
	$('#container-unread-count').show();
}

function prependNewStreamPanel(obj) {
	var html = "<div class='notifications-wrapper'>";
	html += "<a class='content' href='" + obj.detail_url + obj.fk_maintenance + "'>";
	html += "<div class='notification-item-new'>";
	html += "<h4 class='item-title'><label>" + obj.email_from + "</label> <small class='item-actionAge'>" + obj.action_age + "</small></h4>";
	html += "<p class='item-info'>" +  obj.notification_description + "</p>";
	html += "</div>";
	html += "</a>";
	html += "</div>";
	$('#notification-content').prepend(html);
}

function prependNewStreamSection(obj) {
	if($('.stream-new-notifications').is(':visible')) {
		$('.count-new-notifications').text(parseInt($('.count-new-notifications').text()) + 1);
	} else {
		$('.count-new-notifications').text('1');
		$('.stream-new-notifications').show();
	}
	var html = "<li class='list-group-item new-result-hidden'>";
	html += "<div class='media'>";
	html += "<div class='media-body col-md-12'>";
	html += "<h4 class='media-heading' style='width:100%;'>";
	html += "<small class='pull-right'>" + obj.action_age +"</small>";
	html += "<a class='name'>" + obj.email_from + "</a>";
	html += "</h4>";
	html += "<small>" + obj.action_date + "</small>";
	html += "<div class='notification-content content well'>"+obj.overview_message+"</div>";
	html += "<small class='pull-right'><a href='" + obj.detail_url + obj.fk_maintenance + "'>read more</a></small>";
	html += "</div>";
	html += "</div>";
	html += "</li>";
	console.log(html);
	$('.dailyNotifications-feed-content').prepend(html);
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
	var html = notif.email_from + " just submit new \"contact us\" form " + notif.action_age;
	return html;
}