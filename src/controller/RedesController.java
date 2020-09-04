package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.Normalizer;																																					

public class RedesController {
	
	public RedesController() {
		super();
	}
	
	public void IP(String so) {
		//Recebe o nome do S.O. como parâmetro e faz a chamada de configuração de IP e filtra a saída do processo, retornando
		// um String com o nome do(s) adaptador(s) Ethernet e o IPv4 apenas.
		
		String linha = "";
		
		if (so.contains("Windows")) {
			System.out.println("Os seguintes Adaptadores Ethernet e respectivos IPv4 foram encontrados: ");
			
			try {
				Process p = Runtime.getRuntime().exec("ipconfig"); //<cmd.exe /c start cmd.exe> se eu quiser de fato abrir o cmd, não só executar
				InputStream fluxo = p.getInputStream();//
				InputStreamReader leitor = new InputStreamReader(fluxo);//
				BufferedReader buffer = new BufferedReader(leitor); //essas linhas são padrões para leitura de processos.
				linha = buffer.readLine();
				String adapt= "";
				while (linha != null) {
					if (linha.contains("Ethernet")) {
						adapt = linha;
					}
					
					if (linha.contains("IPv4") && adapt!="") {
						System.out.println(adapt + "\n" + linha + "\n");
						adapt = "";
					}
					linha = buffer.readLine();
				}
				fluxo.close();
				leitor.close();
				buffer.close();
				
			} catch (IOException e) {
				String msgErro = e.getMessage();
				if (msgErro.contains("error=740")) {
					StringBuffer buffer = new StringBuffer();
					buffer.append("cmd /c ");
					buffer.append("ipconfig");
					try {
						Process p = Runtime.getRuntime().exec(buffer.toString());
						InputStream fluxo2 = p.getInputStream();
						InputStreamReader leitor2 = new InputStreamReader(fluxo2);
						BufferedReader buffer2 = new BufferedReader(leitor2); //essas linhas são padrões para leitura de processos.
						linha = buffer2.readLine();
						while (buffer2.readLine()!= null) {
							System.out.println(linha);
							linha = buffer2.readLine();
						}
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				else {
					e.printStackTrace();
				}
			}
			
		}

		else {
			if (so.contains("Linux")) {
				System.out.println("Detectamos que seu sistema é: " + so);
				
			}
			else {
				System.out.println("Não foi possível executar por conta da imcompatibilidade de sistema! ");
			
			}
		}
		
	}
	
	//-----------------------------------//--------------------------------------------//----------------------------------------------//------------
	
	public void PING(String so) {
		//Recebe o nome do S.O. como parâmetro e faz a chamada de ping com 10 iterações, pegando e devolvendo, em ms, apenas o tempo médio.
		
		String linha = "";
		
		if (so.contains("Windows")) {
			System.out.println("Aguarde enquanto executamos o ping." + "\n");
			
			try {
				Process p = Runtime.getRuntime().exec("ping -n 10 www.google.com.br");
				InputStream fluxo = p.getInputStream();//
				InputStreamReader leitor = new InputStreamReader(fluxo);//
				BufferedReader buffer = new BufferedReader(leitor); //essas linhas são padrões para leitura de processos.
				linha = buffer.readLine();
				while (linha != null) {
					if (linha.contains("Mdia")) {
						String ms = (linha.substring(linha.lastIndexOf(" ")));
						System.out.println("o tempo médio de ping para www.google.com.br foi de:" + ms );
					}
					linha = buffer.readLine();
					if (linha != null) {
					linha = Normalizer
							.normalize(linha, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
					}
				
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		else {
			if (so.contains("Linux")) {
				// bloco de código
			}
			else {
				System.out.println("Não foi possível executar por conta da imcompatibilidade de sistema!");
			}
		}
		
	}
}
