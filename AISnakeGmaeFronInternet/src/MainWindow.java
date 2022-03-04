



import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class MainWindow extends JFrame implements ActionListener{
	/**
	 * main function of the whole simulation
	 */
	KeyboardListener keyb;
	GameLoop gameLoop;
	JPanel panel = new JPanel();
	JButton pauseButton = new JButton("Pause");
	JButton restartButton = new JButton("Restart Training");
	JButton toggleStatsButton = new JButton("Hide Stats");
	JButton runBestSnakeButton = new JButton("Run Best Snake");
	JLabel label = new JLabel(" Restart after changning params ");
	JTextField numSnakesField;
	JTextField speedField;
	JTextField numFoodField;
	JPanel updatePeriodPanel = new JPanel();
	JPanel numSnakesPanel = new JPanel();
	JPanel numFoodPanel = new JPanel();
	JLabel warning;
	
	
	ArrayList<JPanel> paramList = new ArrayList<>();
	boolean paused = false;
	public static void main(String[] args) {
		new MainWindow();
	}
	/**
	 * Simple JFrame as user interface
	 */
	public MainWindow() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize( 1000, 600);
		setExtendedState(MAXIMIZED_BOTH);
		setTitle("Neural Net Snake Genetic Algorithm");
		addKeyListener(keyb);
		gameLoop = new GameLoop();
		add(BorderLayout.CENTER,gameLoop);
		setUpGui();
		setVisible(true);
	}
	public void setUpGui() {
		pauseButton.addActionListener(this);
		toggleStatsButton.addActionListener(this);
		runBestSnakeButton.addActionListener(this);
		restartButton.addActionListener(this);
		
		JLabel speedLabel = new JLabel("Speed 0-80 ");
		updatePeriodPanel.add(speedLabel);
		speedField = new JTextField("60",5);
		updatePeriodPanel.add(speedField);
		
		JLabel numSnakesLabel = new JLabel("Number of Snakes ");
		numSnakesPanel.add(numSnakesLabel);
		numSnakesField = new JTextField("10",5);
		numSnakesPanel.add(numSnakesField);
		
		JLabel numFoodLabel = new JLabel("Number of Food ");
		numFoodPanel.add(numFoodLabel);
		numFoodField = new JTextField("10",5);
		numFoodPanel.add(numFoodField);
		
		warning = new JLabel("Enter valid integers, or suffer the consequences...");
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.add(label);
		panel.add(pauseButton);
		panel.add(restartButton);
		panel.add(toggleStatsButton);
		panel.add(runBestSnakeButton);
		panel.add(updatePeriodPanel);
		panel.add(numSnakesPanel);
		panel.add(numFoodPanel);
		panel.add(warning);
		this.add(BorderLayout.EAST, panel);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == pauseButton) {
			if (gameLoop.simulationPaused == false) {
				gameLoop.simulationPaused = true;
				pauseButton.setText("Resume");
			}else  {
				gameLoop.simulationPaused = false;
				pauseButton.setText("Pause");
			}
		}else if (e.getSource() == toggleStatsButton) {
			if (gameLoop.displayStatisticsActive == true) {
				gameLoop.displayStatisticsActive = false;
				toggleStatsButton.setText("Hide Stats");
			}else {
				gameLoop.displayStatisticsActive = true;
				toggleStatsButton.setText("Show Stats");
			}
		}else if (e.getSource() == runBestSnakeButton) {
			gameLoop.activateSingleSnakeMode();
		}else if (e.getSource() == restartButton) {
			double speed = Double.parseDouble(speedField.getText());
			speed = 100-speed;
			speed = speed/10;
			long speed2 = (long) Math.ceil(speed);
			if (speed <= 0) {
				speed = 1;
			}
			gameLoop.resetTraining(speed2, Integer.parseInt(numSnakesField.getText()), Integer.parseInt(numFoodField.getText()));


		}
		
	}
	
	
	

}

