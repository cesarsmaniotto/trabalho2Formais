package formais152.Modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabelaDeSimbolos {
	
	private Map<String,Token> tabela;
	
	public TabelaDeSimbolos(List<String> palavrasReservadas) {
		
		tabela = new HashMap<>();		
		adicionaPalavrasReservadas(palavrasReservadas);
	}
	
	private void adicionaPalavrasReservadas(List<String> palavrasReservadas){
		
		for(String pr : palavrasReservadas){
			tabela.put(pr, Token.PALAVRA_RESERVADA);
		}
		
	}

	public void adicionaItem(String lexema, Token token){
		
		if(tabela.containsKey(lexema)){
			token = tabela.get(lexema);
		}
		
		tabela.put(lexema, token);
	}

	public Map<String, Token> getTabela() {
		return tabela;
	}

	
	
	
}
