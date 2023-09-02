package com.ufcg.psoft.scrumboard.observer;

public class ScrumMasterObserver extends NotificationAdapter {

	public void update(NotificationEvent notificationEvent) {
		System.out.print(notificationEvent);
	}


}
