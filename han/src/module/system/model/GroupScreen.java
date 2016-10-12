package module.system.model;

public class GroupScreen {

	Integer groupScreenId;
	Integer groupId;
	Integer screenId;
	String screenName;
	Boolean access;
	
	public Integer getGroupScreenId() {
		return groupScreenId;
	}
	public void setGroupScreenId(Integer groupScreenId) {
		this.groupScreenId = groupScreenId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getScreenId() {
		return screenId;
	}
	public void setScreenId(Integer screenId) {
		this.screenId = screenId;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public Boolean getAccess() {
		return access;
	}
	public void setAccess(Boolean access) {
		this.access = access;
	}
	
	
}
