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
		this.dormindo = false;
	}
	@Override	
	public void run() {
			while (posto.getAbastecido()==false ) {
				if(posto.getLitros() > 99 || posto.getCaminhaoChegou()){
					posto.abastece(this);
				}
				System.out.print(""); //porque quando tira esse print para de funcionar????
				}
//			}
			while (posto.getLitros() > 99) {//volta a abastecer
				posto.abastece(this);
			}
	}
	public String getNome() {
		return nome;
	}
}
