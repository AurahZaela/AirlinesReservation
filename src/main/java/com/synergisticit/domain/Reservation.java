package com.synergisticit.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Setter
@Getter
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ticketNumber;
	
	@OneToOne
	private Passenger passenger;
	
	@ManyToOne
	private Flight flight;
	
	private int checkedBags;
	
	private boolean checkedIn;

	 public Reservation(Passenger passenger, Flight flight, Boolean checkedIn, int checkedBags) {
	        this.passenger = passenger;
	        this.flight = flight;
	        this.checkedIn = checkedIn;
	        this.checkedBags = checkedBags;
	    }
}
