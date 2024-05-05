package clinicaltrialsPOJO;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class InvestigationalProduct implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6715210654882847041L;
	
	private Integer invProduct_id;
	private Integer amount; 
	private String description; 
	private String type;
	private List<Trial> trial;
	
	
	
	
	public InvestigationalProduct(Integer amount, String description, String type) {
		super();
		this.amount = amount;
		this.description = description;
		this.type = type;
	}

	
	
	public Integer getInvProduct_id() {
		return invProduct_id;
	}

	public void setInvProduct_id(Integer invProduct_id) {
		this.invProduct_id = invProduct_id;
	}

	public Integer getAmount() {
		return amount;
	}
	
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}


	public List<Trial> getTrial() {
		return trial;
	}



	public void setTrial(List<Trial> trial) {
		this.trial = trial;
	}



	@Override
	public int hashCode() {
		return Objects.hash(amount, description, invProduct_id, trial, type);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvestigationalProduct other = (InvestigationalProduct) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(description, other.description)
				&& Objects.equals(invProduct_id, other.invProduct_id) && Objects.equals(trial, other.trial)
				&& Objects.equals(type, other.type);
	}



	@Override
	public String toString() {
		return "InvestigationalProduct [invProduct_id=" + invProduct_id + ", amount=" + amount + ", description="
				+ description + ", type=" + type + ", trial=" + trial + "]";
	}

}
