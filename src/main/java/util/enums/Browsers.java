package util.enums;

public enum Browsers
{
	CHROME ("chrome"), FIREFOX ("firefox"), IE ("ie"), SAFARI ("safari");

	private String commonName;

	Browsers(String name) {
		this.commonName = name;
	}

	public String getName() {
		return this.commonName;
	}
}
