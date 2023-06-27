public class SParser {
	private SLex slex;

	public void associate(SLex slex) {
		this.setLex(slex);
	}

	public void parse(SLex slex) {
		SProgram program = new SProgram();
		program.parse(slex);
	}

	public SLex getLex() {
		return slex;
	}

	public void setLex(SLex slex) {
		this.slex = slex;
	}
}