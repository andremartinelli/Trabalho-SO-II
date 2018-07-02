package ipiranga;

import java.util.Random;

public class Caminhao extends Thread{
	String nome;
	Ipiranga posto;
	public Caminhao(String nome, Ipiranga posto){
		this.nome = nome;
		this.posto = posto;
	}
	@Override
	public void run() {
		try {
//			System.out.println("Caminhoneiro indo pro posto abastecer");
			Thread.sleep(esperaPraAbastecer());//espera o tempo de chegar no posto
			posto.enchePosto(this);//abastece o posto
			
		} catch (InterruptedException e) {
		}
		
	}
	public int esperaPraAbastecer(){
		Random rand = new Random();
		return (rand.nextInt(40000) + 10000);
	}
}
