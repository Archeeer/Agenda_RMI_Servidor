package agenda;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Servidor extends UnicastRemoteObject  {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	protected Servidor() throws RemoteException {
		super();
	}

	public static void main(String[] args) throws RemoteException{

//		DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		DateTimeFormatter horaFormat = DateTimeFormatter.ofPattern("HH:mm");
//		LocalDateTime now = LocalDateTime.now();
        Registry registroNomes = null;

        System.out.println("[SERVIDOR]: Criando uma instância do registro de nomes");
    	registroNomes = LocateRegistry.createRegistry(1099); //Criando uma instância do registro de nomes

        System.out.println("[SERVIDOR]: Criando referencia do servidor");
    	ImplServ referenciaServidor = new ImplServ(); //Criando referencia do servidor

    	System.out.println("[SERVIDOR]: Alterando o nome do registro de nomes");
        registroNomes.rebind("Agenda", referenciaServidor); //alterando o nome do registro de nomes

        System.out.println("[SERVIDOR]: Pronto");

    }
}
