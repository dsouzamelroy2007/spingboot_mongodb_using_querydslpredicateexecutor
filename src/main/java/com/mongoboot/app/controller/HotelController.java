package com.mongoboot.app.controller;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongoboot.app.model.Hotel;
import com.mongoboot.app.model.QHotel;
import com.mongoboot.app.repository.HotelRepository;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	
	private HotelRepository hotelRepository;

	public HotelController(HotelRepository hotelRepository) {
		super();
		this.hotelRepository = hotelRepository;
	}
	
	@RequestMapping("/allHotels")
	public List<Hotel> getAllHotels(){
		return this.hotelRepository.findAll();
	}
	
	@PutMapping("/addHotel")
	public void insertHotel(@RequestBody Hotel hotel) {
		 	this.hotelRepository.insert(hotel);
	}
	
	@PostMapping("/updateHotel")
	public Hotel updateHotel(@RequestBody Hotel hotel) {
		 return	this.hotelRepository.save(hotel);
	}
	
	@DeleteMapping("/deleteHotel/{id}")
	public void deleteHotel(@PathVariable("id") String id) {
		 	this.hotelRepository.deleteById(id);
	}
	
	
	@GetMapping("/findById/hotel/{id}")
	public Hotel findHotelById(@PathVariable("id") String id) {
		 return	this.hotelRepository.findById(id).get();
	}
	
	@GetMapping("/findByMaxPricePerNight/hotel/{price}")
	public List<Hotel> findHotelById(@PathVariable("price") Integer price) {
		 return	this.hotelRepository.findByPricePerNightLessThan(price);
	}
	
	@GetMapping("/findHotelsByCity/Address/{city}")
	public List<Hotel> findHotelsByCity(@PathVariable("city") String city) {
		 return	this.hotelRepository.findByCity(city);
	}
	
	@GetMapping("/findHotelsByCountry/Address/{country}")
	public List<Hotel> findHotelsByCountry(@PathVariable("country") String country) {
		QHotel qHotel= new QHotel("hotel");
		BooleanExpression filterByCountry = qHotel.address.country.eq(country);
		List<Hotel> hotels= (List<Hotel>) this.hotelRepository.findAll(filterByCountry);
		return hotels;
	}
	
	@GetMapping("/findHotelsByRecommendation/maxPrice/{price}/minRating/{rating}")
	public List<Hotel> findHotelsByCountry(@PathVariable("price") Integer price,@PathVariable("rating") Integer rating) {
		QHotel qHotel= new QHotel("hotel");
		BooleanExpression filterByMaxPrice = qHotel.pricePerNight.lt(price);
		BooleanExpression filerByMinRating = qHotel.reviews.any().rating.gt(rating);
		List<Hotel> hotels= (List<Hotel>) this.hotelRepository.findAll(filterByMaxPrice.and(filerByMinRating));
		return hotels;
	}
}
