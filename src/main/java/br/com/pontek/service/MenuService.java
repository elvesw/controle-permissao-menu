package br.com.pontek.service;

import java.util.List;

import br.com.pontek.model.Menu;
public interface MenuService {
	
	//Metodos de salvar
 	void salvar(Menu menu);
 	
 	//Metodos de remover
    void deletar(Menu menu);
    void deletarPorId(Integer id);
    
    //Metodos de buscar
    Menu buscar(Integer id);
    List<Menu> listaTodos();
    


}
