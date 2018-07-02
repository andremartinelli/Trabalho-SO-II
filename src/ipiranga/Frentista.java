package ipiranga;

public class Frentista extends Thread {
	Ipiranga posto;
	String nome;
	boolean esperando;
	boolean dormindo;
	public Frentista(Ipiranga posto, String nome) {
		this.posto=posto;
		this.nome=nome;
		this.esperando = false;
		this.dormindo = true;
	}
	@Override	
	public void run() {
//		try {
			this.dormindo = false;
			while (posto.getAbastecido()==false ) {
				if(posto.getLitros() > 99 || posto.getCaminhaoChegou()){
					posto.abastece(this);
				}
				System.out.print(""); //porque quando tira esse print para de funcionar????
//				if(posto.getCaminhaoChegou() == true){//se o caminhao chegar
//					this.esperando = true;//seta como esperando
//					System.out.println("caminhão chegou, o frentista " + nome + " esta esperando");
//					break;//sai do loop e a thread espera até o caminhão sair
				}
//			}
//			while(posto.getCaminhaoChegou() == true){
//					Thread.sleep(3000);//thread esperando enquanto o caminhão está no posto
//			}
//			this.esperando = false;//frentista não esta mais esperando
//			System.out.println("posto abastecido e o frentista volta a atividade");
			while (posto.getLitros() > 99) {//volta a abastecer
				posto.abastece(this);
			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
	}
	public String getNome() {
		return nome;
	}
}
