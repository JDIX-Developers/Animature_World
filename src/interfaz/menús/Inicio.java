package interfaz.menús;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Jordan Aranda Tejada
 *
 */
public class Inicio extends JPanel implements ActionListener {

	private static final long	serialVersionUID	= 3465880948872999761L;

	private JButton	btnNuevaPartida, btnCargarPartida, btnOpciones, btnCreditos, btnSalir;

	/**
	 * Creación del panel
	 */
	public Inicio()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {20, 0, 10, 0, 10, 0, 10, 0, 10,0, 20, 0};
		gridBagLayout.rowHeights = new int[] {20, 0, 20, 20, 0};
		gridBagLayout.columnWeights = new double[] {10.0, 100.0, 10.0, 100.0,
		10.0, 100.0, 10.0, 100.0, 10.0, 100.0, 10.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[] {0.0, 100.0, 1.0, 0.0,Double.MIN_VALUE};
		setLayout(gridBagLayout);

		anyadirBotonesMenu();
	}

	private void anyadirBotonesMenu()
	{
		this.btnNuevaPartida = new JButton("NUEVA PARTIDA");
		this.btnNuevaPartida.setFocusable(false);
		this.btnNuevaPartida.setFocusTraversalKeysEnabled(false);
		this.btnNuevaPartida.setFocusPainted(false);
		this.btnNuevaPartida.setForeground(Color.BLACK);
		this.btnNuevaPartida.setFont(new Font("Serif", Font.PLAIN, 16));
		this.btnNuevaPartida.addActionListener(this);
		GridBagConstraints gbc_btnNuevaPartida = new GridBagConstraints();
		gbc_btnNuevaPartida.fill = GridBagConstraints.BOTH;
		gbc_btnNuevaPartida.insets = new Insets(0, 0, 5, 5);
		gbc_btnNuevaPartida.gridx = 1;
		gbc_btnNuevaPartida.gridy = 2;
		add(this.btnNuevaPartida, gbc_btnNuevaPartida);

		this.btnCargarPartida = new JButton("CARGAR PARTIDA");
		this.btnCargarPartida.setFocusable(false);
		this.btnCargarPartida.setFocusTraversalKeysEnabled(false);
		this.btnCargarPartida.setFocusPainted(false);
		this.btnCargarPartida.setForeground(Color.BLACK);
		this.btnCargarPartida.setFont(new Font("Serif", Font.PLAIN, 16));
		this.btnCargarPartida.addActionListener(this);
		GridBagConstraints gbc_btnCargarPartida = new GridBagConstraints();
		gbc_btnCargarPartida.fill = GridBagConstraints.BOTH;
		gbc_btnCargarPartida.insets = new Insets(0, 0, 5, 5);
		gbc_btnCargarPartida.gridx = 3;
		gbc_btnCargarPartida.gridy = 2;
		add(this.btnCargarPartida, gbc_btnCargarPartida);

		this.btnOpciones = new JButton("OPCIONES");
		this.btnOpciones.setFocusable(false);
		this.btnOpciones.setFocusTraversalKeysEnabled(false);
		this.btnOpciones.setFocusPainted(false);
		this.btnOpciones.setForeground(Color.BLACK);
		this.btnOpciones.setFont(new Font("Serif", Font.PLAIN, 16));
		this.btnOpciones.addActionListener(this);
		GridBagConstraints gbc_btnOpciones = new GridBagConstraints();
		gbc_btnOpciones.fill = GridBagConstraints.BOTH;
		gbc_btnOpciones.insets = new Insets(0, 0, 5, 5);
		gbc_btnOpciones.gridx = 5;
		gbc_btnOpciones.gridy = 2;
		add(this.btnOpciones, gbc_btnOpciones);

		this.btnCreditos = new JButton("CR\u00C9DITOS");
		this.btnCreditos.setFocusable(false);
		this.btnCreditos.setFocusTraversalKeysEnabled(false);
		this.btnCreditos.setFocusPainted(false);
		this.btnCreditos.setForeground(Color.BLACK);
		this.btnCreditos.setFont(new Font("Serif", Font.PLAIN, 16));
		this.btnCreditos.addActionListener(this);
		GridBagConstraints gbc_btnCreditos = new GridBagConstraints();
		gbc_btnCreditos.fill = GridBagConstraints.BOTH;
		gbc_btnCreditos.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreditos.gridx = 7;
		gbc_btnCreditos.gridy = 2;
		add(this.btnCreditos, gbc_btnCreditos);

		this.btnSalir = new JButton("SALIR");
		this.btnSalir.setFocusable(false);
		this.btnSalir.setFocusTraversalKeysEnabled(false);
		this.btnSalir.setFocusPainted(false);
		this.btnSalir.setForeground(Color.BLACK);
		this.btnSalir.setFont(new Font("Serif", Font.PLAIN, 16));
		this.btnSalir.addActionListener(this);
		GridBagConstraints gbc_btnSalir = new GridBagConstraints();
		gbc_btnSalir.fill = GridBagConstraints.BOTH;
		gbc_btnSalir.insets = new Insets(0, 0, 5, 5);
		gbc_btnSalir.gridx = 9;
		gbc_btnSalir.gridy = 2;
		add(this.btnSalir, gbc_btnSalir);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object escuchador = e.getSource();
		if (escuchador == this.btnNuevaPartida)
		{
			// NUEVA PARTIDA
		}
		else if (escuchador == this.btnCargarPartida)
		{
			// CARGAR PARTIDA
		}
		else if (escuchador == this.btnOpciones)
		{
			// OPCIONES
		}
		else if (escuchador == this.btnCreditos)
		{
			// CREDITOS
		}
		else if (escuchador == this.btnSalir)
		{
			// SALIR
		}
	}
}