package restaurantService;

import java.util.List;

import businessobject.Address;
import businessobject.Rating;
import businessobject.Restaurant;

public interface IRating {

	public List<Rating> getRatingsFromRestaurant();
	public List<Rating> getAllRatings();
	public List<Restaurant> getRestaurants();
	public void insertAddress(String street, int postcode, String city, String country);
	public void insertRating(int stars, String comment, Restaurant restaurant, Address address);
	
}
