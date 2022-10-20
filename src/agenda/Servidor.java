package agenda;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Servidor extends UnicastRemoteObject  {

    protected Servidor() throws RemoteException {
		super();
	}

	public static void main(String[] args) throws RemoteException{

		DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter horaFormat = DateTimeFormatter.ofPattern("HH:mm");
		LocalDateTime now = LocalDateTime.now();
        Registry registroNomes = null;

        System.out.println("[SERVIDOR]: Criando uma instância do registro de nomes");
    	registroNomes = LocateRegistry.createRegistry(1099); //Criando uma instância do registro de nomes

        System.out.println("[SERVIDOR]: Criando referencia do servidor");
    	ImplServ referenciaServidor = new ImplServ(); //Criando referencia do servidor

    	System.out.println("[SERVIDOR]: Alterando o nome do registro de nomes");
        registroNomes.rebind("Agenda", referenciaServidor); //alterando o nome do registro de nomes

        System.out.println("[SERVIDOR]: Pronto");

        ArrayList<Usuario> e = new ArrayList<>();

        referenciaServidor.cadastroCompromisso("ABC", LocalDate.parse("19/10/2022", dataFormat), LocalTime.parse("20:00", horaFormat), e);
        referenciaServidor.cadastroCompromisso("ACB", LocalDate.parse("19/10/2022", dataFormat), LocalTime.parse("20:00", horaFormat), e);
        referenciaServidor.cadastroCompromisso("BAC", LocalDate.parse("19/10/2022", dataFormat), LocalTime.parse("20:00", horaFormat), e);
        referenciaServidor.cadastroCompromisso("BCA", LocalDate.parse("19/10/2022", dataFormat), LocalTime.parse("20:00", horaFormat), e);
        referenciaServidor.cadastroCompromisso("CAB", LocalDate.parse("19/10/2022", dataFormat), LocalTime.parse("20:00", horaFormat), e);



    }
}
