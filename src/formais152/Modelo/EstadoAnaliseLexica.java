/**
 * 
 */
package formais152.Modelo;

import java.util.List;

/**
 * @author cesar
 *
 */
public class EstadoAnaliseLexica extends Estado {

	private List<ItemLexico> itensLexicos;

	public EstadoAnaliseLexica(Estado estado) {
		super(estado);
		// TODO Auto-generated constructor stub
	}

	public EstadoAnaliseLexica(String nome, boolean terminal) {
		super(nome, terminal);
	}

	public EstadoAnaliseLexica(String nome) {
		super(nome);
	}

	public List<ItemLexico> getItensLexicos() {
		return itensLexicos;
	}
	
	public void addItemLexico(ItemLexico item){
		itensLexicos.add(item);
	}
	
	public boolean possuiItemLexico(ItemLexico item){
		return itensLexicos.contains(item);
	}
	
	

	
	
	

}
