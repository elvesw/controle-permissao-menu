package br.com.pontek.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pontek.model.Usuario;
import br.com.pontek.repository.UsuarioDao;
import br.com.pontek.service.UsuarioService;

@Service
public class UsuarioServiceImp implements UsuarioService{
	
	@Autowired
	UsuarioDao usuarioDao;

	@Override
	@Transactional
	public void salvar(Usuario usuario) {
		usuarioDao.salvarEntity(usuario);
	}

	@Override
	@Transactional
	public void deletar(Usuario usuario) {
		usuarioDao.deletarEntityPorId(usuario.getId());
	}

	@Override
	@Transactional
	public void deletarPorId(Integer id) {
		usuarioDao.deletarEntityPorId(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario buscar(Integer id) {
		return usuarioDao.buscarEntity(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Usuario> listaTodos() {
		return usuarioDao.listaDeEntitys();
	}

}
