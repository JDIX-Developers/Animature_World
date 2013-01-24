package interfaz.ventanas;

import interfaz.menús.Inicio;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


/**
 * @author Jordan Aranda Tejada
 *
 */
public class VentanaPrincipal extends JFrame
{
	private static final long	serialVersionUID	= -5512652425053684151L;
	private final JPanel contentPane;

	/**
	 * Creación de la ventana
	 */
	public VentanaPrincipal()
	{
		setTitle("PoketMon");
		setAlwaysOnTop(true);
		setResizable(false);
		setUndecorated(true);
		setBounds(getGraphicsConfiguration().getBounds());
		getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);
		getContentPane().add(new Inicio(), BorderLayout.CENTER);

		setVisible(true);
	}
	
	/**
	 * @param args Argumentos
	 */
	public static void main(String[] args)
	{
		VentanaPrincipal v = new VentanaPrincipal();
		
		try
		{
			Thread.sleep(5000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		v.dispose();
	}
}
