import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RobotControlGrid {

	static RobotControlGrid rcg;
	JFrame frame;

	public static void main(String[] args) {
		rcg = new RobotControlGrid();
		rcg.go();
	}

	void go() {
		frame = new JFrame("Robot Navigator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JButton connect = new JButton("Connect");
		JButton drive = new JButton("Drive");

		JTextField robot = new JTextField(20);
		robot.setMaximumSize(robot.getPreferredSize());
		JLabel name = new JLabel("Robot Name:");

		JTextField column = new JTextField(20);
		column.setMaximumSize(robot.getPreferredSize());
		JLabel c = new JLabel("Column:");

		JTextField row = new JTextField(20);
		row.setMaximumSize(robot.getPreferredSize());
		JLabel r = new JLabel("Row:");

		panel.add(name);
		panel.add(robot);
		panel.add(connect);
		panel.add(c);
		panel.add(column);
		panel.add(r);
		panel.add(row);
		panel.add(drive);

		frame.getContentPane().add(BorderLayout.EAST, panel);

		DrawGrid grid = new DrawGrid();
		frame.getContentPane().add(grid);

		grid.repaint();

		frame.setSize(1000, 750);
		frame.setVisible(true);

	}

	class DrawGrid extends JPanel {	
	
		public void paintComponent(Graphics g) {
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			int columns = 8;
			int rows = 6;

			int margin = 50;
			
			int rowSize = (this.getHeight() - 2 * margin) / rows;
			int columnSize = (this.getWidth() - 2 * margin) / columns;
			
			int gridSize = rowSize;
			
			if (columnSize < rowSize) {
				gridSize = columnSize;
			}
			
			
			
			g.setColor(Color.CYAN);

			for (int i = 0; i <= rows; i++) {
				g.drawLine(margin, gridSize *i + margin, gridSize * columns + margin, gridSize *i + margin);
			}

			for (int j = 0; j <= columns; j++) {
				g.drawLine(gridSize *j + margin, margin, gridSize *j + margin, gridSize * rows + margin);
			}
		}
	}

	class ConnectListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

		}
	}

	class DriveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

		}

	}

}
