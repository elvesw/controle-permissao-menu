package br.com.pontek.service;

import java.util.List;

import br.com.pontek.model.Menu;
import br.com.pontek.model.Permissao;
import br.com.pontek.model.Usuario;

public interface PermissaoService {
	
	//Metodos de salvar
 	void salvar(Permissao permissao);
 	
 	//Metodos de remover
    void deletar(Permissao permissao);
    void deletarPorId(Integer id);
    void deletarPorUsuario(Usuario usuario);
    
    //Metodos de buscar
    Permissao buscar(Integer id);
    
    List<Permissao> listaTodos();
    List<Permissao> listaPorUsuario(Usuario usuario);
    Permissao buscaPorMenuUsuario(Menu menu, Usuario usuario);

}
