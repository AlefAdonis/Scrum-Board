package com.ufcg.psoft.scrumboard.observer;

public class ProductOwnerObserver extends NotificationAdapter {

	public void update(NotificationEvent notificationEvent) {
		System.out.println(notificationEvent);
	}
}
