import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GamePanel extends JFrame implements ActionListener, KeyListener, ItemListener, MenuListener, MouseInputListener {

    private ArrayList<Block> blockList;
    private ArrayList<Block> tempList;
    private static boolean play = true;
    private static Block[] map;
    private final String difficulty[] = {"easy", "medium", "hard"};

    private final Map<String, Integer> hard = new HashMap<String, Integer>() {{
        put("easy", 5);
        put("medium", 7);
        put("hard", 9);
    }};
    private final int gameSize[] = {5, 7, 9};
    private static int gridNum;
    private int width, height;

    private JMenuBar bar;
    JMenuItem jitemNew, jitemExit;


    public GamePanel() {
        init();

    }

    ;

    public void countInsets() {
        Insets insets = this.getInsets();
        System.out.printf("top=%d\n", insets.top);
        System.out.printf("left=%d\n", insets.left);
        System.out.printf("right=%d\n", insets.right);
        System.out.printf("bottom=%d\n", insets.bottom);
    }

    public void init() {

        blockList = new ArrayList<>();
        tempList = new ArrayList<>();
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);//
        addMenu();


        this.setSize(800, 800 + this.getJMenuBar().getHeight() + 26);
        this.setLocationRelativeTo(null);
        //設定各種listener

        this.setTitle("踩地雷實驗");
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        countInsets();


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

    /**
     * 暫時先只顯示特定格子周遭
     */
    private int showMine(Block b) throws Exception {//,ArrayList tempList


        ArrayList<Block> temp = new ArrayList<>();
        int count = 0;
        int x = b.getPosX();

        int y = b.getPosY();
        int index = 999;
        System.out.printf("X=%d,Y=%d", x, y);
        System.out.println(b.getBounds());
        for (int i = 0; i <= blockList.size(); i++) {

            if (blockList.get(i) == b) {
//                showMine(blockList.get(i));
                index = i;
                break;

            }
        }
        if (index == 999) {
            throw new Exception("沒有符合的索引");
        }

        /**
         * 判斷目前格子,是否可以往左/往右一格 及能否往上/往下一格,
         * 如果能夠往上,起始值=index-寬,如果還能往左,起始值再-1
         *如果能往下,結尾值+寬,如果還能往右,結尾值+1
         * 雙層迴圈從起始值開始,
         */
//如果x-1>0,表示左邊有一欄,如果x+1<寬,表示右邊有一欄
        //如果
        int yStart = -1, yEnd = 1;
        int xStart = -1, xEnd = 1;

        if (index / width == 0) {
            //如果商數為0,表示目前格子在第一行
            yStart = 0;

        } else if (index / width == height - 1) {
            yEnd = 0;
            //如果商數等於高度-1,表示在最後一行
        }

        if (index % width == 0) {
            //表示在第一列
            xStart = 0;
        } else if (index % width == height - 1) {
            //表示在最後一列
            xEnd = 0;
        }

        System.out.printf("xStart=%d\n", xStart);
        System.out.printf("xEnd=%d\n", xEnd);
        System.out.printf("yStart=%d\n", yStart);
        System.out.printf("yEnd=%d\n", yEnd);

        //起始格子的索引
        //int start=index+xStart+yStart*width;


        for (int i = xStart; i <= xEnd; i++) {
            for (int j = yStart; j <= yEnd; j++) {
                int current = index + i + width * j;
                if(current==index){
                    continue;
                }
                System.out.printf("pos:%d,i=%d,j=%d\n", current, i, j * width);
                temp.add(blockList.get(current));
                if (blockList.get(current).isBomb()) {

                    count++;
                } else {
                  /*  if(showMine(blockList.get(index))==0){
                        blockList.get(index).open();
                    }*/
                }
            }
        }

        if (count == 0) {
            for(Block block:temp){
                if(!tempList.contains(block)){
                    openButton(block);
                }
            }



        }

        b.setText(String.valueOf(count));
        System.out.println("bomb num:" + count);
        return count;

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == jitemNew) {
            play=true;

            Object selectedValue = JOptionPane.showInputDialog(null, "請選遊戲難度", "難度設定",
                    JOptionPane.INFORMATION_MESSAGE, null, difficulty, difficulty[0]);
            if (selectedValue != null) {
                for (Block mine : blockList) {
                    this.remove(mine);
                }
                blockList.clear();
                int gridNum = hard.get(selectedValue);
                this.width = gridNum;
                this.height = gridNum;
                this.setLayout(new GridLayout(gridNum, gridNum));
                System.out.println(gridNum);
                for (int i = 0; i < width * height; i++) {

                    Block b = new Block(i);
                    b.addMouseListener(this);
                    blockList.add(b);

                    this.add(b);

                }
//                Random rnd=new Random();
                switch (gridNum) {

                    case 5:
                        Collections.shuffle(blockList);
                        for (int i = 0; i < 5; i++) {
                            blockList.get(i).setBomb();

                        }
                        Collections.sort(blockList);
                        break;
                    case 7:
                        Collections.shuffle(blockList);
                        for (int i = 0; i < 7; i++) {
                            blockList.get(i).setBomb();
                        }
                        Collections.sort(blockList);
                    case 9:
                        Collections.shuffle(blockList);
                        for (int i = 0; i < 9; i++) {
                            blockList.get(i).setBomb();
                        }
                        Collections.sort(blockList);
                    default:
                }
            /*    for(int i=0;i<blockList.size();i++){
                    blockList.get(i).drawText(String.valueOf(i));
                }*/
                this.pack();

                this.setSize(800, 800 + this.getJMenuBar().getHeight() + 26);

                this.setLocationRelativeTo(null);
                System.out.println("Height" + this.getJMenuBar().getHeight());
                countInsets();
            }

        }
    }


    private void openButton(Block b) {
        b.open();
        if (b.isBomb()) {

            play=false;


        } else {
            tempList.add(b);
            try {
                showMine(b);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        if(play) {

            if (e.getButton() == MouseEvent.BUTTON1) {

                System.out.println("press left");
                if (e.getSource() instanceof Block) {

                    Block b = (Block) e.getSource();
                    if (!b.isOpen()) {
                        tempList.clear();
                        openButton(b);
                    }


                }


            } else if (e.getButton() == MouseEvent.BUTTON2) {


            } else if (e.getButton() == MouseEvent.BUTTON3) {
                System.out.println("press right");
                if (e.getSource() instanceof Block) {
                    Block mine = (Block) e.getSource();
                    if (!mine.getOpen())
                        mine.mark();
                }

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
