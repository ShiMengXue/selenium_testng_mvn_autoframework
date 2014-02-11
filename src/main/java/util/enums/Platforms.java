package util.enums;

public enum Platforms
{
	WINDOWS ("windows"), MAC ("mac"), XP ("xp"), VISTA ("vista");

	private String commonName;

	Platforms(String name) {
		this.commonName = name;
	}

	public String getName() {
		return this.commonName;
	}
}
