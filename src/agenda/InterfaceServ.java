package agenda;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public interface InterfaceServ extends Remote{
//
//    PublicKey getPublicKey()throws RemoteException;
//
	public PublicKey cadastroUsuario(String N, InterfaceCli C) throws RemoteException;
	public void cadastroCompromisso(String nomeOrigem, String nomeCompromisso, LocalDate parse, LocalTime parse2, ArrayList<Usuario> e) throws RemoteException, SignatureException;
    public void cancelamentoCompromisso(String Nome) throws RemoteException;
    public String consultaCompromisso(LocalDate data) throws RemoteException;
    public Usuario buscarConvidado(String Nome) throws RemoteException;
	public void criaAlerta(String linhaHora, String nomeCliente, String nomeCompromisso) throws RemoteException, SignatureException;
	public void cancelamentoAlerta(String Nome) throws RemoteException;
}
