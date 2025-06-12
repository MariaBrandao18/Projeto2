import java.sql.SQLException;

import javax.swing.*;

import classes.CadastroPacote;
import classes.CadastroCliente;

public class Main extends JFrame{
	
	public static void cadastroCliente() throws SQLException {
		
		String[] opcoesAdm = {"Listar Cliente", "Buscar Cliente", "Eliminar Cliente", "Cadastrar Cliente", "Sair"};
       int opcao;
       do {
           opcao = JOptionPane.showOptionDialog(
               null,
               "Pacotes de Viagem",
               "Menu",
               JOptionPane.DEFAULT_OPTION,
               JOptionPane.INFORMATION_MESSAGE,
               null,
               opcoesAdm,
               opcoesAdm[0]
           );
          
           switch (opcao) {
               case 0:                	
               		CadastroCliente.ListarCliente();
                   break;
               case 1:
                   	CadastroCliente.ProcurarCliente();
                   break;
               case 2:
            	   CadastroCliente.EliminarCliente();
               	break;
               case 3:
            	   CadastroCliente.incluirCliente();
            	   break;
               case 4:
                   JOptionPane.showMessageDialog(null, "Saindo...");
                   break;
               default:
                   break;
           }
       } while (opcao != 4);
	}
	
	public static void cadastroPacote() throws SQLException {
		
		String[] opcoesAdm = {"Inserir Pacote", "Listar Pacotes", "Pesquisar Pacotes", "Excluir Pacotes", "Incluir serviço", "Saindo"};
       int opcao;
       do {
           opcao = JOptionPane.showOptionDialog(
               null,
               "Pacotes de Viagem",
               "Menu",
               JOptionPane.DEFAULT_OPTION,
               JOptionPane.INFORMATION_MESSAGE,
               null,
               opcoesAdm,
               opcoesAdm[0]
           );
          
           switch (opcao) {
           	case 0: 
        	   CadastroPacote.inserirPacote();
               case 1:
            	   CadastroPacote.listarPacotes();
                   break;
               case 2:
            	   CadastroPacote.pesquisarPacotes();
                   break;
               case 3:
            	   CadastroPacote.excluirPacote();
                   break;
               case 4:
            	   CadastroPacote.incluirServico();
                   break;
               case 5:
            	   JOptionPane.showMessageDialog(null, "Saindo...");
            	   break;
               default:
                   break;
           }
       } while (opcao != 5);
		
	}
	
	public static void menu() throws SQLException {
       String[] opcoes = {"Cadastro do Cliente", "Cadastro do Pacote", "Sair"};
       int opcao;
       do {
           opcao = JOptionPane.showOptionDialog(
               null,
               "Gerenciador",
               "Menu",
               JOptionPane.DEFAULT_OPTION,
               JOptionPane.INFORMATION_MESSAGE,
               null,
               opcoes,
               opcoes[0]
           );
          
           switch (opcao) {
               case 0:
                   Main.cadastroCliente();
                   break;
               case 1:
                   Main.cadastroPacote();
                   break;
               case 2:
                   JOptionPane.showMessageDialog(null, "Saindo...");
                   break;
               default:
                   break;
           }
       } while (opcao != 2);
   }
	public static void main(String[] args) throws SQLException {
		Main.menu();
	}
}
