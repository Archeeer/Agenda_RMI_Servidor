package agenda;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Timer;

public class ImplServ extends UnicastRemoteObject implements InterfaceServ {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Compromisso> listaCompromissos = new ArrayList<>();
	ArrayList<Usuario> listaClientes = new ArrayList<>();
	ArrayList<Timer> listaAlertas = new ArrayList<>();

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
	public void cadastroCompromisso(String nomeOrigem, String  Nome, LocalDate Data, LocalTime Hora, ArrayList<Usuario> Convidados) throws RemoteException {

		Compromisso compromisso = new Compromisso (Nome, Data, Hora, Convidados);
		listaCompromissos.add(compromisso);
		System.out.println("[SERVIDOR]: Cliente Adicionou o Compromisso " + Nome );

		if(!Convidados.isEmpty()) {
			for(Usuario usuario : Convidados) {
				if(!usuario.getNome().equals(nomeOrigem))
					usuario.getCliente().mostrarConvite(compromisso);
			}
		}

		for(Compromisso compCont: listaCompromissos) {
			System.out.println(compCont);  // Will invoke overrided `toString()` method
			System.out.println(compCont.Convidados);  // Will invoke overrided `toString()` method
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

		String f = "COMPROMISSOS:\n";

		for (Compromisso compromisso : listaCompromissos) {
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
	public void criaAlerta(String hora, String nomeCliente, String nomeCompromisso) throws RemoteException {

		Usuario usr = buscarConvidado(nomeCliente);
		InterfaceCli refDestino = usr.getCliente();
		Compromisso compAlerta = buscarCompromisso(nomeCompromisso);
		String infoCompromisso = compAlerta.toString() + compAlerta.Convidados.toString();
		LocalTime tempo =  compAlerta.Hora.minusMinutes(Long.parseLong(hora));

		Instant instant = compAlerta.Data.atTime(tempo).atZone(ZoneId.systemDefault()).toInstant();
		Date d = Date.from(instant);
		String nomeTimer = nomeCompromisso + usr.getCliente().toString();
		Timer timer = new Timer(nomeTimer);
		System.out.println("[SERVIDOR]: criarAlerta chamando dispararAlerta");
		timer.schedule(new dispararAlerta(infoCompromisso, refDestino), d);
		listaAlertas.add(timer);	}

	@Override
	public void cancelamentoAlerta(String Nome) throws RemoteException {
		for(Timer busca : listaAlertas) {
			if(Thread.currentThread().getName().equals(Nome));
				busca.cancel();
		}
	}

}

