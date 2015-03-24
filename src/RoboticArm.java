import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class RoboticArm {
	private int globalX, globalY;

	private RoboticLink link1, link2, link3;

	private ArrayList<PaintBrush> brushes;

	private boolean isPainting;

	public RoboticArm(int globalX, int globalY) {
		link1 = new RoboticLink(globalX, globalY, 150, 270);
		link2 = new RoboticLink(globalX, globalY, 100, 0);
		link3 = new RoboticLink(globalX, globalY, 75, 0);

		isPainting = false;
		brushes = new ArrayList<PaintBrush>();

		this.onTranslate();
	}

	public void incrementLink1() {
		link1.incrementAngle();
	}

	public void incrementLink2() {
		link2.incrementAngle();
	}

	public void incrementLink3() {
		link3.incrementAngle();
	}

	public void decrementLink1() {
		link1.decrementAngle();
	}

	public void decrementLink2() {
		link2.decrementAngle();
	}

	public void decrementLink3() {
		link3.decrementAngle();
	}

	public void paintCircle() {
		isPainting = !isPainting;
	}

	public boolean isPainting() {
		return this.isPainting;
	}

	public int getLink1Angle() {
		return link1.getAngle();
	}

	public int getLink2Angle() {
		return link2.getAngle();
	}

	public int getLink3Angle() {
		return link3.getAngle();
	}

	public int[] getWorldCoordinates() {
		int coords[] = { globalX, globalY };
		return coords;
	}

	public void incrementGlobalX() {
		++globalX;
	}

	public void incrementGlobalY() {
		++globalY;
	}

	public void decrementGlobalX() {
		--globalX;
	}

	public void decrementGlobalY() {
		--globalY;
	}

	/**
	 * Called after any values are changed for one of the robotic links
	 */
	public void onTranslate() {
		link1.setGlobalPosition(new Point(300, 600), 0);
		link1.onTranslate();
		link2.setGlobalPosition(link1.getEndPointGlobal(), link1.getAngle());
		link2.onTranslate();
		link3.setGlobalPosition(link2.getEndPointGlobal(), link1.getAngle()
				+ link2.getAngle());
		link3.onTranslate();

		globalX = link3.getEndPointGlobal().x;
		globalY = link3.getEndPointGlobal().y;

		if (isPainting) {
			brushes.add(new PaintBrush(link3.getEndPointGlobal(), 5));
		}
	}

	/**
	 * Called when a global position is manually changed
	 */
	public void onWorldTranslate() {
		int maxLength = link1.getLength() + link2.getLength()
				+ link3.getLength();

		int dX = globalX - 300;
		int dY = globalY - 600;

		int maxX = (int) (dX * Math.cos(link1.getAngleRadians()));
		int maxY = (int) (dY * Math.sin(link1.getAngleRadians()));
		int proposedLength = (int) Math.sqrt(Math.pow(maxX, 2)
				+ Math.pow(maxY, 2));

		if (proposedLength < maxLength) {
			System.out.println("robot arm will reach here");
			
			int orig1 = link1.getAngle();
			int orig2 = link2.getAngle();
			int orig3 = link3.getAngle();
			
			int maxAngle1 = link1.getAngle()+2;
			int maxAngle2 = link2.getAngle()+120;
			int maxAngle3 = link3.getAngle()+120;
			
			for (int i = link1.getAngle()-2; i < maxAngle1; ++i) {
				for (int j = link2.getAngle()-120; j < maxAngle2; ++j) {
					for (int k = link3.getAngle()-160; k < maxAngle3; ++k) {
						
						link1.setLocalAngle(i);
						link1.setGlobalPosition(new Point(300, 600), 0);
						link1.onTranslate();
						
						link2.setLocalAngle(j);
						link2.setGlobalPosition(link1.getEndPointGlobal(), link1.getAngle());
						link2.onTranslate();
						
						link3.setLocalAngle(k);
						link3.setGlobalPosition(link2.getEndPointGlobal(), link1.getAngle()
								+ link2.getAngle());
						link3.onTranslate();

						int pX = link3.getEndPointGlobal().x;
						int pY = link3.getEndPointGlobal().y;
						
						if(pX == globalX && pY == globalY){
							if (isPainting) {
								brushes.add(new PaintBrush(link3.getEndPointGlobal(), 5));
							}
							return;
						}
					}
				}
			}
			
			link1.setLocalAngle(orig1);
			link2.setLocalAngle(orig2);
			link3.setLocalAngle(orig3);

		} else {
			System.out.println("robot arm will not reach here");
		}
	}

	/**
	 * Draws each of the robotic links
	 * 
	 * @param g
	 *            Graphics
	 */
	public void onDraw(Graphics g) {
		for (PaintBrush p : this.brushes) {
			p.onDraw(g);
		}
		link1.onDraw(g);
		link2.onDraw(g);
		link3.onDraw(g);
	}
}
