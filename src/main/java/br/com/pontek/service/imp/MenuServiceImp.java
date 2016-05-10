package br.com.pontek.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pontek.model.Menu;
import br.com.pontek.repository.MenuDao;
import br.com.pontek.service.MenuService;

@Service
public class MenuServiceImp implements MenuService{
	
	@Autowired
	MenuDao menuDao;

	@Override
	@Transactional
	public void salvar(Menu menu) {
		if(menu.getId()==null){
			menuDao.salvarEntity(menu);
		}else{
			menuDao.atualizarEntity(menu);
		}
	}

	@Override
	@Transactional
	public void deletar(Menu menu) {
		menuDao.deletarEntityPorId(menu.getId());
	}

	@Override
	@Transactional
	public void deletarPorId(Integer id) {
		menuDao.deletarEntityPorId(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Menu buscar(Integer id) {
		return menuDao.buscarEntity(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Menu> listaTodos() {
		return menuDao.listaDeEntitys();
	}

}
