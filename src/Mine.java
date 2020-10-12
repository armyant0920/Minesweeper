import com.sun.org.apache.bcel.internal.classfile.Constant;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Mine extends JButton {

    private int x;
    private int y;

    private boolean bomb = false;
    private boolean flag = false;
    private boolean open = false;
    private String text;
    private Color color = Color.GREEN;

    public Mine(int x,int y) {
        this.setBackground(color);
        this.x=x;
        this.y=y;
        this.text=x+"/"+y;


    }

    public void Open(boolean open) {
        this.open = open;
        flag = false;
        if (!bomb) {
            setBackground(Color.white);
        } else {
            JOptionPane.showMessageDialog(null,"game over","dead",JOptionPane.INFORMATION_MESSAGE);


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
        g.drawString(text,0,0);

    }

    public void mark() {
        flag = !flag;
        repaint();


    }

    public void drawText(String text){

        this.text=text;
        this.repaint();



    }



}
