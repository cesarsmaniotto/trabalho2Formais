/**
 * 
 */
package formais152.Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cesar
 *
 */
public class AnalisadorLexico {

	private Automato automato;
	private TabelaDeSimbolos tabela;

	public AnalisadorLexico(TabelaDeSimbolos tabela) {
		this.tabela = tabela;
		this.automato = FabricaDeAutomatos.analisadorLexico();

	}

	public Map<String, Token> montaTabelaDeSimbolos(String programa) {

		Map<String, Token> simbolos = new HashMap<>();

		int apontadorInicioLexema = 0, apontadorUltimoReconhecido = -1, apontadorProximo = 1;

		String simboloAtual = "";

		boolean voltaAoEstadoInicial = false;
		Estado estadoAtual = automato.getEstadoInicial();

		while (programa.length() >= apontadorProximo) {

			if (voltaAoEstadoInicial) {
				estadoAtual = automato.getEstadoInicial();
			}

			simboloAtual = programa.substring(apontadorProximo - 1, apontadorProximo);
			estadoAtual = estadoAtual.getTransicao(simboloAtual);

			if (estadoAtual.isTerminal()) {

				apontadorUltimoReconhecido = apontadorProximo++;
				
				if (programa.length() == apontadorProximo) {
					String lexema = programa.substring(apontadorInicioLexema, apontadorUltimoReconhecido);
					simbolos.put(lexema, Token.IDENTIFICADOR);
				}				

			} else if (estadoAtual.isErro()) {

				if (apontadorUltimoReconhecido == -1) {
					String lexema = programa.substring(apontadorInicioLexema, apontadorProximo);
					apontadorInicioLexema = apontadorProximo++;

					simbolos.put(lexema, Token.ERRO);
				} else {
					String lexema = programa.substring(apontadorInicioLexema, apontadorUltimoReconhecido);

					apontadorInicioLexema = apontadorUltimoReconhecido;
					apontadorUltimoReconhecido = -1;

					simbolos.put(lexema, Token.IDENTIFICADOR);
				}

				voltaAoEstadoInicial = true;
			} else {
				if (programa.length() == apontadorProximo) {
					simbolos.put(programa.substring(apontadorInicioLexema, apontadorProximo), Token.ERRO);
				}

				apontadorProximo += 1;
			}

		}

		return simbolos;
	}

	public static void main(String[] args) {

		TabelaDeSimbolos tabela = new TabelaDeSimbolos(new ArrayList<>());
		AnalisadorLexico anal = new AnalisadorLexico(tabela);

		System.out.println(anal.montaTabelaDeSimbolos("ce;joao9870gggg@"));

	}

}
