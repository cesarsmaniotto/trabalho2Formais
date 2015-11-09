package formais152.Modelo;

public class FabricaDeAutomatos {

	public static Automato automatoConstantes() {
		Automato aut = new Automato();
		String esFinal = "CONST";

		aut.addEstado("S0");

		aut.addEstadoFinal(esFinal);
		aut.addEstadoFinal("CONST2");

		try {
			aut.setEstadoInicial("S0");

			for (int i = 0; i <= 9; i++) {
				String simb = "" + i;

				aut.addTransicao("S0", simb, esFinal);
				aut.addTransicao(esFinal, simb, esFinal);
				aut.addTransicao("CONST2", simb, "CONST2");
			}
			String simb = ".";

			aut.addTransicao("S0", simb, "CONST2");
			aut.addTransicao(esFinal, simb, "CONST2");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return aut;
	}

	public static Automato automatoReservadas() {
		Expressao exp = InputOutput.criarExpressao("palavrasReservadas.txt");
		Automato aut = null;
		try {
			aut = exp.obterAutomato();
			aut = aut.removerEpsilonTransicoes();

			aut = aut.obterAutomatoMinimo();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aut;
	}
	public static Automato automatoString(){
		Automato aut = new Automato();
		String esFinal = "STRING";

		aut.addEstado("S0");
		aut.addEstado("S1");
		aut.addEstado(esFinal);

		aut.addEstadoFinal(esFinal);

		try {
			aut.setEstadoInicial("S0");
			
			for (char i = 32; i <= 126; i++) {
				String simb = "" + i;

				aut.addTransicao("S1", simb, "S1");
			}
			
			String simb = "\"";
			aut.addTransicao("S0", simb, "S1");
			aut.addTransicao("S1", simb, esFinal);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return aut;
	}
	public static Automato automatoChar(){
		Automato aut = new Automato();
		String esFinal = "CHAR";

		aut.addEstado("S0");
		aut.addEstado("S1");
		aut.addEstado("S2");
		aut.addEstado(esFinal);

		aut.addEstadoFinal(esFinal);

		try {
			aut.setEstadoInicial("S0");
			
			for (char i = 32; i <= 126; i++) {
				String simb = "" + i;

				aut.addTransicao("S1", simb, "S2");
			}
			
			String simb = "\"";
			aut.addTransicao("S0", simb, "S1");
			aut.addTransicao("S2", simb, esFinal);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return aut;
	}

	public static Automato automatoVariaveis() {
		Automato aut = new Automato();
		String esFinal = "VAR";

		aut.addEstado("S0", Token.IDENTIFICADOR);

		aut.addEstado(esFinal,Token.IDENTIFICADOR);
		aut.addEstadoFinal(esFinal);
		
		try {
			aut.setEstadoInicial("S0");
			char minusc = 'a';
			char maiusc = 'A';
			for (int i = 0; i <= 25; i++) {

				char iterMin = minusc++;
				char iterMaiusc = maiusc++;

				aut.addTransicao("S0", String.valueOf(iterMin), esFinal);
				aut.addTransicao(esFinal, String.valueOf(iterMin), esFinal);

				aut.addTransicao("S0", String.valueOf(iterMaiusc), esFinal);
				aut.addTransicao(esFinal, String.valueOf(iterMaiusc), esFinal);
			}

			for (int i = 0; i <= 9; i++) {

				aut.addTransicao(esFinal, String.valueOf(i), esFinal);
			}
			String simb = "_";

			aut.addTransicao("S0", simb, esFinal);
			aut.addTransicao(esFinal, simb, esFinal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return aut;
	}

	public static Automato automatoOperadoresBinarios() {
		Automato aut = new Automato();
		String esFinal = "OPERB";

		aut.addEstado("S0");
		aut.addEstado(esFinal);
		aut.addEstado("OPERB2");

		aut.addEstadoFinal(esFinal);
		aut.addEstadoFinal("OPERB2");

		// aut.addEstadoFinal("OPERB_OU");
		// aut.addEstadoFinal("OPERB_AND");

		String simbList[] = { "+", "-", "=", "*", "/", "<", ">" };
		try {
			aut.setEstadoInicial("S0");

			for (String simb : simbList) {

				aut.addTransicao("S0", simb, "OPERB2");
			}
			String simb = "=";

			aut.addTransicao("OPERB2", simb, esFinal);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return aut;
	}

	public static Automato automatoMargens() {
		Automato aut = new Automato();
		String esFinal = "MARGEN";

		aut.addEstado("S0");

		aut.addEstadoFinal(esFinal);

		String simbList[] = { "(", ")", "[", "]", "{", "}" };

		try {
			aut.setEstadoInicial("S0");

			for (String simb : simbList) {

				aut.addTransicao("S0", simb, esFinal);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return aut;
	}
	
	public static Estado criaEstadoErro(){
		
		Estado erro = new Estado("ERRO", Token.ERRO);
		erro.setTerminal(true);
		
		char a = '!';
		for(int i=33; i < 127; i++){
			
			erro.addTransicao(String.valueOf(a++), erro);
		}
		
		return erro;
		
	}
	
	public static Automato montaAutomato(){
		
		

//		Automato aut;
//		aut = automatoConstantes();
//		aut = aut.uniao(automatoReservadas());
//		aut = aut.uniao(automatoVariaveis());
//		aut = aut.uniao(automatoString());
//		aut = aut.uniao(automatoChar());
//		aut = aut.uniao(automatoOperadoresBinarios());
//		aut = aut.uniao(automatoMargens());

		
		Automato aut = automatoVariaveis();	

		

		aut.addEstadoErro(criaEstadoErro());
		
//		System.out.println(aut);
		


//		try {
//			String fim = aut.createSingleEnd();
//			String ini = aut.getEstadoInicial().getNome();
//
//			//aut.addTransicao(fim, "&", ini);
//			aut.addTransicao(fim, " ", ini);
//			aut.addTransicao(ini, " ", ini);
//			aut.addTransicao(fim, "\n", ini);
//			aut.addTransicao(ini, "\n", ini);
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}

		
		return aut;
		
		
//		Automato aut;
//		aut = automatoConstantes();
//		aut = aut.uniao(automatoReservadas());
//		aut = aut.uniao(automatoVariaveis());
//		aut = aut.uniao(automatoOperadoresBinarios());
//		aut = aut.uniao(automatoMargens());
//		
//		String fim = "";
//		String ini = "";
//		
//		
//		try {
//			fim =aut.createSingleEnd();
//			ini = aut.getInicial();
//
//			//aut.addTransicao(fim, "&", ini);
//			aut.addTransicao(fim, " ", ini);
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
//		
//		return aut;
		
	}

}
