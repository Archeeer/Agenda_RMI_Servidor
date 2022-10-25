package agenda;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceCli extends Remote {

	public void mostrarAlerta(String infoCompromisso) throws RemoteException;
	public void mostrarConvite(Compromisso compromisso, byte[] compAssinado) throws RemoteException;

}