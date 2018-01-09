package managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import businessobject.Address;
import businessobject.Restaurant;
import restaurantService.IRating;

public class RatingBean {
	
	//restuarant information
	private List<String> restaurantNames;
	private String sourceRestaurantName;
	private List<Restaurant> restaurantList;

	//get infos from person
	private String streetPerson;
	private int postcodePerson;
	private String cityPerson;
	private String countryPerson;
	
	private int amountStars;
	private String ratingComment;
	private String rateInformation = "";
	
	private IRating rating;
	private Restaurant restaurant;
	private Address address;
	
	private String pageChange;
	
	@PostConstruct
	public void initialize() throws NamingException {
		// use JNDI to inject reference to bank EJB
		InitialContext ctx = new InitialContext();
		rating = (IRating) ctx.lookup("java:global/TP12-WEB-EJB-PC-EPC-E-0.0.1-SNAPSHOT/RatingManagementBean!restaurantService.IRating");
		
		// get restaurants
		List<Restaurant> restaurantList = rating.getRestaurants();
		this.restaurantNames = new ArrayList<String>();
		for (Restaurant r : restaurantList) {
			this.restaurantNames.add(r.getName_restaurant());
		}
	}
	
	public String insertRating(){	
		//insert new address
		rating.insertAddress(this.streetPerson, this.postcodePerson, this.cityPerson, this.countryPerson);
		
		// get restaurants
		this.restaurantList.clear();
		this.restaurantList = rating.getRestaurants();
		this.restaurantNames.clear();
		this.restaurantNames = new ArrayList<String>();
		for (Restaurant r : restaurantList) {
			this.restaurantNames.add(r.getName_restaurant());
		}
		int selectedRestIndex = -1;
		for (int i = 0; i < restaurantList.size(); i++) {
			if (restaurantList.get(i).getName_restaurant().equals(this.sourceRestaurantName)) {
				selectedRestIndex = i;
				break;
			}
		}

		if (this.amountStars != 0 && !this.ratingComment.trim().equals("") && selectedRestIndex != -1) {
			rating.insertRating(this.amountStars, this.ratingComment, this.restaurant, this.address);
			// reset content
			resetRateRestaurant();
		} else {
			this.rateInformation = "sorry - not every form is filled out!";
		}
		this.pageChange = "welcomePage";

		return this.pageChange;
	}
	
	public void resetRateRestaurant(){
		this.streetPerson = "";
		this.postcodePerson = 0;
		this.cityPerson = "";
		this.countryPerson = "";
		
		this.amountStars = 0;
		this.ratingComment = "";
		
		this.rateInformation = "";
	}
	
	public void updateRestaurants(ValueChangeEvent event) {
		this.sourceRestaurantName = (String)event.getNewValue();
    	
	    List<Restaurant> rest = rating.getRestaurants();
	    this.restaurantNames.clear();
	    this.restaurantNames = new ArrayList<String>();
		for (Restaurant r : rest) {
			this.restaurantNames.add(r.getName_restaurant());
		}
    }
	
	
	//getters and setters
	public String getStreetPerson() {
		return streetPerson;
	}
	
	public void setStreetPerson(String streetPerson) {
		this.streetPerson = streetPerson;
	}
	
	public int getPostcodePerson() {
		return postcodePerson;
	}
	
	public void setPostcodePerson(int postcodePerson) {
		this.postcodePerson = postcodePerson;
	}
	
	public String getCityPerson() {
		return cityPerson;
	}
	
	public void setCityPerson(String cityPerson) {
		this.cityPerson = cityPerson;
	}
	
	public String getCountryPerson() {
		return countryPerson;
	}
	
	public void setCountryPerson(String countryPerson) {
		this.countryPerson = countryPerson;
	}
	
	public int getAmountStars() {
		return amountStars;
	}
	
	public void setAmountStars(int amountStars) {
		this.amountStars = amountStars;
	}
	
	public String getRatingComment() {
		return ratingComment;
	}
	
	public void setRatingComment(String ratingComment) {
		this.ratingComment = ratingComment;
	}
	
	public String getSourceRestaurantName() {
		return sourceRestaurantName;
	}

	public void setSourceRestaurantName(final String sourceRestaurantName) {
		this.sourceRestaurantName = sourceRestaurantName;
	}
		
	public List<String> getRestaurantNames() {
		return restaurantNames;
	}

	public void setRestaurantNames(List<String> restaurantNames) {
		this.restaurantNames = restaurantNames;
	}
}
