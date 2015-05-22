package ch.judos.generic.games.easymp.msgs;

import ch.judos.generic.data.HashMapR;
import ch.judos.generic.games.easymp.ReflectiveUpdater;
import ch.judos.generic.games.easymp.api.UpdatableI;

/**
 * @since 22.05.2015
 * @author Julian Schelker
 */
public class ObjectUpdateMsg extends UpdateMsg {

	private static final long	serialVersionUID	= -8113376392756050290L;

	public int						id;
	public Object					data;

	public ObjectUpdateMsg(int id, Object obj) {
		this.id = id;
		this.data = obj;
	}

	@Override
	public void install(HashMapR<Integer, Object> monitored) {
		Object target = monitored.getFromKey(this.id);
		ReflectiveUpdater.updateObject(target, this.data);
		if (target instanceof UpdatableI) {
			UpdatableI updateable = (UpdatableI) target;
			updateable.wasUpdated();
		}
	}

}
