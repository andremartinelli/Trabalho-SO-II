package ipiranga;

import java.util.LinkedList;
import java.util.Random;

public class Ipiranga {
	boolean abastecido;
	LinkedList<Carro> fila;
	boolean caminhaoChegou;
	int litros;
	LinkedList<Frentista> trabalhadores;
	
	public Ipiranga(LinkedList<Frentista> f) {
		fila = new LinkedList<Carro>();
		trabalhadores = f;
		litros=2000;
		caminhaoChegou = false;
	}
	
	public synchronized boolean entraFila(Carro aspirante) {
		try {
//			if (fila.size()==0) {
//				System.out.println("fila vazia, acordando todos!");
//				fila.addLast(aspirante);
//				notifyAll();
//			}
//			System.out.println("tamanho da fila de trabalhadores: " + trabalhadores.size());
			if (fila.size()<10){
				fila.addLast(aspirante);
				aspirante.setFila(true);
				System.out.println(aspirante.getIdentificador()+" entrou na fila de espera para abastecer");
				if(fila.size() == 1){
					if(trabalhadores.get(0).dormindo || trabalhadores.get(1).dormindo || trabalhadores.get(2).dormindo){
						System.out.println("VAO TRABAIÁ FRENTISTAS!!!");						
					}
					notifyAll();
				}
				wait();
//				while (!aspirante.equals(fila.getFirst())) {
//					System.out.println(aspirante.getIdentificador()+" Dormindo");
//					wait();
//				}
				
				while (aspirante.fila == true) {
					System.out.println(aspirante.getIdentificador()+" esperando sua vez");
					wait();
				}
				notifyAll();
//				fila.removeFirst();
//				System.out.println(aspirante.getIdentificador()+" Abasteceu!");
//				System.out.println(fila.size());
				
				return true;
			}
			
		}catch (Exception e){
			
		}
		return false;
	}
	
	public synchronized void abastece(Frentista frentista) {
		try {
			while (fila.size()==0) {
				if(frentista.dormindo != true){
					System.out.println("eu sou "+frentista.getNome()+ ", nao tem carro na fila :( && vou dormir! ");
				}
				frentista.dormindo = true;
				wait();
				
			}
			if(!getCaminhaoChegou()){				
				if (litros >= 100 && fila.size() != 0) {
						if(frentista.dormindo){
							System.out.println("ACORDEI CALMA AÍ - disse " + frentista.getNome());			
							frentista.dormindo = false;//verifica se o frentista estava dormindo, caso não estivesse ele abastece sem printar que acordou
						}
						System.out.println(fila.getFirst().getIdentificador()+" sendo abastecido por: " + frentista.getNome());
						fila.getFirst().setAbastecido(true);
						fila.getFirst().fila = false;
						
						fila.removeFirst();
						litros -= 100;
	//					Thread.sleep(1000);
						wait(getAleatorio());
						notifyAll();
				}
			}else{
				System.out.println("caminhão chegou, o frentista " + frentista.getNome() + " esta esperando");
				frentista.esperando = true;
				wait();
				frentista.esperando = false;
			}
			
			
		}catch (Exception e) {
			
		}	
	}
	
	public synchronized void enchePosto(Caminhao caminhao){
		try{
			setCaminhaoChegou(); //avisa que o caminhao chegou setando pra true
//			this.caminhaoChegou = true;
//			caminhaoChegou = true;
//			
			System.out.println("Caminhoneiro na area, esperando os carros que já estão abastecendo..." + caminhaoChegou);
			notifyAll();
//			wait();
			while(!trabalhadores.get(0).esperando && !trabalhadores.get(1).esperando && !trabalhadores.get(2).esperando){
//				System.out.println("trabalhador 0 esperando: " + trabalhadores.get(0).esperando);
//				System.out.println("trabalhador 1 esperando: " + trabalhadores.get(1).esperando);
//				System.out.println("trabalhador 2 esperando: " + trabalhadores.get(2).esperando);
				notifyAll();
				wait(5000);
				//notifica todo mundo pros frentistas terminarem de abastecer
				//espera caso algum frentista ainda esteja abastecendo
				//quando todos estiverem esperando, ele sai do loop
			}
			System.out.println("Caminhão abastecendo, aguarde um instante...");
			caminhaoAbastece(); //aumenta o tempo 
			wait(5000); //abastece por 5 seg
			abastecido = true;
			System.out.println("Posto abastecido, eu to dando o fora daqui tá ligado meu irmão");
			this.caminhaoChegou = false; //camiinhão vai embora
			notifyAll(); //acorda os frentistas
		}catch(Exception e){
		}
	}
	
	
	
	
	public int getLitros() {
		return litros;
	}
	public boolean getAbastecido() {
		return abastecido;
	}
	public int getAleatorio(){
		Random rand = new Random();
		return (rand.nextInt(10000) + 8000);
		
	}
	public boolean getCaminhaoChegou(){
		return caminhaoChegou;
	}
	public void caminhaoAbastece(){
		litros = litros+500;
	}
	public void setCaminhaoChegou(){
		this.caminhaoChegou = true;
	}
		
}
