package program;

import java.awt.*;
import javax.swing.*;

import classes.PacoteViagem;
import classes.CadastroPacote;
import classes.CadastroCliente;
import classes.ServicoAdicional;

public class Main extends JFrame{
	
	public void cadastroCliente() {
		CadastroCliente cadastro = new CadastroCliente();
		
		String[] opcoesAdm = {"Listar Cliente", "Buscar Cliente", "Eliminar Cliente", "Listar Cliente-Pacote", "Sair"};
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
               		cadastro.Listar();
                   break;
               case 1:
                   	cadastro.Procurar();
                   break;
               case 2:
               		cadastro.Eliminar();
               	break;
               case 3:
            	   cadastro.clientePacote();
            	   break;
               case 4:
                   JOptionPane.showMessageDialog(null, "Saindo...");
                   break;
               default:
                   break;
           }
       } while (opcao != 4);
	}
	
	public void cadastroPacote() {
		CadastroPacote cadastroP = new CadastroPacote();
		
		String[] opcoesAdm = {"Listar Pacotes", "Pesquisar Pacotes", "Excluir Pacotes", "Incluir serviço", "Saindo"};
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
            	   cadastroP.listarPacotes();
                   break;
               case 1:
                   cadastroP.pesquisarPacotes();
                   break;
               case 2:
                   cadastroP.excluirPacote();
                   break;
               case 3:
                   cadastroP.incluirServico();
                   break;
               case 4:
            	   JOptionPane.showMessageDialog(null, "Saindo...");
            	   break;
               default:
                   break;
           }
       } while (opcao != 4);
		
	}
	
	public static void menu() {
		Main main = new Main();
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
                   main.cadastroCliente();
                   break;
               case 1:
                   main.cadastroPacote();
                   break;
               case 2:
                   JOptionPane.showMessageDialog(null, "Saindo...");
                   break;
               default:
                   break;
           }
       } while (opcao != 2);
   }
	public static void main(String[] args) {
		Main main = new Main();
		main.menu();
	}
}
