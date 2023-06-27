import java.util.Vector;

public class SCodeSegment implements INode {
	private Vector<SStatement> statements;
	SSymbolTable sSymbolTable;

	public SCodeSegment(SSymbolTable sSymbolTable) { 
		this.statements = new Vector<SStatement>();
		this.sSymbolTable = sSymbolTable;
	}

	@Override
	public String parse(SLex lex) {
		String[] tokens = lex.getTokens();
		String operator = tokens[0];
		boolean isFirstRow = true;
		while (operator.compareTo(".end") != 0) {

			if ((operator.startsWith("//")) || (operator.length() == 0)) {

			} else if (operator.contains(":")) {
				SSymbolEntity entity = new SSymbolEntity();
				entity.setVariableName(operator.replace(":", ""));
				entity.setAddress(this.statements.size() + 55);
				sSymbolTable.add(entity);

			} else {
				SStatement statement = null;
				switch (tokens.length) {
				case 1:
					statement = new SStatement(tokens[0]);
					break;
				case 2:
					statement = new SStatement(tokens[0], tokens[1]);
					break;
				case 3:
					statement = new SStatement(tokens[0], tokens[1], tokens[2]);
					break;
				default:
					break;
				}

				this.statements.add(statement);
				if (isFirstRow) {
					String header = "+------+------------+------------+------------+";
					String columnHeader = "|      | Operator   | Operand 1  | Operand 2  |";
					System.out.println(header);
					System.out.println(columnHeader);
					System.out.println(header);
					isFirstRow = false;
				}

				String row = String.format("|      | %-10s | %-10s | %-10s |", tokens.length >= 1 ? tokens[0] : "",
						tokens.length >= 2 ? tokens[1] : "", tokens.length >= 3 ? tokens[2] : "");
				System.out.println(row);
			}

			tokens = lex.getTokens();
			if (tokens.length >= 1) {
				operator = tokens[0];
			} else {
				operator = "";
			}
		}

		if (!isFirstRow) {
			String footer = "+------+------------+------------+------------+";
			System.out.println(footer);
		}

		System.out.println("+--------------------------------------------+");
		System.out.println("                  기계어 변환 결과");
		System.out.println("+--------------------------------------------+");
		CodeGenerator cg = new CodeGenerator(this.sSymbolTable);
		cg.generateCode(statements);

		return operator;
	}
}