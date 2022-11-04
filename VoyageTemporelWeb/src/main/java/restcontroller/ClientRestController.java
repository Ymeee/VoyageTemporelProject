package restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import model.Client;
import model.JsonViews;
import service.ClientService;

@RestController
@RequestMapping("/api/client")
public class ClientRestController {

	@Autowired
	private ClientService clientSrv;
	
	@JsonView(JsonViews.Common.class)
	@PostMapping("/inscription")
	public Client inscription(@Valid @RequestBody Client client, BindingResult br) {
		if (br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "données incorrectes");
		}
		return clientSrv.save(client);
	}
	
	@JsonView(JsonViews.Common.class)
	@GetMapping("/{id}")
	public Client findById(@PathVariable Integer id) {
		return clientSrv.findById(id);
	}
	
	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Client> findAll() {
		return clientSrv.findAll();
	}
	
	
}
