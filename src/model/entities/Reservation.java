package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainExpection;
import model.exceptions.*;
import model.exceptions.*;	

public class Reservation {
	
	private Integer	roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainExpection {
		
		if (!checkOut.after(checkIn)) {
		throw new DomainExpection("Error in reservation: Check-out date must be after check-in date");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}


	public Integer getRoomNumber() {
		return roomNumber;
	}


	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}


	public Date getCheckIn() {
		return checkIn;
	}


	public Date getCheckOut() {
		return checkOut;
	}
	
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public void updateDates(Date checkIn, Date checkOut) throws DomainExpection {
		
		Date now = new Date();
		if(checkIn.before(checkOut) || checkOut.before(now)) {
			throw new DomainExpection("Error in reservation: Reservation dates for update must be future dates");
		}	
		if (!checkOut.after(checkIn)) {
			throw new DomainExpection("Error in reservation: Check-out date must be after check-in date");
		}
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}	
		
	@Override
	public  String  toString () {
		return  ""
			+ roomNumber
			+ ", check-in: "
			+ sdf.format(checkIn)
			+ ", check-out "
			+ sdf.format(checkOut)
			+ ", "
			+ duration()
			+ " nights" ;
		
	}

}
