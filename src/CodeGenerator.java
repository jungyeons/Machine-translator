import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class CodeGenerator {
	//Statement는 백터로 선언
	private Vector<SStatement> statements;
	//심볼테이블도 함께 
	private SSymbolTable sSymbolTable = new SSymbolTable();
//코드 제너레이터에 심볼 테이블 매핑
	public CodeGenerator(SSymbolTable sSymbolTable) {
		this.sSymbolTable = sSymbolTable;
	}

	public void generateCode(Vector<SStatement> statements) {
		this.statements = statements;
		try {
			File file = new File("executable/exe1");
			FileWriter fileWriter = new FileWriter(file, true);
			fileWriter.write("");

			for (SStatement statement : this.statements) {
				//Operator에 Operand 2개짜리 까지 받을 거임.
				String operator = statement.getOperator();
				String operand1 = statement.getOperand1();
				String operand2 = statement.getOperand2();

				switch (operator) {
				//번역할 오퍼레이터 나열하기
				//레지스터에서 레지스터로 데이터를 이동
				case "movrr":
					String movrr = "11" + getRCode(operand1) + getRCode(operand2);
					System.out.println(movrr);
					fileWriter.write(movrr + "\n");
					break;
				//'movrc'는 레지스터에서 상수값으로 데이터를 이동
				case "movrc":
					String movrc = "12" + getRCode(operand1) + operand2;
					System.out.println(movrc);
					fileWriter.write(movrc + "\n");
					break;
				//'movra'는 레지스터에서 특정 메모리 주소로 데이터를 이동
				case "movra":
					String movra = "13" + getRCode(operand1) + getRCode(operand2);
					System.out.println(movra);
					fileWriter.write(movra + "\n");
					break;
				case "add":
					String add = "14" + getRCode(operand1) + getRCode(operand2);
					System.out.println(add);
					fileWriter.write(add + "\n");
					break;
				case "sub":
					String sub = "15" + getRCode(operand1) + getRCode(operand2);
					System.out.println(sub);
					fileWriter.write(sub + "\n");
					break;
				case "mul":
					String mul = "16" + getRCode(operand1) + getRCode(operand2);
					System.out.println(mul);
					fileWriter.write(mul + "\n");
					break;
				case "div":
					String div = "17" + getRCode(operand1) + getRCode(operand2);
					System.out.println(div);
					fileWriter.write(div + "\n");
					break;
				case "halt":
					System.out.println("18");
					fileWriter.write("18");
					break;
				case "jump":
					String jump = "19" + getRCode(operand1);
					System.out.println(jump);
					fileWriter.write(jump + "\n");
					break;
				case "gtj":
					String gtj = "20" + getRCode(operand1);
					System.out.println(gtj);
					fileWriter.write(gtj + "\n");
					break;
				case "ltj":
					System.out.println("33");
					fileWriter.write("11\n");
					break;
				case "ldi":
					String ldi = "42" + getRCode(operand1) + operand2;
					System.out.println(ldi);
					fileWriter.write(ldi + "\n");
					break;
				case "lda":
					System.out.println("43");
					fileWriter.write("43");
					break;
				case "sto":
					System.out.println("55");
					fileWriter.write("55\n");
					break;
				case "push":
					String push = "44" + getRCode(operand1) + operand2;
					System.out.println("44");
					fileWriter.write(push + "\n");
					break;
				case "out":
					String out = "45" + getRCode(operand1) + operand2;
					System.out.println("45");
					fileWriter.write(out + "\n");
					break;
				case "pop":
					String pop = "46" + getRCode(operand1) + operand2;
					System.out.println("46");
					fileWriter.write(pop + "\n");
					break;
				default:
					System.out.println("Invalid instruction: " + operator);
					break;
				}
			}
			fileWriter.close();

		} catch (IOException e) {

			System.out.println("오류가 발생했습니다 !! ");
		}
	}


	public String getRCode(String operand) {
		//오퍼렌드가 비어있는 지  확인 
	if (operand == null || operand.isEmpty()) {
		return "";
	}
	// operand가 symbolTable에 존재하는지를 확인하고 해당 주소를 저장하는 데 사용
	int address = -1;
	String ReplaceOperand = operand.replace("@", "");
	for (int i = 0; i < this.sSymbolTable.size(); i++) {
		SSymbolEntity entity = sSymbolTable.get(i);
		if (entity.getVariableName().equals(ReplaceOperand)) {
			address = entity.getAddress();
			break;
		}
	}
	if (address == -1) {
		return "";
	}
	return Integer.toString(address);
}
}
