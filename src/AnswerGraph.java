import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class AnswerGraph {

	
	class TSPGraph extends JComponent {
		Answer answer;
		Graphics g;
		double zoom = 4;
		TSPGraph(Answer answer)
		{
			this.answer = answer;
		}
		
		public void paint(Graphics g) {
			this.g = g;
			for (Location loc:answer.question.list)
				drawALocation(loc);		
			
			State state = answer.endState;
			while (state.parent!=null)
			{
				drawALine(state.location, state.parent.location);
				state = state.parent;
			}
			
		}
		
		private void drawALocation(Location loc)
		{
			int x;
			int y;
			String name;
			x = (int) (loc.x*zoom);
			y = (int) (loc.y*zoom);
			name = loc.name;
			g.drawString(name, x+5, y+5);
			g.drawOval(x, y, 5, 5);
		}

		private void drawALine(Location loc1, Location loc2)
		{
			int x1 = (int) (loc1.x*zoom);
			int x2 = (int) (loc2.x*zoom);
			int y1 = (int) (loc1.y*zoom);
			int y2 = (int) (loc2.y*zoom);
			g.drawLine(x1, y1, x2, y2);
			g.drawString(Integer.toString((int)Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1))), (x2+x1)/2, (y2+y1)/2);
		}
	}

	JFrame window;


	AnswerGraph(Answer answer)
	{
		window = new JFrame();
		window.setBounds(30, 30, 600, 600);
		window.getContentPane().add(new TSPGraph(answer));
		window.setVisible(true);
	}

}
