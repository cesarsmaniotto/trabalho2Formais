/**
 * 
 */
package formais152;

import java.util.ArrayList;
import java.util.List;

import formais152.Modelo.AnalisadorLexico;
import formais152.Modelo.TabelaDeSimbolos;

/**
 * @author cesar
 *
 */
public class mainT2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<String> palavrasReservadas = new ArrayList<>();
		palavrasReservadas.add("if");
		palavrasReservadas.add("else");
		palavrasReservadas.add("while");

		TabelaDeSimbolos tabela = new TabelaDeSimbolos(palavrasReservadas);
		AnalisadorLexico anal = new AnalisadorLexico(tabela);

		anal.montaTabelaDeSimbolos("\"ee\" if else cesar ( ) == += ; \"aaa");

		System.out.println(tabela.getTokens());
		
	}

}
