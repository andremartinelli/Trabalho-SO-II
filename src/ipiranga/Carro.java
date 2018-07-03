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
		try {
			for (int i=0; i<10 && abastecido == false ;i++) {
				if (posto.entraFila(this)==true) {
					System.out.println(identificador + " foi abastecido");
				}
				System.out.println(identificador + " vai dar uma volta, fila cheia!" + posto.imprimeFila());
				
				Thread.sleep(getAleatorio());
				
			}
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
		return (rand.nextInt(5000) + 5000);
		
	}
}
