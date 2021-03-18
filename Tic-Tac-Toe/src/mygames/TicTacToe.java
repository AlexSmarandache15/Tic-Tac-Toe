package mygames;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * @author AlexSmarandache
 * @date 18/03/2021
 * @category game
 */

public class TicTacToe implements ActionListener {

	private JPanel title = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JLabel text = new JLabel();
	private JFrame myFrame = new JFrame();
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	Boolean turn; // true - player 1, false - player 2
	private int counter;
	
	public TicTacToe() {
		turn = true;
		counter = 0;
	}

	public void play() {

		initFrame();

		initText();

		initTitle();

		initButtons();

		myFrame.add(title, BorderLayout.NORTH);
		myFrame.add(buttonPanel);
	}

	private void initFrame() {
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(800, 800);
		ImageIcon img = new ImageIcon("icon.png");
		myFrame.setIconImage(img.getImage());
		myFrame.setTitle("Tic Tac Toe - by Alex Smarandache");
		myFrame.getContentPane().setBackground(Color.BLACK);
		myFrame.setLayout(new BorderLayout());
		myFrame.setVisible(true);
	}

	private void initText() {
		text.setBackground(Color.WHITE);
		text.setForeground(Color.BLUE);
		text.setFont(new Font("Consolas", Font.BOLD, 75));
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setText("Tic Tac Toe");
		text.setOpaque(true);
	}

	private void initTitle() {
		title.setLayout(new BorderLayout());
		title.setBounds(0, 0, 800, 100);
		title.add(text);
	}

	private void initButtons() {

		buttonPanel.setLayout(new GridLayout(3, 3));
		buttonPanel.setBackground(Color.DARK_GRAY);

		for (int i = 0; i < 9; i++) {
			buttons.add(new JButton());
			buttonPanel.add(buttons.get(buttons.size() - 1));
			buttons.get(buttons.size() - 1).setFont(new Font("Arial", Font.BOLD, 120));
			buttonPanel.add(buttons.get(buttons.size() - 1));
			buttons.get(buttons.size() - 1).setFocusable(false);
			buttons.get(buttons.size() - 1).addActionListener(this);
		}
	}

	private boolean existWinner() {
		if (buttons.get(0).getText() == buttons.get(1).getText()
				&& buttons.get(2).getText() == buttons.get(0).getText()) {
			if (buttons.get(0).getText() != "") {
				Integer[] pos = { 0, 1, 2 };
				displayWinner(buttons.get(0).getText(), pos);
				return true;
			}
		} else if (buttons.get(3).getText() == buttons.get(4).getText()
				&& buttons.get(5).getText() == buttons.get(3).getText()) {
			if (buttons.get(3).getText() != "") {
				Integer[] pos = { 3, 4, 5 };
				displayWinner(buttons.get(3).getText(), pos);
				return true;
			}
		} else if (buttons.get(6).getText() == buttons.get(7).getText()
				&& buttons.get(8).getText() == buttons.get(6).getText()) {
			if (buttons.get(6).getText() != "") {
				Integer[] pos = { 6, 7, 8 };
				displayWinner(buttons.get(6).getText(), pos);
				return true;
			}
		} else if (buttons.get(0).getText() == buttons.get(3).getText()
				&& buttons.get(0).getText() == buttons.get(6).getText()) {
			if (buttons.get(3).getText() != "") {
				Integer[] pos = { 0, 3, 6 };
				displayWinner(buttons.get(0).getText(), pos);
				return true;
			}
		} else if (buttons.get(1).getText() == buttons.get(4).getText()
				&& buttons.get(1).getText() == buttons.get(7).getText()) {
			if (buttons.get(1).getText() != "") {
				Integer[] pos = { 1, 4, 7 };
				displayWinner(buttons.get(1).getText(), pos);
				return true;
			}
		} else if (buttons.get(2).getText() == buttons.get(5).getText()
				&& buttons.get(2).getText() == buttons.get(8).getText()) {
			if (buttons.get(2).getText() != "") {
				Integer[] pos = { 2, 5, 8 };
				displayWinner(buttons.get(2).getText(), pos);
				return true;
			}
		} else if (buttons.get(0).getText() == buttons.get(4).getText()
				&& buttons.get(0).getText() == buttons.get(8).getText()) {
			if (buttons.get(0).getText() != "") {
				Integer[] pos = { 0, 4, 8 };
				displayWinner(buttons.get(0).getText(), pos);
				return true;
			}
		} else if (buttons.get(2).getText() == buttons.get(4).getText()
				&& buttons.get(2).getText() == buttons.get(6).getText()) {
			if (buttons.get(2).getText() != "") {
				Integer[] pos = { 2, 4, 6 };
				displayWinner(buttons.get(2).getText(), pos);
				return true;
			}
		}

		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		boolean isWinner = false;
		
		for (JButton iterator : buttons) {
			if (e.getSource() == iterator) {
				if (turn) {
					if (iterator.getText() == "") {
						iterator.setForeground(Color.CYAN);
						iterator.setText("X");
						counter++;
						if (!existWinner()) {
							turn = false;
							text.setText("O turn");
						} else {
							isWinner = true;
						}

					}
				} else {
					if (iterator.getText() == "") {
						iterator.setForeground(Color.RED);
						iterator.setText("O");
						counter++;
						if (!existWinner()) {
							turn = true;
							text.setText("X turn");
						} else {
							isWinner = true;
						}

					}
				}
			}
		}
		if(counter == 9 && !isWinner) {
			text.setText("Draw");
		}
	}

	private void displayWinner(String name, Integer[] pos) {
		buttons.get(pos[0]).setBackground(Color.GREEN);
		buttons.get(pos[1]).setBackground(Color.GREEN);
		buttons.get(pos[2]).setBackground(Color.GREEN);

		for (JButton iterator : buttons) {
			iterator.setEnabled(false);
		}

		text.setText(name + " wins");
	}

}
