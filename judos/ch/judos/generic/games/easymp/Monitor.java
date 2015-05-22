package ch.judos.generic.games.easymp;

import java.util.HashSet;
import ch.judos.generic.data.HashMapR;
import ch.judos.generic.games.easymp.api.CommunicatorI;
import ch.judos.generic.games.easymp.msgs.Message;
import ch.judos.generic.games.easymp.msgs.ObjectUpdateMsg;
import ch.judos.generic.games.easymp.msgs.UpdateMsg;

/**
 * @since 22.05.2015
 * @author Julian Schelker
 */
public abstract class Monitor {

	protected boolean							isServer;
	protected HashMapR<Integer, Object>	monitored;
	protected CommunicatorI					communicator;
	protected int								nextObjectId;
	private HashSet<Object>					updates;
	private static Monitor					instance;

	public static void initializeServer(CommunicatorI c) {
		if (instance != null)
			throw new RuntimeException("Monitor was already initialized");
		instance = new ServerMonitor(true, c);
	}

	public static void initializeClient(CommunicatorI c) {
		if (instance != null)
			throw new RuntimeException("Monitor was already initialized");
		instance = new ClientMonitor(false, c);
	}

	public static Monitor getMonitor() {
		if (instance == null)
			throw new RuntimeException(
				"Monitor needs to be first initialized with isServer parameter");
		return instance;
	}

	protected Monitor(boolean isServer, CommunicatorI c) {
		this.isServer = isServer;
		this.monitored = new HashMapR<>();
		this.updates = new HashSet<>();
		this.nextObjectId = 0;
		this.communicator = c;
	}

	/**
	 * @param o
	 */
	public synchronized void addMonitoredObject(Object o) {
		this.monitored.put(this.nextObjectId, o);
		this.nextObjectId++;
	}

	public void forceUpdate(Object o) {
		this.updates.add(o);
	}

	public void update() {
		Message m;
		while ((m = this.communicator.receive()) != null) {
			receiveUpdate(m);
		}
		sendUpdates();
	}

	private void sendUpdates() {
		for (Object o : this.updates) {
			int id = this.monitored.getFromValue(o);
			UpdateMsg up = new ObjectUpdateMsg(id, o);
			this.communicator.sendToAll(up);
		}
		this.updates.clear();
	}

	private void receiveUpdate(Message m) {
		redistribute(m); // eventually send to other clients
		m.data.install(this.monitored);
	}

	protected abstract void redistribute(Message m);

}
