package businessobject;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Rating")
public class Rating {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="lastname")
	private String lastname;
	@Column(name="firstname")
	private String fistname;
	@Column(name="amount_stars")
	private int amount_stars;
	@Column(name="comment")
	private String comment;
	
	//Relations
	@Embedded
	private Address address;

	public Rating(String lastname, String fistname, int amount_stars, String comment) {
		super();
		this.lastname = lastname;
		this.fistname = fistname;
		this.amount_stars = amount_stars;
		this.comment = comment;
	}
	
	public Rating(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFistname() {
		return fistname;
	}

	public void setFistname(String fistname) {
		this.fistname = fistname;
	}

	public int getAmount_stars() {
		return amount_stars;
	}

	public void setAmount_stars(int amount_stars) {
		this.amount_stars = amount_stars;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
}
