package br.com.pontek.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.pontek.model.Menu;
import br.com.pontek.model.Permissao;
import br.com.pontek.model.Usuario;
import br.com.pontek.service.MenuService;
import br.com.pontek.service.PermissaoService;
import br.com.pontek.service.UsuarioService;
import br.com.pontek.util.jsf.FacesUtil;

@ManagedBean(name = "permissaoBean")
@Controller
@Scope("session")
public class PermissaoBean {
    
    @Autowired UsuarioService usuarioService;
    @Autowired PermissaoService permissaoService;
    @Autowired MenuService menuService;
	
	 	private TreeNode root;
	    private TreeNode[] nosSelecionados;
	    private Usuario usuarioSelecionado = new Usuario();
	    private List<Usuario> listaUsuario = new ArrayList<Usuario>();
	    private List<Permissao> listaPermissao = new ArrayList<Permissao>();
	    private List<Menu> listaMenu = new ArrayList<Menu>();
	    private List<Menu> menusPermitidos =  new ArrayList<Menu>();
	    private List<Menu> menusUsuario =  new ArrayList<Menu>();
	    
	    

	    
	    @PostConstruct
		public void init(){
	    	listaUsuario = usuarioService.listaTodos();
	    }
	 
	    
	    private boolean isFilho(String[] indicesPai, String[] indicesFilho) {
	        boolean isFilho = false;
	        int i = 0;
	        if (indicesPai.length == indicesFilho.length - 1) {
	            for (String s : indicesPai) {
	                isFilho = s.equals(indicesFilho[i]);
	                if (!isFilho) {
	                    break;
	                }
	                i++;
	            }
	        }
	        return isFilho;
	    }
	 
	    private void carregarFilhos(List<Menu> menus, Menu menu, TreeNode treeNode) {
	    	for (Menu m : menus) {
	            String[] indicesPai = menu.getIndice().split("\\.");
	            String[] indicesFilho = m.getIndice().split("\\.");
	            TreeNode tr;
	            if ((indicesFilho.length > indicesPai.length)) {
	                if (isFilho(indicesPai, indicesFilho)) {
	                    if (m.getUrl().equals("")) {
	                        tr = new CheckboxTreeNode(m, treeNode);
	                        carregarFilhos(menus, m, tr);
	                    } else {
	                        tr = new CheckboxTreeNode(m, treeNode);
	                        if (isPermitido(m)) {
	                            tr.setSelected(true);
	                        }
	                    }
	                }
	            }
	        }
	    }
	 
	    /**Função que faz a checagem se um menu está na lista de permitidos
	     * @param Menu (que pode vir de uma lista de todos os menus)*/
	    private boolean isPermitido(Menu menu) {
	        boolean retorno = false;
	        for (Menu m : menusPermitidos) {
	            if (m.equals(menu)) {
	                retorno = true;
	                break;
	            }
	        }
	        return retorno;
	    }
	 
	    public void carregaPermissoesUsuario() {

	        root = new CheckboxTreeNode("Qp", null);
	        menusPermitidos = new ArrayList<Menu>();
	        try {
	            listaPermissao = permissaoService.listaPorUsuario(usuarioSelecionado);

	            System.out.println("Lista de permissoes por usuário...");
	            for (Permissao p : listaPermissao) {
	         	   System.out.println("Id :"+p.getId()+" menu_id: "+p.getMenu().getId()+" menu_descricao: "+p.getMenu().getDescricao()+" usuario_login: "+p.getUsuario().getLogin());
	            }
	            listaMenu = menuService.listaTodos();

	 
	            for (Permissao p : listaPermissao) {
	                menusPermitidos.add(p.getMenu());
	            }
	            
	            if (usuarioSelecionado.getId() != 0) {
	                for (Menu menu : listaMenu) {
	                    if ((menu.getUrl().trim().equals("")) && (menu.getIndice().length() == 1)) {
	                        TreeNode treeNode = new CheckboxTreeNode(menu, root);
	                        carregarFilhos(listaMenu, menu, treeNode);
	                    }
	                }
	            }
	        } catch (Exception e) {
	            FacesUtil.exibirMensagemErro("Erro: "+e.getMessage());
	        }
	    }
	 
	    public void salvaPermissoes() {
	        if (usuarioSelecionado.getId() == 0) {
	        	FacesUtil.exibirMensagemErro("Nenhum usuário SELECIONADO");
	        } else {
	            try {
	                permissaoService.deletarPorUsuario(usuarioSelecionado);
	                for (TreeNode no : nosSelecionados) {
	                    if (no.isLeaf()) {
	                        Menu folha = (Menu) no.getData();
	                        Permissao permissao = new Permissao();
	                        permissao.setMenu(folha);
	                        permissao.setUsuario(usuarioSelecionado);
	 
	                        permissaoService.salvar(permissao);
	                        salvaPai(no);
	                    }
	                }
	                carregaPermissoesUsuario();
	                FacesUtil.exibirMensagemSucesso("Permissões Salvas");
	            } catch (Exception e) {
		            FacesUtil.exibirMensagemErro("Erro: "+e.getMessage());
		        }
	        }
	    }
	 
	    public void salvaPai(TreeNode no) {
	        TreeNode tr = no.getParent();
	        if (!tr.equals(root)) {
	            Permissao p;
	            try {
	                p = permissaoService.buscaPorMenuUsuario((Menu) tr.getData(), usuarioSelecionado);
	                if (p == null) {
	                    Permissao permissao = new Permissao();
	                    permissao.setUsuario(usuarioSelecionado);
	                    permissao.setMenu((Menu) tr.getData());
	                    permissaoService.salvar(permissao);
	                }
	                salvaPai(tr);
	            } catch (Exception e) {
		            FacesUtil.exibirMensagemErro("Erro: "+e.getMessage());
		        }
	        }
	    }
	    
	    
	    /*  ######## GETS E SETS ###############*/
		public TreeNode getRoot() {
			return root;
		}

		public void setRoot(TreeNode root) {
			this.root = root;
		}

		public TreeNode[] getNosSelecionados() {
			return nosSelecionados;
		}

		public void setNosSelecionados(TreeNode[] nosSelecionados) {
			this.nosSelecionados = nosSelecionados;
		}

		public Usuario getUsuarioSelecionado() {
			return usuarioSelecionado;
		}

		public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
			this.usuarioSelecionado = usuarioSelecionado;
		}

		public List<Usuario> getListaUsuario() {
			return listaUsuario;
		}

		public void setListaUsuario(List<Usuario> listaUsuario) {
			this.listaUsuario = listaUsuario;
		}

		public List<Permissao> getListaPermissao() {
			return listaPermissao;
		}

		public void setListaPermissao(List<Permissao> listaPermissao) {
			this.listaPermissao = listaPermissao;
		}

		public List<Menu> getListaMenu() {
			return listaMenu;
		}

		public void setListaMenu(List<Menu> listaMenu) {
			this.listaMenu = listaMenu;
		}

		public List<Menu> getMenusPermitidos() {
			return menusPermitidos;
		}

		public void setMenusPermitidos(List<Menu> menusPermitidos) {
			this.menusPermitidos = menusPermitidos;
		}

		public List<Menu> getMenusUsuario() {
			return menusUsuario;
		}

		public void setMenusUsuario(List<Menu> menusUsuario) {
			this.menusUsuario = menusUsuario;
		}


	    /*############################################
	    ###########################################*/
	    
	   
		
		public void verNodesSelecionados() {
			TreeNode[] nodes= nosSelecionados;
	        if(nodes != null && nodes.length > 0) {
	        	Menu menu = new Menu();
	            for(TreeNode node : nodes) {
	            	menu = (Menu) node.getData();
	            	FacesUtil.exibirMensagemSucesso(menu.getDescricao());
	            }
	            
	        }
	    }
	    
}
