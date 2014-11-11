package br.com.localhost8080.iav;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * Verifica se o computador tem acesso a internet. Caso nao tenha, tentara novamente apos 10 segundos.
 * Quando tiver acesso, fara o computador dar 5 beeps.
 * 
 * @author Adriano Schmidt ( www.localhost8080.com.br )
 */
public class InternetAccessValidator {

	public static void main(String[] args) throws IOException, InterruptedException {

		while (true) {
			
			// Este eh o inicio da mensagem retornada pelo ping quando nao consegue acessar a internet
			String error = "A solicit";

			// Executa o comando ping no terminal da maquina
			Process p = Runtime.getRuntime().exec("ping www.localhost8080.com.br");

			// Obtem o retorno do comando
			BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String retorno = bf.readLine();

			// Se o retorno for diferente da mensagem de erro significa que esta com acesso a internet
			if (retorno.startsWith(error) == false) {
				System.out.println("Voltou a internet!!!");
				System.out.println("Agora são: " + new Date());
				
				// Faz 5 beeps
				for (int i = 1; i < 5; i++) {
					Toolkit.getDefaultToolkit().beep();
					Thread.sleep(1000);
				}

				// finaliza o programa
				break;
			} else {
				// caso nao tenha acesso a internet informa no log e aguarda 10 segundos para tentar novamente
				System.out.println("Não funcionou: " + retorno);
				Thread.sleep(10000);
			}
			
		}
		
	}

}
