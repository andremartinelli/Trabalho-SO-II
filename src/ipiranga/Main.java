package ipiranga;

import java.util.LinkedList;
public class Main {

	public static void main(String[] args) {
		LinkedList<Frentista> trab = new LinkedList<Frentista>();
		Ipiranga perguntaLa=new Ipiranga(trab);
		Caminhao bino= new Caminhao("Bino", perguntaLa);
		Frentista seuZe =new Frentista(perguntaLa,"Seu Zé");
		Frentista Dougras=new Frentista(perguntaLa,"Dougras");
		Frentista Cleverson=new Frentista(perguntaLa,"Cleverson");
		trab.add(seuZe);
		trab.add(Dougras);
		trab.add(Cleverson);
		LinkedList<Carro> carros =new LinkedList<Carro>();
		bino.start();
		seuZe.start();
		Dougras.start();
		Cleverson.start();
		for(int i=0; i<50;i++) {
			carros.addLast(new Carro(perguntaLa, "carro "+i) );
			
		}
		for(int i=0; i<50;i++) {			
			carros.get(i).start();
		}
		
		
	}

}
