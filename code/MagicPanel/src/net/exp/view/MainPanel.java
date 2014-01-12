/**
 * 
 */
package net.exp.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

/**
 * @author worm
 *
 */
public class MainPanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4400040427033950786L;

	public MainPanel ()
	{
		super("Chart Show");
		this.setLayout(new BorderLayout());
		JToolBar tool = new JToolBar();
		this.add(tool, BorderLayout.NORTH);
		tool.setFloatable(false);
		tool.add(new JButton("Open"));
		this.add(new PaintPanel(), BorderLayout.CENTER);
		setSize(600, 800);
		this.setEnabled(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
