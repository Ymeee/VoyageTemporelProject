package restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import model.JsonViews;
import model.Passager;
import service.PassagerService;

@RestController
@RequestMapping("/api/passager")
public class PassagerRestController {
	@Autowired
	private PassagerService passagerSrv;

	@JsonView(JsonViews.Common.class)
	@GetMapping("/{id}")
	public Passager findById(@PathVariable Integer id) {
		return passagerSrv.findById(id);
	}

	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Passager> findAll() {
		return passagerSrv.findAll();
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("")
	@JsonView(JsonViews.Common.class)
	public Passager create(@Valid @RequestBody Passager passager, BindingResult br) {
		if (br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "données incorrectes");
		}
		return passagerSrv.create(passager);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Integer id) {
		try {
			passagerSrv.deleteById(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id inconnu");
		}
	}

	@PutMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public Passager update(@Valid @RequestBody Passager passager, BindingResult br, @PathVariable Integer id) {
		if (br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "données incorrectes");
		}
		passager.setId(id);
		return passagerSrv.update(passager);
	}

}
