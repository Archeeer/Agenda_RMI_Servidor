package agenda;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

public class ImplServ extends UnicastRemoteObject implements InterfaceServ {
	
	ArrayList<Compromisso> listaCompromissos = new ArrayList<>();
	ArrayList<Usuario> listaClientes = new ArrayList<>();
	ArrayList<Alerta> listaAlertas = new ArrayList<>();

	protected ImplServ() throws RemoteException {
		super();
	}

//	@Override
//	public PublicKey getPublicKey() throws RemoteException {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@Override
	public void cadastroUsuario(String N, InterfaceCli C) throws RemoteException{
		
		Usuario U = new Usuario(N, C);
		listaClientes.add(U);
		System.out.println("[SERVIDOR]: Cliente: " + N + " Conectado e Usuario cadastrado!");
		     
//		return getPublicKey().hashCode();
	}

	@Override
	public void cadastroCompromisso(String Nome, LocalDate Data, LocalTime Hora, ArrayList<Usuario> Convidados) throws RemoteException {
		
		Compromisso C = new Compromisso (Nome, Data, Hora, Convidados);
		listaCompromissos.add(C);
		System.out.println("[SERVIDOR]: Cliente Adicionou o Compromisso " + Nome );
		
		for(Compromisso compromisso: listaCompromissos) {
		    System.out.println(compromisso);  // Will invoke overrided `toString()` method
		    System.out.println(compromisso.Convidados);  // Will invoke overrided `toString()` method
		}
	
	}
//
	@Override
	public void cancelamentoCompromisso(String Nome) throws RemoteException {

		Iterator<Compromisso> c = listaCompromissos.iterator();
		while (c.hasNext()) {
			Compromisso compromisso = c.next();	
			if (compromisso.getNome().equals(Nome)){
				c.remove();
			}
		}
	}

	@Override
	public String consultaCompromisso(LocalDate Data) throws RemoteException {

		Iterator<Compromisso> c = listaCompromissos.iterator();
		String f = "COMPROMISSOS:\n";
		
		while (c.hasNext()) {
			Compromisso compromisso = c.next();	
			if (compromisso.getData().equals(Data)){
				f = f + compromisso.toString() + "\n" + compromisso.Convidados.toString() + '\n';
				System.out.println("[SERVIDOR]: Achei 1");
			}
			f = f +"\n";
		}
		System.out.println("[SERVIDOR]: Retornando f\n" + f);
		return f;
		
		
	}

	@Override
	public Usuario buscarConvidado(String Nome) throws RemoteException {
		
		System.out.println("[SERVIDOR]: Buscando Convidado");
		for (Usuario user: listaClientes) {
			if (user.getNome().equals(Nome)) {
				return user;
			}
		}
			    return null;
	}
	
	public Compromisso buscarCompromisso(String Nome) throws RemoteException {
		
		System.out.println("[SERVIDOR]: Procurando compromisso");
		for (Compromisso compromisso: listaCompromissos) {
			if (compromisso.getNome().equals(Nome)) {
				return compromisso;
			}
		}
			    return null;
	}

	@Override
	public Alerta criaAlerta(String hora, String nomeCliente, String nomeCompromisso) throws RemoteException {
		
		Alerta temp = new Alerta();
		temp.refCliente = buscarConvidado(nomeCompromisso).getCliente();
		temp.dados = buscarCompromisso(nomeCompromisso);
		temp.Hora =  temp.dados.Hora.minusMinutes(parseLong());
		parse
		//TODO parse String to long
		
		return temp;
		
	}

}

