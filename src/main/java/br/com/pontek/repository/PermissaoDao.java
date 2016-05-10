package br.com.pontek.repository;

import java.util.List;

import br.com.pontek.model.Menu;
import br.com.pontek.model.Permissao;
import br.com.pontek.model.Usuario;

public interface PermissaoDao extends AbstractDao<Permissao, Integer>{
	
	Permissao buscaPorMenuUsuario(Menu menu, Usuario usuario);
	List<Permissao> listaPorUsuario(Usuario usuario);

}
