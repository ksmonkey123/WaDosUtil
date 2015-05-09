package ch.judos.generic.data;

/**
 * @since 06.07.2013
 * @author Julian Schelker
 */
public class DuplicateFilter {

	private int		cleanUpCounter;
	private Group	gMaster;
	protected int	groupStart;

	public DuplicateFilter() {
		this.gMaster = new Group(3);
		this.groupStart = 0;
		this.cleanUpCounter = 0;
	}

	/**
	 * @param index
	 *            checks if this index has been hit before
	 * @return true if index was not hit, false otherwise
	 */
	public boolean check(int index) {
		return this.gMaster.check(index);
	}

	/**
	 * it is assumed that the indices circulate from Integer.MIN_VALUE to
	 * Integer.MAX_VALUE. to be able to use indices again, this function needs
	 * to be called
	 */
	public void cleanup() {
		int used = 0;
		int i = groupStart;
		while (gMaster.f[i] || gMaster.g[i] != null) {
			used++;
			i++;
		}
		int cleanGroups = used - 10;
		for (int j = 0; j < cleanGroups; j++) {
			int cleanIndex = groupStart + j;
			if (gMaster.f[cleanIndex])
				gMaster.finished--;
			gMaster.f[cleanIndex] = false;
			gMaster.g[cleanIndex] = null;
		}
	}

	/**
	 * @param index
	 *            this index is hit, check(index) will return false after hit()
	 * @return true if this index has not been hit before, false otherwise
	 */
	public boolean hit(int index) {
		this.cleanUpCounter++;
		if (this.cleanUpCounter % 1000000 == 0)
			cleanup();
		return this.gMaster.hit(index);
	}

	protected static class Group {

		private int			div;
		private boolean[]	f;
		private int			finished;
		private Group[]		g;
		private int			lvl;

		public Group(int lvl) {
			this.lvl = lvl;
			if (lvl > 0) {
				this.g = new Group[256];
				this.div = (int) Math.pow(2, 8 * lvl);
			}
			this.f = new boolean[256];
			this.finished = 0;
		}

		public boolean check(int index) {
			if (lvl == 0)
				return !this.f[index];

			int gIndex = index / div;
			if (lvl == 3) // since integers start at -2^31
				gIndex += 128;
			if (f[gIndex]) // if whole group is already marked as hit/finished
				return false;
			if (g[gIndex] == null) // if group doesn't exist yet
				return true;

			int lowerIndex = index % div;
			return g[gIndex].check(lowerIndex);
		}

		private boolean finished() {
			return this.finished == 256;
		}

		public boolean hit(int index) {
			if (lvl == 0) {
				boolean unhitBefore = !this.f[index];
				if (unhitBefore) {
					this.finished++;
					this.f[index] = true;
				}
				return unhitBefore;
			}
			int gIndex = index / div;
			if (lvl == 3) // since integers start at -2^31
				gIndex += 128;
			if (f[gIndex]) // if whole group is already marked as hit/finished
				return false;

			int lowerIndex = index % div;
			if (g[gIndex] == null)
				g[gIndex] = new Group(lvl - 1);
			boolean unhitBefore = g[gIndex].hit(lowerIndex);
			if (g[gIndex].finished()) {
				g[gIndex] = null;
				f[gIndex] = true;
				this.finished++;
			}
			return unhitBefore;
		}

	}

}
