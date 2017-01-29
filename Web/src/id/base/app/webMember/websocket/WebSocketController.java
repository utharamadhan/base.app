package id.base.app.webMember.websocket;

import id.base.app.rest.RestConstant;
import id.base.app.valueobject.notification.Notification;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_WEB_SOCKET)
public class WebSocketController {
	
	@MessageMapping("/webSocket")
	@SendTo("/topic/listenNotification")
	public NotificationJson listenNotification(Notification notification) throws Exception {
		return null;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/notify")
	@ResponseStatus( HttpStatus.OK )
	public void saveOrUpdate(@RequestBody Notification notification, BindingResult bindingResult) throws Exception {
	    listenNotification(notification);
	}
	
}
