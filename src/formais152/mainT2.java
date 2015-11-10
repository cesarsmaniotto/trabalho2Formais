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
		String[] palavras = {"for","new","float","int","const","final","static","double","char","bool","long","short","typedef","class","struct","namespace","using","if","else","while","return"};
		
		for(String pr : palavras){
			palavrasReservadas.add(pr);
		}

		TabelaDeSimbolos tabela = new TabelaDeSimbolos(palavrasReservadas);
		AnalisadorLexico anal = new AnalisadorLexico(tabela);

		anal.montaTabelaDeSimbolos("int a = 3 ; if ( a > 1 ) { a = 1 ; } else { a = 2 } double b = a + 4 * 2 ; char s = \"stringsemespacos\" ;");

		System.out.println(tabela.getTokens());
		
	}

}
