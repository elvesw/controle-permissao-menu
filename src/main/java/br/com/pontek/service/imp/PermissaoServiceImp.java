package br.com.pontek.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pontek.model.Menu;
import br.com.pontek.model.Permissao;
import br.com.pontek.model.Usuario;
import br.com.pontek.repository.PermissaoDao;
import br.com.pontek.repository.UsuarioDao;
import br.com.pontek.service.PermissaoService;

@Service
public class PermissaoServiceImp implements PermissaoService{
	
	@Autowired
	PermissaoDao permissaoDao;
	@Autowired
	UsuarioDao usuarioDao;

	@Override
	@Transactional
	public void salvar(Permissao permissao) {
		permissaoDao.salvarEntity(permissao);
	}

	@Override
	@Transactional
	public void deletar(Permissao permissao) {
		permissaoDao.deletarEntityPorId(permissao.getId());
	}

	@Override
	@Transactional
	public void deletarPorId(Integer id) {
		permissaoDao.deletarEntityPorId(id);
	}
	
	@Override
	@Transactional
	public void deletarPorUsuario(Usuario usuario) {
		permissaoDao.deleteAllEntitys(permissaoDao.listaPorUsuario(usuario));
	}

	@Override
	@Transactional(readOnly=true)
	public Permissao buscar(Integer id) {
		return permissaoDao.buscarEntity(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Permissao> listaTodos() {
		return permissaoDao.listaDeEntitys();
	}

	@Override
	@Transactional(readOnly=true)
	public Permissao buscaPorMenuUsuario(Menu menu, Usuario usuario) {
		return permissaoDao.buscaPorMenuUsuario(menu, usuario);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Permissao> listaPorUsuario(Usuario usuario) {
		return permissaoDao.listaPorUsuario(usuario);
	}

}
