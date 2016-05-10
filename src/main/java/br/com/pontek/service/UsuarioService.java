package br.com.pontek.service;

import java.util.List;

import br.com.pontek.model.Usuario;

public interface UsuarioService {
	
	//Metodos de salvar
 	void salvar(Usuario usuario);
 	
 	//Metodos de remover
    void deletar(Usuario usuario);
    void deletarPorId(Integer id);
    
    //Metodos de buscar
    Usuario buscar(Integer id);
    List<Usuario> listaTodos();

}
