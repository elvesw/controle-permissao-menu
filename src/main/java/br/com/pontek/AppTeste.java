package br.com.pontek;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import br.com.pontek.model.Menu;
import br.com.pontek.model.Permissao;
import br.com.pontek.model.Usuario;
import br.com.pontek.service.MenuService;
import br.com.pontek.service.PermissaoService;
import br.com.pontek.service.UsuarioService;



@Component
public class AppTeste {
	
	@Autowired UsuarioService usuarioService;
	@Autowired PermissaoService permissaoService;
	@Autowired MenuService menuService;

	public static void main(String[] args) {
		final ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		
		final AppTeste app = context.getBean(AppTeste.class);
		app.inicioTestes();
	}
	
	void inicioTestes(){
		System.out.println("Iniciando Testes...");
		List<Permissao> listaPermissao = new ArrayList<Permissao>();
		List<Menu> listaMenu = new ArrayList<Menu>();
		Usuario usuarioSelecionado = new Usuario();
		usuarioSelecionado.setId(2);
		
		listaPermissao = permissaoService.listaTodos();
        listaMenu = menuService.listaTodos();
        
        System.out.println("Lista de menus...");
       for (Menu m : listaMenu) {
    	   System.out.println("Id :"+m.getId()+"Menu :"+m.getDescricao()+" indice: "+m.getIndice());
       }
       System.out.println("");
       System.out.println("Lista de permissoes...");
       for (Permissao p : listaPermissao) {
    	   System.out.println("Id :"+p.getId()+" menu_id: "+p.getMenu().getId()+" menu_descricao: "+p.getMenu().getDescricao()+" usuario_login: "+p.getUsuario().getLogin());
       }
		
	}

}
