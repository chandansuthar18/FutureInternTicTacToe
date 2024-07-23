package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private char[][] board = new char[3][3];
    private char currentPlayer = 'X';
    private JLabel statusLabel;

    public TicTacToeGame() {
        setTitle("Tic Tac Toe");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(new Color(50, 50, 50));

        Font buttonFont = new Font("Arial", Font.BOLD, 60);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(buttonFont);
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                boardPanel.add(buttons[i][j]);
                board[i][j] = ' ';
            }
        }

        statusLabel = new JLabel("Player X's turn");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 24));
        statusLabel.setForeground(Color.WHITE);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.setBackground(new Color(50, 50, 50));

        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> resetGame());
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));

        controlPanel.add(restartButton);
        controlPanel.add(exitButton);

        JLabel createdByLabel = new JLabel("Created by Chandan Kumar");
        createdByLabel.setHorizontalAlignment(SwingConstants.CENTER);
        createdByLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        createdByLabel.setForeground(Color.BLACK);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(controlPanel, BorderLayout.CENTER);
        bottomPanel.add(createdByLabel, BorderLayout.SOUTH);

        add(statusLabel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttonClicked == buttons[i][j] && board[i][j] == ' ') {
                    board[i][j] = currentPlayer;
                    buttonClicked.setText(String.valueOf(currentPlayer));
                    buttonClicked.setEnabled(false);
                    if (checkWin()) {
                        statusLabel.setText("Player " + currentPlayer + " wins!");
                        disableButtons();
                    } else if (checkDraw()) {
                        statusLabel.setText("It's a draw!");
                    } else {
                        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                        statusLabel.setText("Player " + currentPlayer + "'s turn");
                    }
                }
            }
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer)
                return true;
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)
                return true;
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer)
            return true;
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)
            return true;
        return false;
    }

    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }

    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void resetGame() {
        currentPlayer = 'X';
        statusLabel.setText("Player X's turn");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToeGame game = new TicTacToeGame();
            game.getContentPane().setBackground(new Color(30, 30, 30));
            game.setVisible(true);
        });
    }
}
