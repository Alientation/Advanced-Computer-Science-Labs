import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
    this is the Controller component
 */

class Life extends JFrame implements ActionListener
{
   private static String[] list = {
      "blinker.lif",
      "glgun13.lif",
      "penta.lif",
      "tumbler.lif"
   };
	private static final long serialVersionUID = 1L;
	public static LifeView view;
	public static LifeModel model;
	private JButton runButton, pauseButton, resumeButton, randomColorButton, resetDropDownButton, clearButton;
   private Thread mousePositionThread;
   private JPopupMenu resetMenu;

	/** construct a randomized starting grid */
	Life() throws IOException
	{
		this(null);
	}
	
	/** construct a grid using the info in text file */
	Life(String fileName) throws IOException
	{
		super("Conway's Life");

		// build the buttons
		JPanel controlPanel = 
				new JPanel(new FlowLayout(FlowLayout.CENTER));
		runButton = new JButton("Run");
		runButton.addActionListener(this);
		runButton.setEnabled(true);
		controlPanel.add(runButton);
		pauseButton = new JButton("Pause");
		pauseButton.addActionListener(this);
		pauseButton.setEnabled(false);
		controlPanel.add(pauseButton);
		resumeButton = new JButton("Resume");
		resumeButton.addActionListener(this);
		resumeButton.setEnabled(false);
		controlPanel.add(resumeButton);
      
      resetMenu = new JPopupMenu();   //reset popup menu prob can be better programmed but im lazy
      resetMenu.add(new JMenuItem(new AbstractAction("Random") {
         public void actionPerformed(ActionEvent e) {
            model.constructGrid(null);
            runButton.setEnabled(true);
            resumeButton.setEnabled(false);
            pauseButton.setEnabled(false);
            clearButton.setEnabled(true);
            model.reset();
         }
      }));
      resetMenu.add(new JMenuItem(new AbstractAction("blinker.lif") {
         public void actionPerformed(ActionEvent e) {
            model.constructGrid("blinker.lif");
            runButton.setEnabled(true);
            resumeButton.setEnabled(false);
            pauseButton.setEnabled(false);
            clearButton.setEnabled(true);
            model.reset();
         }
      }));
      resetMenu.add(new JMenuItem(new AbstractAction("glgun13.lif") {
         public void actionPerformed(ActionEvent e) {
            model.constructGrid("glgun13.lif");
            runButton.setEnabled(true);
            resumeButton.setEnabled(false);
            pauseButton.setEnabled(false);
            clearButton.setEnabled(true);
            model.reset();
         }
      }));
      resetMenu.add(new JMenuItem(new AbstractAction("penta.lif") {
         public void actionPerformed(ActionEvent e) {
            model.constructGrid("penta.lif");
            runButton.setEnabled(true);
            resumeButton.setEnabled(false);
            clearButton.setEnabled(true);
            pauseButton.setEnabled(false);
            model.reset();
         }
      }));
      resetMenu.add(new JMenuItem(new AbstractAction("tumbler.lif") {
         public void actionPerformed(ActionEvent e) {
            model.constructGrid("tumbler.lif");
            runButton.setEnabled(true);
            resumeButton.setEnabled(false);
            pauseButton.setEnabled(false);
            clearButton.setEnabled(true);
            model.reset();
         }
      }));
      resetDropDownButton = new JButton("Reset");
      resetDropDownButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                resetMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
		controlPanel.add(resetDropDownButton);
      
      clearButton = new JButton("Clear");
      clearButton.addActionListener(this);
      clearButton.setEnabled(true);
      controlPanel.add(clearButton);
      
      randomColorButton = new JButton("Randomize Color");
		randomColorButton.addActionListener(this);
		randomColorButton.setEnabled(true);
		controlPanel.add(randomColorButton);


		// build the view
		view = new LifeView();
		view.setBackground(Color.white);

		// put buttons, view together
		Container c = getContentPane();
		c.add(controlPanel, BorderLayout.NORTH);
		c.add(view, BorderLayout.CENTER);

		// build the model
		model = new LifeModel(view, fileName);
	}

	public void actionPerformed(ActionEvent e)
	{
		JButton b = (JButton)e.getSource();
		if ( b == runButton ) {
			model.run();
			runButton.setEnabled(false);
			pauseButton.setEnabled(true);
			resumeButton.setEnabled(false);
         clearButton.setEnabled(false);
		} else if ( b == pauseButton ) {
			model.pause();
			runButton.setEnabled(false);
			pauseButton.setEnabled(false);
			resumeButton.setEnabled(true);
         clearButton.setEnabled(true);
		} else if ( b == resumeButton ) {
			model.resume();
			runButton.setEnabled(false);
			pauseButton.setEnabled(true);
			resumeButton.setEnabled(false);
         clearButton.setEnabled(false);
		} else if ( b == randomColorButton ) {
			model.color();
         randomColorButton.setEnabled(true);
		} else if ( b == clearButton) {
         model.clear();
      }

	}
	
	public static void main(String[] args) throws IOException
	{
		Life conway = new Life(list[1]); //parameterize to start w/ a particular file
		
		conway.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		conway.setSize(570, 640);
		//conway.show(); //deprecated, added call below
		conway.setVisible(true);
	}
}