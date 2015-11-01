package formais152.Modelo;

public class FabricaDeAutomatos {
	
	
	public static Automato automatoConstantes(){
		Automato aut = new Automato();
		String esFinal="CONST";
		
		aut.addEstado("S0");
		aut.addEstado(esFinal);
		aut.addEstado("CONST2");
		
		aut.addEstadoFinal(esFinal);
		aut.addEstadoFinal("CONST2");
		
		try {
			aut.setEstadoInicial("S0");
			
			for(int i=0; i<=9 ; i++){
				String simb="";
				simb+=i;
				aut.addTransicao("S0", simb, esFinal);
				aut.addTransicao(esFinal, simb, esFinal);
				aut.addTransicao("CONST2", simb, "CONST2");
			}
			String simb="";
			simb+='.';
			aut.addTransicao("S0", simb, "CONST2");
			aut.addTransicao(esFinal, simb, "CONST2");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return aut;
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
		aut.addEstado(esFinal);
		
		aut.addEstadoFinal(esFinal);
		
		try {
			aut.setEstadoInicial("S0");	
			for(int i='a'; i<='z' ; i++){
				String simb="";
				simb+=i;
				aut.addTransicao("S0", simb, esFinal);
				aut.addTransicao(esFinal, simb, esFinal);
			}
			for(int i='A'; i<='Z' ; i++){
				String simb="";
				simb+=i;
				aut.addTransicao("S0", simb, esFinal);
				aut.addTransicao(esFinal, simb, esFinal);
			}
			for(int i=0; i<=9 ; i++){
				String simb="";
				simb+=i;
				aut.addTransicao(esFinal, simb, esFinal);
			}
			String simb="";
			simb+='_';
			aut.addTransicao("S0", simb, esFinal);
			aut.addTransicao(esFinal, simb, esFinal);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Automato res = automatoReservadas();
		res = res.obterAutomatoFechamento();
		
		aut = aut.interseccao(res);
		aut = aut.removerEpsilonTransicoes();
		aut = aut.determinizar();
		aut = aut.obterAutomatoMinimo();
		

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
				simb="";
				aut.addTransicao("S0", simb, "OPERB2");
			}
			String simb="";
			simb+='=';
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
		aut.addEstado(esFinal);

		aut.addEstadoFinal(esFinal);

		String simbList[]={"(",")","[","]","{","}"};
		
		try {
			aut.setEstadoInicial("S0");
			
			for(String simb: simbList){
				simb="";
				aut.addTransicao("S0", simb, esFinal);
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return aut;
	}
	
	

}
