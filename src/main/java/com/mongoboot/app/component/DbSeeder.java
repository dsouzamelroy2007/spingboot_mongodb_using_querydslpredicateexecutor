package com.mongoboot.app.component;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mongoboot.app.model.Hotel;
import com.mongoboot.app.model.Review;
import com.mongoboot.app.repository.HotelRepository;
import com.mongoboot.app.model.Address;;

@Component
public class DbSeeder implements CommandLineRunner{

	private HotelRepository hotelRepository;
	
	
	
	public DbSeeder(HotelRepository hotelRepository) {
		super();
		this.hotelRepository = hotelRepository;
	}



	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Hotel taj =  new Hotel("Taj Mahal", 456, 
				new Address("Mumbai", "India"), 
				Arrays.asList(
						new Review("Melroy", 7, true)
						));
		
		Hotel Novotel =  new Hotel("Novotel", 356, 
				new Address("Paris", "France"), 
				Arrays.asList(
						new Review("Sharon", 8, true)
						));
		
		Hotel IMBISS =  new Hotel("IMBISS", 456, 
				new Address("London", "UK"), 
				Arrays.asList(
						new Review("Ashley", 6, false)
						));
		
		Hotel adlon =  new Hotel("Adlon", 758, 
				new Address("Berlin", "Germany"), 
				Arrays.asList(
						new Review("Joachim", 8, true)
						));
		
		
		this.hotelRepository.deleteAll();
		List<Hotel> hotels=Arrays.asList(taj,Novotel,IMBISS,adlon);
		this.hotelRepository.saveAll(hotels);
	}

}
