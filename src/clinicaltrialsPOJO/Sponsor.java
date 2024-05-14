package clinicaltrialsPOJO;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Sponsor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8957665511111229688L;
	
	private Integer sponsor_id;
	private String name; 
	private String email;
	private Integer phone;
	private Integer cardNumber;
	private List<Trial> trials; 
	private List<Reports> reports; 
	
	
	public Sponsor(Integer sponsor_id, String name, String email, Integer phone, Integer cardNumber) {
		super();
		this.sponsor_id = sponsor_id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.cardNumber = cardNumber;
	}

	
	

	public Sponsor(String name, String email, Integer phone, Integer cardNumber) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.cardNumber = cardNumber;
	}





	public Integer getSponsor_id() {
		return sponsor_id;
	}


	public void setSponsor_id(Integer sponsor_id) {
		this.sponsor_id = sponsor_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Integer getPhone() {
		return phone;
	}


	public void setPhone(Integer phone) {
		this.phone = phone;
	}


	public Integer getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(Integer cardNumber) {
		this.cardNumber = cardNumber;
	}

	
	public List<Trial> getTrials() {
		return trials;
	}


	public void setTrials(List<Trial> trials) {
		this.trials = trials;
	}


	public List<Reports> getReports() {
		return reports;
	}


	public void setReports(List<Reports> reports) {
		this.reports = reports;
	}
	
	



	@Override
	public int hashCode() {
		return Objects.hash(cardNumber, email, name, phone, reports, sponsor_id, trials);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sponsor other = (Sponsor) obj;
		return Objects.equals(cardNumber, other.cardNumber) && Objects.equals(email, other.email)
				&& Objects.equals(name, other.name) && Objects.equals(phone, other.phone)
				&& Objects.equals(reports, other.reports) && Objects.equals(sponsor_id, other.sponsor_id)
				&& Objects.equals(trials, other.trials);
	}


	@Override
	public String toString() {
		return "Sponsor [sponsor_id=" + sponsor_id + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", cardNumber=" + cardNumber + ", trials=" + trials + ", reports=" + reports + "]";
	}

	
}
