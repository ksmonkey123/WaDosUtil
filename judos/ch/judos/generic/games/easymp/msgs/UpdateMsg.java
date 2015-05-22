package ch.judos.generic.games.easymp.msgs;

import java.io.Serializable;
import ch.judos.generic.data.HashMapR;

/**
 * @since 22.05.2015
 * @author Julian Schelker
 */
public abstract class UpdateMsg implements Serializable {

	private static final long	serialVersionUID	= -3702614265304050303L;

	public abstract void install(HashMapR<Integer, Object> monitored);
}
