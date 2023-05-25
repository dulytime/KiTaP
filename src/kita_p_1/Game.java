package kita_p_1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.ImageIcon;

public class Game extends Thread {

	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();

	private int score = 0,perfect=0,good=0,miss=0;
	private String titleName; // ชื่อเพลงที่จะแสดง
	private String hitNote = "";
	private String musicTitle; // เพลงที่จะใช้เล่น
	private Music gameMusic;
	private String level;
	private ArrayList<String> beatmap = new ArrayList<String>();
	private ArrayList<Note> noteList = new ArrayList<Note>();
	
	private boolean isSummary=false;
	private String Summary;

	public Game(String titleName, String musicTitle) {
		this.titleName = titleName;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
		gameMusic.start();
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(noteRouteDImage, 363, -496, null);
		g.drawImage(noteRouteFImage, 568, -496, null);
		g.drawImage(noteRouteJImage, 773, -496, null);
		g.drawImage(noteRouteKImage, 978, -496, null);
		g.drawImage(noteRouteLineImage, 358, -396, null);
		g.drawImage(noteRouteLineImage, 563, -396, null);
		g.drawImage(noteRouteLineImage, 768, -396, null);
		g.drawImage(noteRouteLineImage, 973, -396, null);
		g.drawImage(noteRouteLineImage, 1178, -396, null);
		g.drawImage(gameInfoImage, 0, 804, null);
		g.drawImage(judgementLineImage, 0, 725, null);
		
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (!note.isProceeded()) {
				noteList.remove(i);
				i--;
			} else {
				note.screenDraw(g);
			}
		}
		
		g.setColor(Color.white);
		g.setFont(new Font("Cordia New", Font.PLAIN, 50));
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.drawString(titleName, 30, 854);
		g.drawString("D", 453, 760);
		g.drawString("F", 658, 760);
		g.drawString("J", 863, 760);
		g.drawString("K", 1068, 760);
		g.setFont(new Font("FC Marshmallow", Font.BOLD, 70));
		// g.setColor(Color.green);
		// g.drawString("Score : ", 1368 ,854);
		if (titleName.equals("หวง  -  เอิ๊ต ภัทรวี"))
			g.setColor(Color.BLACK);
		else
			g.setColor(Color.CYAN);
		g.drawString(String.format("%06d", score), 30, 200);
		g.drawString(hitNote, 30, 300);
		if(isSummary) {
			g.setFont(new Font("FC Marshmallow", Font.BOLD, 140));
			g.drawString(Summary, 550, 200);
		}
	}

	public void setShowNote(String hitNote) {
		this.hitNote = hitNote;
	}

	public int getPerfect() {
		return perfect;
	}

	public void setPerfect() {
		this.perfect++;
	}

	public int getGood() {
		return good;
	}

	public void setGood() {
		this.good++;
	}

	public int getMiss() {
		return miss;
	}

	public void setMiss() {
		this.miss++;
	}

	public void PressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}

	public void ReleaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void PressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}

	public void ReleaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void PressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}

	public void ReleaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void PressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}

	public void ReleaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score += score;
	}

	@Override
	public void run() {
		dropNotes();
	}

	public void close() {
		gameMusic.close();
		this.interrupt();
	}

	public void dropNotes() {
		ArrayList<Beat> beats = new ArrayList<Beat>();
		if (titleName.equals("หวง  -  เอิ๊ต ภัทรวี")) {
			int startTime = 2450 - Main.REACH_TIME * 1000;
			int gap = 80;// 3 gap per beat
			level = "Easy";
			setMap(titleName);
			changeToBeat(beats, startTime, gap);
		} else if (titleName.equals("สวัสดี!  -  encX")) {
			int startTime = 1650 - Main.REACH_TIME * 1000;
			int gap = 37;
			level = "Hard";
			setMap(titleName);
			changeToBeat(beats, startTime, gap);
		}
		int i = 0;

		while (i < beats.size() && !isInterrupted()) {
			if (beats.get(i).getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats.get(i).getNoteName());
				note.setLevel(level);
				note.start(); // Start run Method of Note Class!!
				noteList.add(note);
				i++;
			}
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		close();
		summary();
	}
	public void summary() {
		if (miss==0 && good==0) {
			Summary="All Perfect";
		}
		else if(miss==0 && good!=0) {
			Summary="All Combo";
		}
		else if(miss!=0) {
			Summary="Clear";
		}
		isSummary=true;
	}
	public void judge(String input) {
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (input.equals(note.getNoteKey())) {
				note.judge();
				break;
			}
		}
	}

	public void setMap(String titleName) {
		try {
			File myObj = new File(Main.class.getResource("../beatmaps/" + titleName + ".txt").toURI());
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				beatmap.add(myReader.nextLine());
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.reverse(beatmap);
	}

	public void changeToBeat(ArrayList<Beat> beats, int StartTime, int gap) {
		for (int i = 0; i < beatmap.size(); i++) {
			if (beatmap.get(i).charAt(0) == '1') {
				beats.add(new Beat(StartTime + gap * (i * 2), "D"));
			}
			if (beatmap.get(i).charAt(1) == '1') {
				beats.add(new Beat(StartTime + gap * (i * 2), "F"));
			}
			if (beatmap.get(i).charAt(2) == '1') {
				beats.add(new Beat(StartTime + gap * (i * 2), "J"));
			}
			if (beatmap.get(i).charAt(3) == '1') {
				beats.add(new Beat(StartTime + gap * (i * 2), "K"));
			}
		}
	}
}