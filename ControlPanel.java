package Orbital;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

//http://stackoverflow.com/questions/15538127/how-do-i-receive-input-from-a-textbox-in-a-jframe

/*
 * Annabel Strauss, Emily Cohen, Karina Shah
 * April/May 2015
 * Comp Sci X
 * Oribtal -- Control Panel
 * Version 2.9
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

//http://stackoverflow.com/questions/15538127/how-do-i-receive-input-from-a-textbox-in-a-jframe

/*
 * Annabel Strauss, Emily Cohen, Karina Shah
 * April/May 2015
 * Comp Sci X
 * Oribtal -- Control Panel
 * Version 2.9
 */

public class ControlPanel implements ItemListener
{
  JPanel cards; //a panel that uses CardLayout
  public final static String Earth = "Earth";
  public final static String Sun = "Sun";
  public final static String Moon = "Moon";
  public final static JTextField masssun = new JTextField(10);
  public final static JTextField xpossun = new JTextField(10);
  public final static JTextField ypossun = new JTextField(10);
  public final static JTextField massearth = new JTextField(10);
  public final static JTextField xposearth = new JTextField(10);
  public final static JTextField yposearth = new JTextField(10);
  public final static JTextField tanveloearth = new JTextField(10);
  public final static JTextField massmoon = new JTextField(10);
  public final static JTextField xposmoon = new JTextField(10);
  public final static JTextField yposmoon = new JTextField(10);
  public final static JTextField tanvelomoon = new JTextField(10);
  public final static JColorChooser color1 = new JColorChooser();
  public final static JColorChooser color2 = new JColorChooser();
  public final static JColorChooser color3 = new JColorChooser();
  JFrame frame;
  Container pane;
    
  


  public void addComponentToPane(Container pane) {
  JPanel comboBoxPane = new JPanel();
  
  String comboBoxItems[] = { Earth, Moon, Sun };
  JComboBox cb = new JComboBox(comboBoxItems);
  cb.setEditable(false);
  cb.addItemListener(this);
  comboBoxPane.add(cb);
  JButton runorbitalsim1 = new JButton("Run");
  JButton runorbitalsim2 = new JButton("Run");
  JButton runorbitalsim3 = new JButton("Run");

  JPanel card1 = new JPanel();
  card1.add(new JLabel("Mass Earth:"));
  card1.add(massearth);
  card1.add(new JLabel("X Position Earth:"));
  card1.add(xposearth);
  card1.add(new JLabel("Y Position Earth:"));
  card1.add(yposearth);
  card1.add(new JLabel("Tangential Velocity Earth:"));
  card1.add(tanveloearth);
  card1.add(runorbitalsim1);
  card1.add(color1);

  JPanel card2 = new JPanel();
  card2.add(new JLabel("Mass Sun:"));
  card2.add(masssun);
  card2.add(new JLabel("X Position Sun:"));
  card2.add(xpossun);
  card2.add(new JLabel("Y Position Sun:"));
  card2.add(ypossun);
  card2.add(runorbitalsim2);
  card2.add(color2);

  JPanel card3 = new JPanel();
  card3.add(new JLabel("Mass Moon:"));
  card3.add(massmoon);
  card3.add(new JLabel("X Position Moon:"));
  card3.add(xposmoon);
  card3.add(new JLabel("Y Position Moon:"));
  card3.add(yposmoon);
  card3.add(new JLabel("Tangential Velocity Moon:"));
  card3.add(tanvelomoon);
  card3.add(runorbitalsim3);
  card3.add(color3);

  runorbitalsim1.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e){
      //RUN ORBITALSIM
    }
  });
    
  runorbitalsim2.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e){
      //RUN ORBITALSIM
    }
  });
    
  runorbitalsim3.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e){
      //RUN ORBITALSIM
    }
  });

  cards = new JPanel(new CardLayout());
  cards.add(card1, Earth);
  cards.add(card2, Sun);
  cards.add(card3, Moon);

  pane.add(comboBoxPane, BorderLayout.PAGE_START);
  pane.add(cards, BorderLayout.CENTER);
  }

  public void itemStateChanged(ItemEvent evt) {
  CardLayout cl = (CardLayout)(cards.getLayout());
  cl.show(cards, (String)evt.getItem());
  }


  public ControlPanel()
  {
  //Create and set up the window.
  frame = new JFrame("ControlPanel");
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  masssun.setText("1.989E30");
  xpossun.setText("0");
  ypossun.setText("0");
  massearth.setText("5.972E24");
  xposearth.setText("1.496E11");
  yposearth.setText("0");
  tanveloearth.setText("2.9747E4");
  massmoon.setText("7.34E22");
  xposmoon.setText("3.844E8");
  yposmoon.setText("0");
  tanvelomoon.setText("4.21E4");
  color1.setColor(Color.blue);
  color2.setColor(Color.yellow);
  color3.setColor(Color.gray);

  addComponentToPane(frame.getContentPane());
    
  frame.pack();
  frame.setVisible(true);
  
  /**
  JFrame frame = new JFrame ("Contol Panel");

  JPanel panel = new JPanel();
  panel.setLayout(new BoxLayout);

  JLabel Earthlabel1 = new JLabel("Mass Earth:");
  panel.add(Earthlabel1);
  JTextField earthmass = new JTextField(10);
  panel.add(earthmass);

  JLabel Earthlabel2 = new JLabel("X Position Earth");
  panel.add(Earthlabel2);
  JTextField earthxposition = new JTextField(10);
  panel.add(earthxposition);

  JLabel Earthlabel3 = new JLabel("Y Position Earth");
  panel.add(Earthlabel3);
  JTextField earthyposition = new JTextField(10);
  panel.add(earthyposition);

  JLabel Earthlabel4 = new JLabel("Tangential Velocity Earth");
  panel.add(Earthlabel4);
  JTextField earthtanvelo = new JTextField(10);
  panel.add(earthtanvelo);

  frame.add(panel);
  frame.setSize(500, 500);
  frame.setVisible(true);
  **/
  }
}







