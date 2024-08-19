package control;

import model.CalculadoraMetroJardas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ServidorEcho {

	private ServerSocket sckServidor;

	private CalculadoraMetroJardas calc;

	public ServidorEcho() throws IOException {
		this.sckServidor = new ServerSocket(4001);
		calc = new CalculadoraMetroJardas();
		NumberFormat formatter = new DecimalFormat("#0.00");

		for (;;) {
			Socket sckEcho;
			InputStream canalEntrada;
			OutputStream canalSaida;
			BufferedReader entrada;
			PrintWriter saida;

			sckEcho = this.sckServidor.accept();
			canalEntrada = sckEcho.getInputStream();
			canalSaida = sckEcho.getOutputStream();
			entrada = new BufferedReader(new InputStreamReader(canalEntrada));
			saida = new PrintWriter(canalSaida, true);

			while (true) {
				String linhaPedido = entrada.readLine();
				Double resultado;

				if (linhaPedido == null || linhaPedido.length() == 0)
					break;

				String mensagem = linhaPedido;

				if(linhaPedido.equals("1")){

					String jardas = entrada.readLine();
					Double jardasValor = Double.parseDouble(jardas);
					resultado = calc.converteJardasMetro(jardasValor);

					saida.println("Em metros: "+ formatter.format(resultado));


				}else{

					String metros = entrada.readLine();
					Double metrosValor = Double.parseDouble(metros);
					resultado = calc.converteMetroJardas(metrosValor);

					saida.println("Em jardas: "+ formatter.format(resultado));

				}






				saida.println("Echo: " + mensagem);
			}
			sckEcho.close();
		}
	}
}
