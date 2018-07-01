package ipiranga;

import java.util.Random;

public class Carro extends Thread{
	String identificador;
	Ipiranga posto;
	boolean abastecido;
	boolean fila;
	public Carro(Ipiranga posto, String identificador) {
		this.posto=posto;
		this.fila=false;
		this.abastecido=false;
		this.identificador = identificador;
		
	}
	@Override
	public void run() {
//		System.out.println("Iniciou Carro");
		try {
			for (int i=0; i<10 && abastecido == false ;i++) {
//				System.out.println("tenta entrar na fila de carros");
				if (posto.entraFila(this)==true) {
					System.out.println(identificador + " foi abastecido");
//					fila = true;
				}
				System.out.println(identificador + " vai dar uma volta, fila cheia!");
				Thread.sleep(getAleatorio());
				
			}
//			if (fila==true) {
//				
////				System.out.println(identificador + " Abasteceu");
//				
//			}else {
//				System.out.println("Eu sou o dono do carro "+ identificador +" vou para passeata");
//				
//			}
			if(abastecido == false){
				System.out.println("Eu sou o dono do carro "+ identificador +" vou para passeata");
			}
		}catch(Exception e) {
			
		}
	}
	public boolean getAbastecido() {
		return abastecido;
	}
	public String  getIdentificador() {
		return identificador;
	}
	public void setFila(boolean valor) {
		this.fila = valor;
	}
	public void setAbastecido(boolean ab){
		this.abastecido = ab;
	}
	public int getAleatorio(){
		Random rand = new Random();
		return (rand.nextInt(10000) + 5000);
		
	}
}
