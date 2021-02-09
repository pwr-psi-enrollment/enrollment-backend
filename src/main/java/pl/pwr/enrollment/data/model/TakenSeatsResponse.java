package pl.pwr.enrollment.data.model;

public class TakenSeatsResponse {

	private final Integer takenSeats;

	public TakenSeatsResponse(Integer takenSeats) {
		this.takenSeats = takenSeats;
	}

	public Integer getTakenSeats() {
		return takenSeats;
	}
}
