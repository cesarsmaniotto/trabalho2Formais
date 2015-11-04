package formais152.Modelo;

public class FabricaDeAutomatos {
	
	
	public static Automato automatoConstantes(){
		Automato aut = new Automato();
		String esFinal="CONST";
		
		aut.addEstado("S0");
	
		aut.addEstadoFinal(esFinal);
		aut.addEstadoFinal("CONST2");
		
		try {
			aut.setEstadoInicial("S0");
			
			for(int i=0; i<=9 ; i++){
				String simb=""+i;

				aut.addTransicao("S0", simb, esFinal);
				aut.addTransicao(esFinal, simb, esFinal);
				aut.addTransicao("CONST2", simb, "CONST2");
			}
			String simb=".";
			
			aut.addTransicao("S0", simb, "CONST2");
			aut.addTransicao(esFinal, simb, "CONST2");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return aut.obterAutomatoMinimo();
	}
	public static Automato automatoReservadas(){
		Expressao exp=InputOutput.criarExpressao("palavrasReservadas.txt");
		Automato aut = null; 
		try {
		    aut =exp.obterAutomato();
		    aut = aut.removerEpsilonTransicoes();
			aut = aut.determinizar();
			aut = aut.obterAutomatoMinimo();
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return aut;
	}
	public static Automato automatoVariaveis(){
		Automato aut = new Automato();
		String esFinal="VAR";
		
		aut.addEstado("S0");

		
		aut.addEstadoFinal(esFinal);
		
		try {
			aut.setEstadoInicial("S0");	
			char minusc= 'a';
			char maiusc = 'A';
			for(int i=0; i<=25 ; i++){

				char iterMin = minusc++;
				char iterMaiusc = maiusc++;
				
				aut.addTransicao("S0", String.valueOf(iterMin), esFinal);
				aut.addTransicao(esFinal, String.valueOf(iterMin), esFinal);
				
				aut.addTransicao("S0", String.valueOf(iterMaiusc), esFinal);
				aut.addTransicao(esFinal, String.valueOf(iterMaiusc), esFinal);
			}
			
			for(int i=0; i<=9 ; i++){

				aut.addTransicao(esFinal, String.valueOf(i), esFinal);
			}
			String simb="_";

			aut.addTransicao("S0", simb, esFinal);
			aut.addTransicao(esFinal, simb, esFinal);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Automato res = automatoReservadas();
//		res = res.obterAutomatoFechamento();
		
//		aut = aut.interseccao(res);
//		aut = aut.removerEpsilonTransicoes();
//		aut = aut.determinizar();
		aut = aut.obterAutomatoMinimo();
		
//		aut = aut.interseccao(res);
		return aut;
	}
	public static Automato automatoOperadoresBinarios(){
		Automato aut = new Automato();
		String esFinal="OPERB";
		
		aut.addEstado("S0");
		aut.addEstado(esFinal);
		aut.addEstado("OPERB2");

		aut.addEstadoFinal(esFinal);
		aut.addEstadoFinal("OPERB2");
		
	//	aut.addEstadoFinal("OPERB_OU");
	//	aut.addEstadoFinal("OPERB_AND");
		
		String simbList[]={"+","-","=","*","/","<",">"};
		try {
			aut.setEstadoInicial("S0");
			
			for(String simb: simbList){
				
				aut.addTransicao("S0", simb, "OPERB2");
			}
			String simb="=";

			aut.addTransicao("OPERB2", simb, esFinal);
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return aut;
	}
	public static Automato automatoMargens(){
		Automato aut = new Automato();
		String esFinal="MARGEN";
		
		aut.addEstado("S0");

		aut.addEstadoFinal(esFinal);

		String simbList[]={"(",")","[","]","{","}"};
		
		try {
			aut.setEstadoInicial("S0");
			
			for(String simb: simbList){

				aut.addTransicao("S0", simb, esFinal);
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return aut;
	}
	
	

}
