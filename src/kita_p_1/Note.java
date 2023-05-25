package kita_p_1;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int x, y = 725 - 1000 / Main.SLEEP_TIME * Main.NOTE_SPEED;
	private String noteKey;
	private boolean proceeded = true;
	private int level;

	public boolean isProceeded() {
		return proceeded;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(String level) {
		if (level.equals("Easy"))
			this.level = 10;
		else if (level.equals("Normal"))
			this.level = 25;
		else if (level.equals("Hard"))
			this.level = 50;
	}

	public void close() {
		proceeded = false;
	}

	public String getNoteKey() {
		return noteKey;
	}

	public Note(String noteKey) {
		if (noteKey.equals("D"))
			x = 363;
		else if (noteKey.equals("F"))
			x = 568;
		else if (noteKey.equals("J"))
			x = 773;
		else if (noteKey.equals("K"))
			x = 978;
		this.noteKey = noteKey;
	}

	public void drop() throws InterruptedException {
		y += Main.NOTE_SPEED;
		if (Main.BOT) {
			if(y==725 && noteKey.equals("D")) {
				KitaP.game.PressD();
				Thread.sleep(125);
				KitaP.game.ReleaseD();
			}
			else if(y==725 && noteKey.equals("F")) {
				KitaP.game.PressF();
				Thread.sleep(125);
				KitaP.game.ReleaseF();
			}
			else if(y==725 && noteKey.equals("J")) {
				KitaP.game.PressJ();
				Thread.sleep(125);
				KitaP.game.ReleaseJ();
			}
			else if(y==725 && noteKey.equals("K")) {
				KitaP.game.PressK();
				Thread.sleep(125);
				KitaP.game.ReleaseK();
			}
		}
		if (y > 850 + level) {
			System.out.println("Miss" + " y at: " + y);
			KitaP.game.setShowNote("Miss");
			KitaP.game.setMiss();
			close();
		}
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(noteBasicImage, x, y, null);
	}

	@Override
	public void run() {
		try {
			while (true) {
				drop();
				if (proceeded) {
					Thread.sleep(Main.SLEEP_TIME);
				} else {
					interrupt();
					break;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void judge() {
		if (y >= 675 - level && y <= 775 + level) {
			System.out.println("Perfect" + " y at: " + y);
			KitaP.game.setScore(500);
			KitaP.game.setShowNote("Perfect");
			KitaP.game.setPerfect();
			close();
		} else if (y >= 600 - level && y <= 850 + level) {
			System.out.println("Good" + " y at: " + y);
			KitaP.game.setScore(250);
			KitaP.game.setShowNote("Good");
			KitaP.game.setGood();
			close();
		}
	}

}
