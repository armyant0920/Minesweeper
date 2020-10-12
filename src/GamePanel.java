import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GamePanel extends JFrame implements ActionListener, KeyListener, ItemListener, MenuListener, MouseInputListener {

    private ArrayList<Mine> blockList;
    private final String difficulty[] = {"easy", "medium", "hard"};

    private final Map<String, Integer> hard = new HashMap<String, Integer>() {{
        put("easy", 5);
        put("medium", 7);
        put("hard", 9);
    }};
    private final int gameSize[] = {5, 7, 9};
    private static int gridNum;
    private int width,height;

    private JMenuBar bar;
    JMenuItem jitemNew, jitemExit;


    public GamePanel() {
        init();

    }

    ;

    public void init() {

        blockList = new ArrayList<>();
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);//
        addMenu();
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        //設定各種listener

        this.setTitle("踩地雷實驗");
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);


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

    private void showMine(){

  /*      for(int i=0;i<gridNum;i++){
            for(int j=0;j<gridNum;j++){

                //先確認目前pick到的格子
                int xStart=()

            }


        }*/

        for(int i=0;i<blockList.size();i++){

            int xStart=(i-1>=0)?i-1:0;
            int xEnd=(i+1<width)?i+1:width-1;
            int yStart=(i-width>=0)?i-width:0;
            int yEnd=(i+width<height)?i+height:height;

        }

    for(Mine m:blockList){

        int xStart=(m.getX()-1>=0)?m.getX()-1:0;
        int xEnd=(m.getX()+1<gridNum)?m.getX()+1:gridNum-1;
        int yStart=(m.getY()-1>=0)?m.getY()-1:0;
        int yEnd=(m.getY()+1<gridNum)?m.getY()+1:gridNum-1;
        int count=0;
        for(int i=xStart;i<xEnd;i++){
            for(int j=yStart;j<yEnd;j++){

            }
        }

    }


    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == jitemNew) {

            Object selectedValue = JOptionPane.showInputDialog(null, "請選遊戲難度", "難度設定",
                    JOptionPane.INFORMATION_MESSAGE, null, difficulty, difficulty[0]);
            if (selectedValue != null) {
                for (Mine mine : blockList) {
                    this.remove(mine);
                }
                blockList.clear();
                int gridNum = hard.get(selectedValue);
                this.width=gridNum;
                this.height=gridNum;
                this.setLayout(new GridLayout(gridNum, gridNum));
                System.out.println(gridNum);
                for (int i = 0; i < gridNum; i++) {
                    for (int j = 0; j < gridNum; j++) {
                        Mine mine = new Mine(i,j);
                        mine.addMouseListener(this);
                        blockList.add(mine);
                        this.add(mine);
                    }
                }
//                Random rnd=new Random();
                switch (gridNum) {

                    case 5:
                        Collections.shuffle(blockList);
                        for (int i = 0; i < 5; i++) {
                            blockList.get(i).setBomb();
                        }
                        break;
                    case 7:
                        Collections.shuffle(blockList);
                        for (int i = 0; i < 7; i++) {
                            blockList.get(i).setBomb();
                        }
                    case 9:
                        Collections.shuffle(blockList);
                        for (int i = 0; i < 9; i++) {
                            blockList.get(i).setBomb();
                        }
                    default:
                }
                this.pack();
                this.setSize(500, 500);
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

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON1) {

            System.out.println("press left");
            if (e.getSource() instanceof Mine) {

                Mine mine = (Mine) e.getSource();
                if (!mine.getOpen() && !mine.getFlag()) {
                    mine.Open(true);
                }
            }


        } else if (e.getButton() == MouseEvent.BUTTON2) {



        } else if (e.getButton() == MouseEvent.BUTTON3) {
            System.out.println("press right");
            if (e.getSource() instanceof Mine) {
                Mine mine = (Mine) e.getSource();
                if (!mine.getOpen())
                    mine.mark();
            }

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
