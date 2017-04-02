package id.base.app.webMember.controller.publication;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Scope(value="request")
@Controller
@RequestMapping("/publication/eventMaintenance")
public class HousingIndexWebController {

	private final String PATH_LIST = "/publication/eventMaintenanceList";
	private final String PATH_DETAIL = "/publication/eventMaintenanceDetail";
}
