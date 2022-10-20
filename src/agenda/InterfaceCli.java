package agenda;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface InterfaceCli extends Remote {
//	public void notificarCompromisso(Compromisso C);
//	public void conviteCompromisso(Compromisso C);
	public Date setNotificacao(String notificacao) throws RemoteException;
	public Alerta atulizarNotificao(String notificacao,int Hora,int Minuto,int Segundo)throws RemoteException;
}