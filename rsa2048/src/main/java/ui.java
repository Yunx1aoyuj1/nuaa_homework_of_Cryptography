import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ui extends JFrame implements ActionListener {

    //定义组件
    JButton jb1,jb2,jb3=null;
    JRadioButton jrb1,jrb2,jrb3,jrb4,jrb5=null;
    JPanel jp1,jp2,jp3,jp4,jp5,jp6=null;
    JTextField jtf1,jtf2,jtf3=null;
    JLabel jlb1,jlb2,jlb3,jlb4,jlb5=null;
    ButtonGroup bg=null;
    //教程是这么写的，我照猫画虎一下
    //原教程是学生管理系统

    public static void main(String[] args) {

        ui tUI=new ui();
    }

    public ui() {
        //创建组件
        jb1=new JButton("开始");
        //设置监听
        jb1.addActionListener(this);
        //行逻辑组件
        jp1=new JPanel();
        jp1.add(jb1);
        this.add(jp1);
        this.setLayout(new GridLayout(1,1));            //选择GridLayout布局管理器
        this.setTitle("性能测试");
        this.setSize(600,250);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出
        this.setVisible(true);
        this.setResizable(true);
    }

    public void actionPerformed(ActionEvent e) {            //事件判断
        if (e.getActionCommand() == "开始") {
            testRSA rsa = new testRSA();
            long startTime = System.currentTimeMillis(); //程序结束记录时间
            try {
                rsa.test();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            long endTime = System.currentTimeMillis(); //程序结束记录时间
            long TotalTime = endTime - startTime; //总消耗时间
            JOptionPane.showMessageDialog(null, TotalTime + "毫秒", "测试结果", JOptionPane.WARNING_MESSAGE);

        }
    }

}