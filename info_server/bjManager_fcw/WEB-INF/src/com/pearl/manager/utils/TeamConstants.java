package com.pearl.manager.utils;

public interface TeamConstants {
	// team request operate
	String TEAM_OPERATE_JOIN = "join";
	String TEAM_OPERATE_APPROVE = "approve";
	String TEAM_OPERATE_REJECT = "reject";
	String TEAM_OPERATE_QUIT = "quit";
	String TEAM_OPERATE_HANDOVER = "handover";
	String TEAM_OPERATE_APPOINT = "appoint";
	String TEAM_OPERATE_KICK = "kick";

	public static enum TEAMJOB {
		// be aware the sequence
		TEAM_CAPTAIN(4), TEAM_OFFICER(3), TEAM_HIGH_MEMBER(2), TEAM_MEMBER(1), TEAM_BLACK_ROOM(0);
		private int value;

		TEAMJOB(int value) {
			this.value = value;
		}

		public boolean isGreateThan(TEAMJOB teamJob) {
			return this.ordinal() < teamJob.ordinal();
		}

		public static TEAMJOB getTypeByValue(int value) {
			for (TEAMJOB type : TEAMJOB.values()) {
				if (type.value == value) {
					return type;
				}
			}
			return null;
		}

		public int getValue() {
			return value;
		}
	}
}
