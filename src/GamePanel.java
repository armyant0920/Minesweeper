import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GamePanel extends JFrame implements ActionListener, KeyListener, ItemListener, MenuListener {

    private ArrayList<JButton> blockList;
    private final String difficulty[] = {"easy", "medium", "hard"};
    private final Map<String,Integer>hard=new HashMap<String,Integer>(){{put("easy",5);put("medium",7);put("hard",9);}};
    private final int gameSize[] = {5, 7, 9};

    private JMenuBar bar;
    JMenuItem jitemNew, jitemExit;


    public GamePanel() {
        init();

    }

    ;

    public void init() {

        blockList=new ArrayList<>();
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);//
        addMenu();
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLocationRelativeTo(null);

        this.setVisible(true);


    }



    private void addMenu() {
        bar = new JMenuBar();
        this.setJMenuBar(bar);
        JMenu game = new JMenu("遊戲");
        jitemNew = new JMenuItem("新遊戲");
        jitemNew.setActionCommand("new");
        jitemNew.addActionListener(this);
        game.add(jitemNew);
        game.addSeparator();//選單裡設定分隔線
        jitemExit = new JMenuItem("退出");
        jitemExit.setActionCommand("exit");
        jitemExit.addActionListener(this);

        game.add(jitemExit);
        bar.add(game);
//        System.out.println("選單高度" + game.getComponent().getHeight());


    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == jitemNew) {

            Object selectedValue = JOptionPane.showInputDialog(null, "請選遊戲難度", "難度設定",
                    JOptionPane.INFORMATION_MESSAGE, null, difficulty, difficulty[0]);
            if (selectedValue != null) {
                for(JButton btn:blockList){
                    this.remove(btn);

                }
                blockList.clear();
                int gridNum =hard.get(selectedValue);

                this.setLayout(new GridLayout(gridNum, gridNum));
                System.out.println(gridNum);
                for (int i = 0; i < gridNum; i++) {
                    for (int j = 0; j < gridNum; j++) {

                        JButton btn = new JButton();

                        btn.setBackground(Color.GREEN);
                        blockList.add(btn);
                        this.add(btn);

                    }
                }
                this.pack();
                this.setSize(500,500);
                this.setLocationRelativeTo(null);
            }



//            this.setSize(DEFAULT_SIZE);

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (ItemEvent.SELECTED == e.getStateChange()) {

            String selectedItem = e.getItem().toString();
            System.out.printf("new selected item : %s%n", selectedItem);

        }
        if (ItemEvent.DESELECTED == e.getStateChange()) {
            String selectedItem = e.getItem().toString();
            System.out.printf("deselected item : %s%n", selectedItem);
        }
    }

    @Override
    public void menuSelected(MenuEvent e) {
        if (e.getSource() instanceof JMenu) {
            JMenu menu = (JMenu) e.getSource();
            System.out.println(menu.getName() + "已選取");
        }


    }

    @Override
    public void menuDeselected(MenuEvent e) {
        if (e.getSource() instanceof JMenu) {
            JMenu menu = (JMenu) e.getSource();
            System.out.println(menu.getName() + "已取消選取");
        }

    }

    @Override
    public void menuCanceled(MenuEvent e) {
        if (e.getSource() instanceof JMenu) {
            JMenu menu = (JMenu) e.getSource();
            System.out.println(menu.getName() + "已取消");
        }

    }
}
