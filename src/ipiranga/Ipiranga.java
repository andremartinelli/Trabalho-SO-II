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
			if (fila.size()<10){ //se a fila por merno que 10
				fila.addLast(aspirante); //entra na fila
				aspirante.setFila(true);
				System.out.println(aspirante.getIdentificador()+" entrou na fila de espera para abastecer");
				if(fila.size() == 1){ //caso seja o primeiro da fila
					if(trabalhadores.get(0).dormindo || trabalhadores.get(1).dormindo || trabalhadores.get(2).dormindo){
						System.out.println("VAO TRABAIÁ FRENTISTAS!!!");						
					}
					notifyAll(); //acorda os frentistas
				}
				wait();
				
				while (aspirante.fila == true) {
					wait();//espera ser atendido
				}
				notifyAll();
				return true; //conseguiu ser atendido, saiu da fila
			}
			
		}catch (Exception e){
			
		}
		return false;//não conseguiu entrar na fila
	}
	
	public synchronized void abastece(Frentista frentista) {
		try {
			while (fila.size()==0) { //se a fila de carro estiver vazia
				if(frentista.dormindo != true){
					System.out.println("eu sou "+frentista.getNome()+ ", nao tem carro na fila :( && vou dormir! ");
				}
				frentista.dormindo = true;
				wait();
				
			}
			if(!getCaminhaoChegou()){ //se o caminhão não estiver no posto, abastece, se não, espera o caminhão sair do posto			
				if (litros >= 100 && fila.size() != 0) {
						if(frentista.dormindo){
							System.out.println("ACORDEI CALMA AÍ - disse " + frentista.getNome());			
							frentista.dormindo = false;//verifica se o frentista estava dormindo, caso não estivesse ele abastece sem printar que acordou
						}
						System.out.println(fila.getFirst().getIdentificador()+" sendo abastecido por: " + frentista.getNome());
						fila.getFirst().setAbastecido(true);
						fila.getFirst().fila = false;
						System.out.println(fila.getFirst().getIdentificador()+" foi abastecido");
						litros -= 100;
						fila.removeFirst();
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
			System.out.println("Caminhoneiro na area, esperando os carros que já estão abastecendo..." + caminhaoChegou);
			notifyAll();
			while(!trabalhadores.get(0).esperando && !trabalhadores.get(1).esperando && !trabalhadores.get(2).esperando){
				notifyAll();
				wait(5000);
				//notifica pros frentistas terminarem de abastecer
				//espera caso algum frentista ainda esteja abastecendo
				//quando todos estiverem esperando(ninguem abastecendo), ele sai do loop
			}
			System.out.println("Caminhão abastecendo, aguarde um instante...");
			caminhaoAbastece(); //aumenta o tempo 
			wait(5000); //abastece por 5 seg
			abastecido = true;
			System.out.println("Posto abastecido, eu to dando o fora daqui tá ligado meu irmão");
			this.caminhaoChegou = false; //caminhão vai embora
			notifyAll(); //acorda os frentistas
		}catch(Exception e){
		}
	}
	
	
	public String imprimeFila(){ //retorna os carros que estão na lista de carros
		String fil = " -- Carros na fila: [";
		fil = fil + this.fila.getFirst().getIdentificador();
		for(int i = 1; i < this.fila.size(); i++){
			fil = fil + ", " + this.fila.get(i).getIdentificador();
		}
		fil = fil + "]";
		return fil;
	}
	
	public int getLitros() {
		return litros;
	}
	public boolean getAbastecido() {
		return abastecido;
	}
	public int getAleatorio(){
		Random rand = new Random();
		return (rand.nextInt(2000) + 8000);
		
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
