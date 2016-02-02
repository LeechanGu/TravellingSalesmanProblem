import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class TSPSolutionDrawer {

	class TSPGraphComponent extends JComponent {
		TSPSolution answer;
		Graphics g;
		int zoom = 4;
		TSPGraphComponent(TSPSolution answer)
		{
			this.answer = answer;
		}
		
		public void paint(Graphics g) {
			this.g = g;
			for (TSPCity loc:answer.question.list)
				drawALocation(loc);		
			
			if (answer.cities.size()<2) return;
			TSPCity prevCity = null;//answer.cities.get(0);
			TSPCity nextCity = answer.cities.get(0);
			for (int i=1;i<answer.cities.size();i++)
			{
				prevCity = nextCity;
				nextCity = answer.cities.get(i);
				drawALine(prevCity, nextCity);
			}
			
		}
		
		private void drawALocation(TSPCity loc)
		{
			int x;
			int y;
			String name;
			x = (int) (loc.x);
			y = (int) (loc.y);
			name = loc.name;
			g.drawString(name, x*zoom+5, y*zoom+5);
			g.drawOval(x*zoom, y*zoom, 5, 5);
		}

		private void drawALine(TSPCity loc1, TSPCity loc2)
		{
			int x1 = (int) (loc1.x);
			int x2 = (int) (loc2.x);
			int y1 = (int) (loc1.y);
			int y2 = (int) (loc2.y);
			g.drawLine(x1*zoom, y1*zoom, x2*zoom, y2*zoom);
			g.drawString(Integer.toString((int)Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1))), (x2+x1)*zoom/2, (y2+y1)*zoom/2);
		}
	}



	private TSPSolutionDrawer(TSPSolution solution)
	{
		JFrame window;
		window = new JFrame();
		window.setBounds(30, 30, 600, 600);
		window.getContentPane().add(new TSPGraphComponent(solution));
		window.setVisible(true);
	}
	
	public static void drawTSPSolution(TSPSolution solution)
	{
		new TSPSolutionDrawer(solution);
	}

}
