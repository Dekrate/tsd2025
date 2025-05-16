package com.yourcompany.game.tennis;

public class TennisPoint {
	private int value;
	private boolean advantage;
	private boolean isTieBreak;

	public TennisPoint(boolean isTieBreak) {
		this.value = 0;
		this.advantage = false;
		this.isTieBreak = isTieBreak;
	}

	public TennisPoint() {
		this(false);
	}

	public int getValue() {
		return value;
	}

	public boolean isAdvantage() {
		return advantage;
	}

	public boolean isTieBreak() {
		return isTieBreak;
	}

	public void setAdvantage() {
		if (!isTieBreak) {
			this.advantage = true;
			this.value = 40;
		}
	}

	public void reset() {
		if (!isTieBreak) {
			this.value = 0;
			this.advantage = false;
		}
	}

	public void increment() {
		if (isTieBreak) {
			value++;
		} else {
			if (value == 0) {
				value = 15;
			} else if (value == 15) {
				value = 30;
			} else if (value == 30) {
				value = 40;
			}
		}
	}

	@Override
	public String toString() {
		if (isTieBreak) {
			return String.valueOf(value);
		} else {
			if (advantage) {
				return "AD";
			}
			return switch (value) {
				case 0 -> "0";
				case 15 -> "15";
				case 30 -> "30";
				case 40 -> "40";
				default -> String.valueOf(value);
			};
		}
	}
}
