package br.com.pontek.repository.imp;

import org.springframework.stereotype.Repository;

import br.com.pontek.model.Menu;
import br.com.pontek.repository.MenuDao;


@Repository(value = "menuDao")
public class MenuDaoImp extends AbstractDaoImpl<Menu, Integer> implements MenuDao{

	public MenuDaoImp() {
		super(Menu.class);
	}
	

}
