/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package event;

import java.util.logging.Logger;

import com.google.common.eventbus.EventBus;

public class EventManager {

	private static final Logger logger = Logger.getLogger(EventManager.class.getName());

	private static final EventBus clientEventBus = new EventBus(new RethrowingExceptionHandler());
	private static final EventBus serverEventBus = new EventBus(new RethrowingExceptionHandler());

	public static void post(ServerEvent event) {
		serverEventBus.post(event);
	}

	public static void post(ToServerEvent event) {
		logger.info("Event posted:" + event);
		clientEventBus.post(event);
	}

	public static void post(ClientEvent event) {
		logger.info("Event posted:" + event);
		clientEventBus.post(event);
	}

	public static void registerForClient(Object obj) {
		logger.info("register for ClientEvents:" + obj);
		clientEventBus.register(obj);
	}

	public static void registerForServer(Object obj) {
		logger.info("register for ServerEvents:" + obj);
		serverEventBus.register(obj);
	}

	public static void unregister(Object obj) {
		logger.info("unregister for Events:" + obj);
		clientEventBus.unregister(obj);
		serverEventBus.unregister(obj);
	}
}
