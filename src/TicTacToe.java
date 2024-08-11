import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 700;//50 px for text panel at the top and 50 px for restart button

    JFrame frame = new JFrame("TIC-TAC-TOE");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JButton restartButton = new JButton();
    JLabel restartLabel = new JLabel();
    JPanel restartPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns = 0;

    TicTacToe(){
        //Window Creation
        frame.setVisible(true); //Opens the window
        frame.setSize(boardWidth,boardHeight); // Sets the size for the window
        frame.setLocationRelativeTo(null); //Makes the window centered
        frame.setResizable(false);//Stops the user from changing the window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Terminates program when window closes
        frame.setLayout(new BorderLayout());

        //Top Text Creation
        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        //Insert Text into Window
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);//Add the text to the text panel
        frame.add(textPanel,BorderLayout.NORTH); //Add the panel to the frame/window

        restartButton.setText("Restart?");
        restartButton.setBackground(Color.darkGray);
        restartButton.setForeground(Color.white);
        restartButton.setFont(new Font("Arial",Font.BOLD,50));
        restartButton.setFocusable(false);

        restartPanel.setLayout(new BorderLayout());
        restartPanel.add(restartButton);
        frame.add(restartPanel,BorderLayout.SOUTH);

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOver = false;
                turns = 0;
                resetBoard();
            }
        });


        boardPanel.setLayout(new GridLayout(3,3));//This creates a 3x3 grid
        boardPanel.setBackground(Color.lightGray);
        frame.add(boardPanel);


        for (int r = 0; r<3; r++){
            for(int c = 0; c<3; c++){
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial",Font.BOLD,120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if(tile.getText() == "") {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver) {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn.");
                            }
                        }

                    }
                });

            }
        }
    }

    void checkWinner() {
        //horizontal
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText() == "") continue;

            if (board[r][0].getText() == board[r][1].getText() &&
                    board[r][1].getText() == board[r][2].getText()) {
                gameOver = true;

                for (int i = 0; i < 3; i++) {
                    setWinner(board[r][i]);
                }
                return;
            }
        }

        //Vertical
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText() == "") continue;

            if (board[0][c].getText() == board[1][c].getText() &&
                board[1][c].getText() == board[2][c].getText()) {

                gameOver = true;

                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][c]);
                }
                return;
            }
        }

        //Diagonals

        //Top-Left to Bottom-Right
        if (board[0][0].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][2].getText() &&
            board[0][0].getText() != "" ) {
            gameOver = true;

            for (int j = 0; j < 3; j++) {
                setWinner(board[j][j]);
            }

            return;
        }


        //Top-Right to Bottom-Left
        if (board[0][2].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][0].getText() &&
            board[0][2].getText() != "" ){
            gameOver = true;

            for (int j = 0; j < 3; j++) {
                setWinner(board[j][2-j]);
            }
            return;
        }

        if(turns >= 9){
            gameOver = true;
            setTie();
        }
    }


    void setWinner(JButton tile){
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText(currentPlayer + " player Wins!");
        textLabel.setForeground(Color.green);
    }
    void setTie(){
        for(int r=0;r<3;r++){
            for(int c=0;c<3;c++){
                board[r][c].setForeground(Color.YELLOW);
                board[r][c].setBackground(Color.gray);
            }
        }
        textLabel.setText("It is a Tie!");
    }
    void resetBoard(){
        for(int r=0;r<3;r++){
            for(int c=0;c<3;c++){
                board[r][c].setBackground(Color.darkGray);
                board[r][c].setForeground(Color.white);
                board[r][c].setText("");
                textLabel.setText("Tic-Tac-Toe");
                textLabel.setForeground(Color.white);
            }
        }
    }
}
