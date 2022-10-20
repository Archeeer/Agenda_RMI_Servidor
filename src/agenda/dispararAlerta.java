package agenda;

import java.util.TimerTask;

public class dispararAlerta extends TimerTask{

	String infoCompromisso;
	InterfaceCli refDestino;


	dispararAlerta(String infoCompromisso, InterfaceCli refDestino) {
		this.infoCompromisso = infoCompromisso;
		this.refDestino = refDestino;
	}


	@Override
	public void run() {
	refDestino.mostrarAlerta(infoCompromisso);
	}

}
