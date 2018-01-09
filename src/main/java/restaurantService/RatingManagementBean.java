package restaurantService;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import businessobject.Address;
import businessobject.Rating;
import businessobject.Restaurant;

public class RatingManagementBean implements IRating {

	@PersistenceContext(name = "RestaurantPU")
	private EntityManager em;
	
	@Override
	public List<Rating> getRatingsFromRestaurant() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rating> getAllRatings() {
		try {
			return em.createQuery("FROM Rating").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Restaurant> getRestaurants() {
		try {
			return em.createQuery("FROM Restaurant").getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void insertAddress(String street, int postcode, String city, String country) {
		Address address = new Address(street, postcode, city, country);
		em.persist(address);
	}

	@Override
	public void insertRating(int stars, String comment, Restaurant restaurant, Address address) {
		Rating rating = new Rating(stars, comment, address, restaurant);
		em.persist(rating);
	}
}
