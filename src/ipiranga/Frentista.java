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
		try {
			this.dormindo = false;
			while (posto.getAbastecido()==false ) {
				if(posto.getLitros() > 99 && posto.getCaminhaoChegou() == false){
					posto.abastece(this);
				}
				System.out.print(""); //porque quando tira esse print para de funcionar????
				if(posto.getCaminhaoChegou() == true){
					this.esperando = true;
					System.out.println("caminhão chegou, o frentista " + nome + " esta esperando");
					break;
				}
			}
			while(posto.getCaminhaoChegou() == true){
					Thread.sleep(3000);
			}
			this.esperando = false;
			System.out.println("posto abastecido e o frentista volta a atividade");
			while (posto.getLitros() > 99) {
				posto.abastece(this);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String getNome() {
		return nome;
	}
}
