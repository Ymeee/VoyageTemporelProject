package restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import model.JsonViews;
import model.Reservation;
import service.ReservationService;

@RestController
@RequestMapping("/api/reservation")
public class ReservationRestController {
	
	@Autowired
	private ReservationService reservationSrv;
	
	@JsonView(JsonViews.Common.class)
	@PostMapping("")
	public Reservation create(@RequestBody Reservation reservation) {
		//controles
		return reservationSrv.save(reservation);
	}
	
	@JsonView(JsonViews.Common.class)
	@PostMapping("/list")
	public List<Reservation> create(@RequestBody List<Reservation> reservations) {
		//controles
		return reservationSrv.saveAll(reservations);
	}
	
	@JsonView(JsonViews.Common.class)
	@GetMapping("")
	public List<Reservation> findAll(){
		return reservationSrv.findAll();
	}
	
	
}
