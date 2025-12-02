import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectWindow extends JFrame implements ActionListener {

    public String result="";

    public enum bType {Radio, Dropdown}
    public bType buttonType;

    public SelectWindow(String selection, bType type, String [] options)
    {
        super(selection);
        buttonType=type;
        switch(buttonType)
        {
            case Radio : {
                JRadioButton [] bRadio = new JRadioButton[options.length];

                ButtonGroup group=new ButtonGroup();

                JPanel panel=new JPanel();
                panel.setLayout(new GridLayout(options.length,1));

                for (int i = 0; i < options.length; i++)
                {
                    bRadio[i]=new JRadioButton(options[i]);
                    bRadio[i].addActionListener(this);

                    group.add(bRadio[i]);
                    panel.add(bRadio[i]);
                }

                setContentPane(panel);

            } break;
            case Dropdown : {
                JComboBox bDropdown = new JComboBox(options);
            }

        }
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        switch(buttonType)
        {
            case Radio : {
                if(source.getClass() == JRadioButton.class)
                {
                    result=((JRadioButton) source).getText();
                }
            } break;

            case Dropdown : {
                System.out.println("N/A");
            }
        }
    }
}
