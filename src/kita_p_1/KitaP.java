package kita_p_1;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class KitaP extends JFrame {

	// BACKGROUND RESOURSE-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	private Image screenImage;
	private Graphics screenGraphic;
	private Image BG = new ImageIcon(Main.class.getResource("../images/IntroBG.jpg")).getImage();

	int width = 1920;
	int height = 720;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	

	// BUTTON RESOURSE-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	private ImageIcon ExitEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon ExitBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private ImageIcon PlayEnteredImage = new ImageIcon(Main.class.getResource("../images/playButtonEntered.png"));
	private ImageIcon PlayBasicImage = new ImageIcon(Main.class.getResource("../images/playButtonBasic.png"));
	private ImageIcon LeftEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon LeftBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	private ImageIcon RightEnteredImage = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));
	private ImageIcon RightBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
	private ImageIcon BeginEnteredImage = new ImageIcon(Main.class.getResource("../images/BeginButtonEntered.png"));
	private ImageIcon BeginBasicImage = new ImageIcon(Main.class.getResource("../images/BeginButtonBasic.png"));
	private ImageIcon BackEnteredImage = new ImageIcon(Main.class.getResource("../images/BackButtonEntered.png"));
	private ImageIcon BackBasicImage = new ImageIcon(Main.class.getResource("../images/BackButtonBasic.png"));

	private JButton exitButton = new JButton(ExitBasicImage);
	private JButton playButton = new JButton(PlayBasicImage);
	private JButton leftButton = new JButton(LeftBasicImage);
	private JButton rightButton = new JButton(RightBasicImage);
	private JButton beginButton = new JButton(BeginBasicImage);
	private JButton backButton = new JButton(BackBasicImage);

	// OTHER RESOURSE -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	private boolean isMainScreen = false;
	private boolean isGameScreen = false;

	// TRACKS *-*-*-*-*-*-*-*-*-*-*-*
	ArrayList<Track> trackList = new ArrayList<Track>();
	private Music selectedMusic;
	private Image selectedImage;
	private Image titleImage;
	private int nowSelected = 0;
	private Music IntroMusic = new Music("IntroMusic.mp3", true);
	public static Game game;

	public KitaP() {
		// ADDING SONGS -!-!-!-!-!-!-!-!-!
		trackList.add(new Track("YouAreMineName1.png", "YouAreMineBG.png", "YouAreMineInGame.jpg",
				"YouAreMineSelect.mp3", "YouAreMineMusic.mp3", "หวง  -  เอิ๊ต ภัทรวี"));
		trackList.add(new Track("SawasdeeName.png", "SawasdeeBG.png", "SawasdeeInGame.jpg", "SawasdeeSelect.mp3",
				"SawasdeeMusic.mp3", "สวัสดี!  -  encX"));

		// MAIN BACKGROUND-*-*-*-*-*-*-*--*-*-*-*
		setTitle("KitaP");
		
		setSize(screenSize);
		setResizable(false);
		setLocationRelativeTo(null); //
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //
		setUndecorated(true); //
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null); //
		addKeyListener(new KeyListener());

		// MUSIC----------------
		IntroMusic.start();

		// BUTTONS-----------------

		exitButton.setBounds(1360, 0, 160, 160);
		add(exitButton);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(ExitEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(ExitBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}

		});

		playButton.setBounds(1000, 400, 250, 250);
		add(playButton);
		playButton.setBorderPainted(false);
		playButton.setContentAreaFilled(false);
		playButton.setFocusPainted(false);
		playButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				playButton.setIcon(PlayEnteredImage);
				playButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				playButton.setIcon(PlayBasicImage);
				playButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				enterMain();
			}
		});

		leftButton.setVisible(false);
		leftButton.setBounds(30, 310, 160, 160);
		add(leftButton);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(LeftEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(LeftBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				selectLeft();
			}

		});

		rightButton.setVisible(false);
		rightButton.setBounds(1350, 310, 160, 160);
		add(rightButton);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(RightEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(RightBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				selectRight();
			}

		});

		beginButton.setVisible(false);
		beginButton.setBounds(1250, 600, 250, 250);
		add(beginButton);
		beginButton.setBorderPainted(false);
		beginButton.setContentAreaFilled(false);
		beginButton.setFocusPainted(false);
		beginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				beginButton.setIcon(BeginEnteredImage);
				beginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				beginButton.setIcon(BeginBasicImage);
				beginButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				gameStart(nowSelected);
			}
		});

		backButton.setVisible(false);
		backButton.setBounds(25, 25, 125, 125);
		add(backButton);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(BackEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				backButton.setIcon(BackBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				backMain(nowSelected);
			}
		});

	}

	public void paint(Graphics g) {
		screenImage = createImage(screenSize.width,screenSize.height);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {

		g.drawImage(BG, 0, 0, null);
		if (isMainScreen) {
			g.drawImage(selectedImage, 400, 300, null);
			g.drawImage(titleImage, 525, 40, null);
		}
		if (isGameScreen) {
			game.screenDraw(g);
		}
		paintComponents(g);
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.repaint(); // เรียก paint แบบรวดเร็ว
	}

	public void selectTrack(int nowSelected) {
		if (selectedMusic != null)
			selectedMusic.close();
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage()))
				.getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage()))
				.getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
	}

	public void selectLeft() {
		if (nowSelected == 0)
			nowSelected = trackList.size() - 1;
		else
			nowSelected--;
		selectTrack(nowSelected);
	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1)
			nowSelected = 0;
		else
			nowSelected++;
		selectTrack(nowSelected);
	}

	public void gameStart(int nowSelected) {
		if (selectedMusic != null)
			selectedMusic.close();
		isMainScreen = false;
		beginButton.setVisible(false);
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		BG = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage())).getImage();
		backButton.setVisible(true);
		isGameScreen = true;
		requestFocus(); //
		game = new Game(trackList.get(nowSelected).getTitleName(), trackList.get(nowSelected).getGameMusic());
		game.start();
		setFocusable(true); //
	}

	public void backMain(int nowSelected) {
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		beginButton.setVisible(true);
		BG = new ImageIcon(Main.class.getResource("../images/MainBG.jpg")).getImage();
		selectTrack(nowSelected);
		backButton.setVisible(false);
		isGameScreen = false;
		game.close();
	}

	public void enterMain() {
		IntroMusic.close();
		selectTrack(0);
		playButton.setVisible(false);
		BG = new ImageIcon(Main.class.getResource("../images/MainBG.jpg")).getImage();
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		beginButton.setVisible(true);
	}
}
