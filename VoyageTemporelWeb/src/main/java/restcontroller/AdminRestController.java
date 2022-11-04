package restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import model.Admin;
import model.JsonViews;
import service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {
	
	@Autowired
	private AdminService adminSrv;
	
	@JsonView(JsonViews.Common.class)
	@GetMapping("/{id}")
	public Admin findById(@PathVariable Integer id) {
		return adminSrv.findById(id);
	}
	
	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Admin> findAll() {
		return adminSrv.findAll();
	}
	
	
	
}
