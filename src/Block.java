import javax.swing.*;
import java.awt.*;

public class Block extends JButton implements Comparable {

    private int posX;
    private int posY;
    private int index;


    public boolean isBomb() {
        return bomb;
    }

    private boolean bomb = false;
    private boolean flag = false;
    private boolean open = false;

    public boolean isOpen() {
        return open;
    }

    private String text;

    @Override
    public void setText(String text) {
        super.setText(text);
        this.text = text;
        this.setFont(new Font("", Font.BOLD, 20));
        repaint();
    }

    private Color color = Color.GREEN;


    public int getPosX() {
        return posX;
    }


    public int getPosY() {
        return posY;
    }

    public Block(int i) {
        this.setBackground(color);
        this.index=i;
//        this.posX =x;
//        this.posY =y;
//        this.text=x+"/"+y;


    }

    public void open() {
        this.open = true;
        flag = false;
        if (!bomb) {
            setBackground(Color.white);
        } else {
            JOptionPane.showMessageDialog(null,"game over","dead",JOptionPane.INFORMATION_MESSAGE);
            repaint();



        }
    }

    public void setBomb() {
        this.bomb = true;
        this.color = Color.YELLOW;
        setBackground(color);
    }

    public boolean getOpen() {
        return this.open;
    }

    public boolean getFlag(){
        return this.flag;
    }




    @Override
    public void paint(Graphics g) {
        super.paint(g);

//        Dimension dimension=this.getSize();
        if(flag){

//            g.drawOval(point.x,point.y,dimension.width,dimension.height);
            g.fillOval(0,0,this.getWidth(),this.getHeight());

        }else{
//            this.setBackground(Color.GREEN);
//            g.clearRect(point.x,point.y,dimension.width,dimension.height);
        }
        if(open){
            if(isBomb()){
                g.setColor(Color.red);
                g.fillOval(0,0,this.getWidth(),this.getHeight());

            }else{
                if(text!=null && !text.equals("")){
                    this.setForeground(Color.black);
                    this.setFont(new Font("標楷體", Font.BOLD, 20));
                    this.setText(text);

                }

            }

        }


    }

    public void mark() {
        flag = !flag;
        repaint();


    }

    public void drawText(String text){

        this.text=text;

        this.repaint();



    }


    @Override
    public int compareTo(Object o) {

        if(o instanceof Block){
         Block b2=(Block)o;
         return this.index-b2.index;
        }
        return 0;
    }
}
