
public class SProgram implements INode {
	private SSymbolTable sSymbolTable;
	private SHeader sheader; 
	private SCodeSegment sCodeSegment;

	public SProgram() {
		this.sSymbolTable = new SSymbolTable();
		this.sheader = new SHeader(sSymbolTable);
		this.sCodeSegment = new SCodeSegment(sSymbolTable);
	}

	@Override
	public String parse(SLex lex) {
		String token = lex.getToken();

		if (token.compareTo(".header") == 0) {
			token = this.sheader.parse(lex);
		}
		if (token.compareTo(".code") == 0) {
			token = this.sCodeSegment.parse(lex);
		}

		return null;
	}
}