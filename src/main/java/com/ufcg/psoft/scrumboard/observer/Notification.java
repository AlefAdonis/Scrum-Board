package com.ufcg.psoft.scrumboard.observer;

import com.ufcg.psoft.scrumboard.exception.UserAlreadySubscribedException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


@Component
public class Notification {

	private Collection<NotificationListener> listeners = new HashSet<NotificationListener>();
	private final List<String> subscribers = new ArrayList<>();


	public synchronized void addNotificacaoListener(NotificationListener notificationListener) {
		this.listeners.add(notificationListener);
	}
	
	public synchronized void removeNotificacaoListener(NotificationListener notificationListener) {
		this.listeners.remove(notificationListener);
	}
	
	public void mudancaDescricao(String username) {
		if(!(this.isInSubscribers(username))){
			return;
		}
		NotificationEvent newEvent = new NotificationEvent(username, "Descrição da User Story alterada!");
		this.disparaMudancaDescricao(newEvent);
	}

	private void disparaMudancaDescricao(NotificationEvent event) {
		Collection<NotificationListener> listenersCopy;
		synchronized (this) {
			listenersCopy = (Collection) ((HashSet<NotificationListener>) listeners).clone();
		}
		for (NotificationListener listener : listenersCopy) {
			listener.update(event);
		}
	}
	
	public void mudancaEstagio(String username) {
		if(!(this.isInSubscribers(username))){
			return;
		}
		NotificationEvent newEvent = new NotificationEvent(username, "Estagio da User Story alterada!");
		this.disparaMudancaEstagio(newEvent);
	}

	private boolean isInSubscribers(String username) {
		for(int i =0; i < this.subscribers.size(); i++){
			if(this.subscribers.get(i).equals(username)){
				return true;
			}
		}

		return false;
	}

	private void disparaMudancaEstagio(NotificationEvent event) {
		Collection<NotificationListener> listenersCopy;
		synchronized (this) {
			listenersCopy = (Collection) ((HashSet<NotificationListener>) listeners).clone();
		}
		for (NotificationListener listener : listenersCopy) {
			listener.update(event);
		}
	}
	
	public void taskRealizada() {
		NotificationEvent newEvent = new NotificationEvent(this);
		this.disparaTaskRealizada(newEvent);
	}

	private void disparaTaskRealizada(NotificationEvent event) {
		Collection<NotificationListener> listenersCopy;
		synchronized (this) {
			listenersCopy = (Collection) ((HashSet<NotificationListener>) listeners).clone();
		}
		for (NotificationListener listener : listenersCopy) {
			listener.update(event);
		}
	}
	
	public void usFinalizada() {
		NotificationEvent newEvent = new NotificationEvent(this);
		this.disparaUsFinalizada(newEvent);
	}

	private void disparaUsFinalizada(NotificationEvent event) {
		Collection<NotificationListener> listenersCopy;
		synchronized (this) {
			listenersCopy = (Collection) ((HashSet<NotificationListener>) listeners).clone();
		}
		for (NotificationListener listener : listenersCopy) {
			listener.update(event);
		}
	}

	public void subscribe(String username) throws UserAlreadySubscribedException {
		if(isInSubscribers(username)){
			throw new UserAlreadySubscribedException("Usuário já está cadastrado");
		}

		this.subscribers.add(username);
	}
}
