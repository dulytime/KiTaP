package kita_p_1;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) {
		if (KitaP.game == null) {
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			KitaP.game.PressD();
		} else if (e.getKeyCode() == KeyEvent.VK_F) {
			KitaP.game.PressF();
		} else if (e.getKeyCode() == KeyEvent.VK_J) {
			KitaP.game.PressJ();
		} else if (e.getKeyCode() == KeyEvent.VK_K) {
			KitaP.game.PressK();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (KitaP.game == null) {
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			KitaP.game.ReleaseD();
		} else if (e.getKeyCode() == KeyEvent.VK_F) {
			KitaP.game.ReleaseF();
		} else if (e.getKeyCode() == KeyEvent.VK_J) {
			KitaP.game.ReleaseJ();
		} else if (e.getKeyCode() == KeyEvent.VK_K) {
			KitaP.game.ReleaseK();
		}
	}
}
