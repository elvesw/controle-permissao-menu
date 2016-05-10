package br.com.pontek.repository.imp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.pontek.model.Menu;
import br.com.pontek.model.Permissao;
import br.com.pontek.model.Usuario;
import br.com.pontek.repository.PermissaoDao;


@Repository(value = "permissaoDao")
public class PermissaoDaoImp extends AbstractDaoImpl<Permissao, Integer> implements PermissaoDao{

	public PermissaoDaoImp() {
		super(Permissao.class);
	}

	@Override
	public Permissao buscaPorMenuUsuario(Menu menu, Usuario usuario) {
		Session session = (Session) getEm().getDelegate();
		Criteria criteria = session.createCriteria(Permissao.class);
	
		criteria.add(Restrictions.eq("menu",menu));
		criteria.add(Restrictions.eq("usuario",usuario));
		return (Permissao) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permissao> listaPorUsuario(Usuario usuario) {
		Session session = (Session) getEm().getDelegate();
		Criteria criteria = session.createCriteria(Permissao.class);
		
		criteria.add(Restrictions.eq("usuario",usuario));
		return criteria.list();
	}

}
