package clinicaltrialsPOJO;

import java.util.Objects;

public class Trial {
	private Integer trial_id;
	private Integer totalAmountInvested;
	
	
	public Trial(Integer trial_id, Integer totalAmountInvested) {
		super();
		this.trial_id = trial_id;
		this.totalAmountInvested = totalAmountInvested;
	}


	public Integer getTrial_id() {
		return trial_id;
	}


	public void setTrial_id(Integer trial_id) {
		this.trial_id = trial_id;
	}


	public Integer getTotalAmountInvested() {
		return totalAmountInvested;
	}


	public void setTotalAmountInvested(Integer totalAmountInvested) {
		this.totalAmountInvested = totalAmountInvested;
	}


	@Override
	public int hashCode() {
		return Objects.hash(totalAmountInvested, trial_id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trial other = (Trial) obj;
		return Objects.equals(totalAmountInvested, other.totalAmountInvested)
				&& Objects.equals(trial_id, other.trial_id);
	}


	@Override
	public String toString() {
		return "Trial [trial_id=" + trial_id + ", totalAmountInvested=" + totalAmountInvested + "]";
	}
	
	
	

}
