package com.ufcg.psoft.scrumboard.observer;

public class NotificationEvent {

	String usuario;
	String motivo;
	private Notification notification;
	
	public NotificationEvent(String usuario, String motivo) {
		this.usuario = usuario;
		this.motivo = motivo;
	}
	
	public NotificationEvent(Notification notification) {
		this.notification = notification;
	}

	public String toString() {
		return "Usuario: " + usuario  + "\n" +
				"Motivo: " + motivo;
	}
	
}
