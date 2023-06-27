public class SMain {
	private SLex slex;
	private SParser sparser;

	public SMain() {

	}

	public void initialize() {
		slex = new SLex();
		slex.initialize("source/exe1.txt");
		sparser = new SParser();
		sparser.associate(slex); 
	}

	public void finaliize() {
		slex.finalize();
	}

	private void run() {
		sparser.parse(this.slex);
	}

	public static void main(String[] args) {
		SMain main = new SMain();
		main.initialize();
		main.run();
		main.finaliize();
	}
}