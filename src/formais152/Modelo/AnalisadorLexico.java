/**
 * 
 */
package formais152.Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cesar
 *
 */
public class AnalisadorLexico {
	
	private AutomatoAnaliseLexica automato;
	
	public AnalisadorLexico() {
		
	}
	
	public List<Pair<String, ItemLexico>> montaTabelaDeSimbolos(String programa) {

		List<Pair<String, ItemLexico>> listaDeTokens = new ArrayList<>();
		String palavra = "";
		String simboloAtual = "";
		String proximoSimbolo = "";
		EstadoAnaliseLexica estadoAtual = (EstadoAnaliseLexica) automato.getEstadoInicial();

		while (programa.length() > 0) {

			simboloAtual = programa.substring(0, 1);
			proximoSimbolo = programa.substring(1, 2);
			palavra += simboloAtual;
			programa = programa.substring(1);

			estadoAtual = (EstadoAnaliseLexica) estadoAtual.transitar(
					simboloAtual).get(0);

			if (StringUtil.ehNuloOuEspacoEmBranco(proximoSimbolo)) { // ou
																		// separador

				if (estadoAtual.isTerminal()) {
					listaDeTokens.add(montaToken(estadoAtual, palavra));

				} else {
					listaDeTokens.add(new Pair<String, ItemLexico>(palavra,
							ItemLexico.ERRO));
				}
				palavra = "";

			} else if (proximoSimbolo.equals(";")) {
				listaDeTokens.add(montaToken(estadoAtual, palavra));

				palavra = "";

			}

		}

		return listaDeTokens;
	}
	
	private Pair<String,ItemLexico> montaToken(EstadoAnaliseLexica estado, String palavra){
		
		if(estado.possuiItemLexico(ItemLexico.PALAVRA_RESERVADA)){
			return new Pair<String, ItemLexico>(palavra, ItemLexico.PALAVRA_RESERVADA);
		}
		
		return new Pair<String, ItemLexico>(palavra, estado.getItensLexicos().get(0));
		
	}
	
	public void geraAutomato(){
		
	
		Automato autVar = FabricaDeAutomatos.automatoVariaveis();
		Automato autConst = FabricaDeAutomatos.automatoConstantes();
		Automato autPr = FabricaDeAutomatos.automatoReservadas();
		Automato autOp = FabricaDeAutomatos.automatoOperadoresBinarios();
		Automato autMarg = FabricaDeAutomatos.automatoMargens();
		
//		Automato autUniao = autVar.uniao(autConst);
//		autUniao = autUniao.uniao(autPr);
//		autUniao = autUniao.uniao(autOp);
//		autUniao = autUniao.uniao(autMarg);
		
	
		
		System.out.println(autVar);
	}

}
