import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

class ScoreTracker {
    JFrame game;
    JComboBox<Player> list;
    Player[] players;
    JPanel buttonsPanel;
    JPanel playerPanel;
    JLabel playerScore;
    JLabel playerPhase;
    JButton up;
    final Font f;
    final GridLayout grid;

    ScoreTracker(final ArrayList<String> names) {
        f = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
        grid = new GridLayout();

        prepareGUI();
        setList(names);
        setPlayerPanel();
        setButtonsPanel();

        game.add(list, BorderLayout.NORTH);
        game.add(buttonsPanel, BorderLayout.CENTER);
        game.add(playerPanel, BorderLayout.SOUTH);
        game.setVisible(true);
    }

    private void prepareGUI() {
        game = new JFrame("Five Crowns");
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setExtendedState(JFrame.MAXIMIZED_BOTH);
        game.setLayout(new BorderLayout());
    }

    private void setList(ArrayList<String> names) {
        players = new Player[names.size()];
        for (int i = 0; i < names.size(); i++) {
            players[i] = new Player(names.get(i));
        }
        list = new JComboBox<>(players);
        list.setFont(f);
        list.addActionListener((event) -> {
            playerScore.setText("Score: " + players[list.getSelectedIndex()].getScore());
            playerPhase.setText("Phase: " + players[list.getSelectedIndex()].getPhase());
        });

    }

    private void setButtonsPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(grid);
        JButton five = new JButton("+ 5");
        setButtonProperties(five, 5);
        JButton ten = new JButton("+ 10");
        setButtonProperties(ten, 10);
        JButton wild = new JButton("Wild");
        setButtonProperties(wild, 25);
        JButton skip = new JButton("Skip");
        setButtonProperties(skip, 15);
    }

    private void setButtonProperties(final JButton b, int score) {
        b.setFont(f);
        b.addActionListener((ActionEvent e) -> {
            players[list.getSelectedIndex()].addScore(score);
            playerScore.setText("Score: " + players[list.getSelectedIndex()].getScore());
        });
        buttonsPanel.add(b);
    }

    private void setPlayerPanel() {
        playerPanel = new JPanel();
        playerPanel.setLayout(grid);

        playerScore = new JLabel("Score: " + players[list.getSelectedIndex()].getScore());
        playerScore.setFont(f);

        playerPhase = new JLabel("Phase: " + players[list.getSelectedIndex()].getPhase());
        playerPhase.setFont(f);

        JButton up = new JButton("+");
        up.addActionListener((event) -> {
            players[list.getSelectedIndex()].addPhase();
            playerPhase.setText("Phase: " + players[list.getSelectedIndex()].getPhase());
            if (players[list.getSelectedIndex()].getPhase() == 10) {
                up.setVisible(false);
            }
        });

        playerPanel.add(playerScore);
        playerPanel.add(playerPhase);
        playerPanel.add(up);
    }

    public static void main(String[] args) {
        OpenScreen open = new OpenScreen();
        System.out.println(open);
    }
}
