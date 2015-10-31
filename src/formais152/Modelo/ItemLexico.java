/**
 * 
 */
package formais152.Modelo;

/**
 * @author cesar
 *
 */
public enum ItemLexico {
	
	CONSTANTE("CTE"),
	PALAVRA_RESERVADA("PR"),
	OPERADOR("OP"),
	IDENTIFICADOR("ID"),
	SEPARADOR("SEP"),
	
	ERRO("ERRO");
	
	private String tipoItem;
	
	private ItemLexico(String tipoItem){
		this.tipoItem = tipoItem;
	}

}
