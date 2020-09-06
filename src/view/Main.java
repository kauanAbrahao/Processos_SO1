package view;

import javax.swing.JOptionPane;

import controller.RedesController;

public class Main { //Escolhe qual a��o quer fazer.
	public static void main(String[] args) {
		RedesController redes = new RedesController();
		boolean manter = true;
		
		String so = System.getProperty("os.name");
		if (so.contains("Windows")) {
			System.out.println("Voc� selecionou configura��o de IP. Detectamos que seu sistema � Windows. Mais precisamente, " + so);
		}
		else {
			if(so.contains("Linux")) {
				System.out.println("Detectamos que seu sistema é Linux.");
			}
			else {
				System.out.println("Seu Sistema Operacional n�o � compat�vel com o sistema!");
			}
		}
	
			
		
		while(manter) {
			int input = Integer.parseInt(JOptionPane.showInputDialog("Você acabou de Acessar o Sistema IP/PING! " + "\n" + "Escolha: " + "\n" + "1 - Mostrar IPv4 e"
					+ " adaptadores Ethernet" + "\n" + "2 - Mostrar tempo médio de ping www.google.com.br" + "\n" + "0 - Sair do sistema"));
			
			switch(input) {
			
			case 1:
				redes.IP(so);
				break;
				
			case 2:
				redes.PING(so);
				break;
			
			case 0:
				manter = false;
				System.out.println("Sistema finalizado!");
				break;
				
			default:
				System.out.println("Op��o inv�lida. Tente novamente");
				break;
			}
			
		}
		
	}
}
