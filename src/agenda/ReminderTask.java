package agenda;

import java.util.Timer;
import java.util.TimerTask;

public class ReminderTask extends TimerTask {

	//classe incia a notificacao
		Timer timer;

		@Override
		public void run() {
			System.out.println("Notificação!!!");
			timer.cancel();
		}

}
