package com.esprit.hypnotrip.presentation.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;

import com.esprit.hypnotrip.persistence.Event;
import com.esprit.hypnotrip.persistence.User;
import com.esprit.hypnotrip.services.exceptions.SenderIsRecieverException;
import com.esprit.hypnotrip.services.interfaces.EventServicesLocal;
import com.esprit.hypnotrip.services.interfaces.InvitationServicesLocal;
import com.esprit.hypnotrip.services.interfaces.UserServicesLocal;

@ManagedBean
@ViewScoped
public class InvitationBean {

	// model
	private List<Event> eventsIFollow = new ArrayList<Event>();
	private List<User> myFriends = new ArrayList<User>();

	DataModel<Event> dataModelEvents;
	DataModel<User> dataModelFriends;

	private Event selectedEvent = new Event();
	private User selectedFriend = new User();

	private String idUser;

	private boolean displayFormEventsIFollow = true;
	private boolean displayFormMyFriends = false;
	private boolean displayFormInviteFriendToEvent = false;
	private boolean response = false;
	private String chaine;

	// EJB injection
	@EJB
	EventServicesLocal eventServicesLocal;

	@EJB
	UserServicesLocal userServicesLocal;

	@EJB
	InvitationServicesLocal invitationServicesLocal;

	// injection des beans
	@ManagedProperty(value = "#{manageListEventsBean}")
	private ManageListEventsBean manageListEventsBean;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	// methode qui charge ... lors de la creation du bean
	@PostConstruct
	public void init() {

		idUser = loginBean.getUser().getId();
		System.out.println(idUser);

		// System.out.println("*************************************");
		System.out.println(selectedEvent.getPageId());
		// System.out.println(selectedFriend.getId());

		eventsIFollow = eventServicesLocal.getAllEventsFollowedByUser(idUser);
		myFriends = userServicesLocal.getAllFriendsByUserId(idUser);

		// display forms
		// selectEventsIFollowToShow();
		// selectMyFriendsToShow();

	}

	public String selectEventsIFollowToShow() {
		displayFormEventsIFollow = true;
		displayFormMyFriends = false;
		displayFormInviteFriendToEvent = false;
		return null;
	}

	public String selectMyFriendsToShow() {
		displayFormEventsIFollow = false;
		displayFormMyFriends = true;
		displayFormInviteFriendToEvent = false;
		return null;
	}

	public String selectFriendToInvite() {
		displayFormEventsIFollow = false;
		displayFormMyFriends = false;
		displayFormInviteFriendToEvent = true;
		return null;
	}

	public String inviteFriendToFollowEvent(String id) {

		response = invitationServicesLocal.isInvitedToLikeApage(id,selectedEvent.getPageId());
		if (response == false) {

			System.out.println("ma3andouch menha");
			chaine = "Invite To Like";

		} else if (response == true) {
			System.out.println("3andou menha");
			chaine = "Already Invited";
		} else {
			chaine = "blah";
		}

		return chaine;
	}

	// recall services
	public String doInviteFriendToLikeEvent() throws SenderIsRecieverException {
		System.out.println("hedhi el page" + selectedEvent.getPlace());
		System.out.println("hedha el dsadig" + selectedFriend.getId());

		invitationServicesLocal.saveOrUpdateInvitation(selectedEvent.getPageId(), 0, idUser, selectedFriend.getId());

		return "eventsIFollow?faces-redirect=true";
	}

	public List<Event> getEventsIFollow() {
		return eventsIFollow;
	}

	public void setEventsIFollow(List<Event> eventsIFollow) {
		this.eventsIFollow = eventsIFollow;
	}

	public ManageListEventsBean getManageListEventsBean() {
		return manageListEventsBean;
	}

	public void setManageListEventsBean(ManageListEventsBean manageListEventsBean) {
		this.manageListEventsBean = manageListEventsBean;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public boolean isDisplayFormEventsIFollow() {
		return displayFormEventsIFollow;
	}

	public void setDisplayFormEventsIFollow(boolean displayFormEventsIFollow) {
		this.displayFormEventsIFollow = displayFormEventsIFollow;
	}

	public boolean isDisplayFormMyFriends() {
		return displayFormMyFriends;
	}

	public void setDisplayFormMyFriends(boolean displayFormMyFriends) {
		this.displayFormMyFriends = displayFormMyFriends;
	}

	public List<User> getMyFriends() {
		return myFriends;
	}

	public void setMyFriends(List<User> myFriends) {
		this.myFriends = myFriends;
	}

	public EventServicesLocal getEventServicesLocal() {
		return eventServicesLocal;
	}

	public void setEventServicesLocal(EventServicesLocal eventServicesLocal) {
		this.eventServicesLocal = eventServicesLocal;
	}

	public DataModel<Event> getDataModelEvents() {
		dataModelEvents.setWrappedData(eventsIFollow);
		return dataModelEvents;
	}

	public void setDataModelEvents(DataModel<Event> dataModelEvents) {
		this.dataModelEvents = dataModelEvents;
	}

	public DataModel<User> getDataModelFriends() {
		dataModelFriends.setWrappedData(myFriends);
		return dataModelFriends;
	}

	public void setDataModelFriends(DataModel<User> dataModelFriends) {
		this.dataModelFriends = dataModelFriends;
	}

	public Event getSelectedEvent() {
		return selectedEvent;
	}

	public void setSelectedEvent(Event selectedEvent) {
		this.selectedEvent = selectedEvent;
	}

	public User getSelectedFriend() {
		return selectedFriend;
	}

	public void setSelectedFriend(User selectedFriend) {
		this.selectedFriend = selectedFriend;
	}

	public boolean isDisplayFormInviteFriendToEvent() {
		return displayFormInviteFriendToEvent;
	}

	public void setDisplayFormInviteFriendToEvent(boolean displayFormInviteFriendToEvent) {
		this.displayFormInviteFriendToEvent = displayFormInviteFriendToEvent;
	}

}
