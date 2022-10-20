package agenda;

import java.rmi.RemoteException;
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
	try {
		System.out.println("[SERVIDOR]: dispararAlerta enviando alerta para o cliente");
		refDestino.mostrarAlerta(infoCompromisso);
	} catch (RemoteException e) {
		e.printStackTrace();
	}
	}

}