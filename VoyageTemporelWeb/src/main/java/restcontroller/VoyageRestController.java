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
import model.Voyage;
import service.VoyageService;

@RestController
@RequestMapping("/api/voyage")
public class VoyageRestController {
	@Autowired
	private VoyageService voyageSrv;

	@JsonView(JsonViews.Common.class)
	@GetMapping("/{id}")
	public Voyage findById(@PathVariable Integer id) {
		return voyageSrv.findById(id);
	}

	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Voyage> findAll() {
		return voyageSrv.findAll();
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("")
	@JsonView(JsonViews.VoyageWithReservation.class)
	public Voyage create(@Valid @RequestBody Voyage voyage, BindingResult br) {
		if (br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "données incorrectes");
		}
		return voyageSrv.create(voyage);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Integer id) {
		try {
			voyageSrv.deleteById(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id inconnu");
		}
	}

	@PutMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public Voyage update(@Valid @RequestBody Voyage voyage, BindingResult br, @PathVariable Integer id) {
		if (br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "données incorrectes");
		}
		voyage.setId(id);
		return voyageSrv.update(voyage);
	}

}
