package br.com.pontek.repository.imp;

import org.springframework.stereotype.Repository;

import br.com.pontek.model.Usuario;
import br.com.pontek.repository.UsuarioDao;

@Repository(value = "usuarioDao")
public class UsuarioDaoImp extends AbstractDaoImpl<Usuario, Integer> implements UsuarioDao{

	public UsuarioDaoImp() {
		super(Usuario.class);
	}
	

}