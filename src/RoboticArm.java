import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class RoboticArm
{
	private RoboticLink link1, link2, link3;

	private ArrayList<PaintBrush> brushes;

	public RoboticArm( int globalX, int globalY )
	{
		link1 = new RoboticLink( globalX, globalY, 150, 270 );
		link2 = new RoboticLink( globalX, globalY, 100, 0 );
		link3 = new RoboticLink( globalX, globalY, 75, 0 );

		brushes = new ArrayList<PaintBrush>();

		this.onTranslate();
	}

	public void incrementLink1()
	{
		link1.incrementAngle();
	}

	public void incrementLink2()
	{
		link2.incrementAngle();
	}

	public void incrementLink3()
	{
		link3.incrementAngle();
	}

	public void decrementLink1()
	{
		link1.decrementAngle();
	}

	public void decrementLink2()
	{
		link2.decrementAngle();
	}

	public void decrementLink3()
	{
		link3.decrementAngle();
	}

	public void paintCircle()
	{
		brushes.add( new PaintBrush( link3.getEndPointGlobal(), 5 ) );
	}

	public int getLink1Angle()
	{
		return link1.getAngle();
	}

	public int getLink2Angle()
	{
		return link2.getAngle();
	}

	public int getLink3Angle()
	{
		return link3.getAngle();
	}

	/**
	 * Called after any values are changed for one of the robotic links
	 */
	public void onTranslate()
	{
		link1.setGlobalPosition( new Point( 300, 600 ), 0 );
		link1.onTranslate();
		link2.setGlobalPosition( link1.getEndPointGlobal(), link1.getAngle() );
		link2.onTranslate();
		link3.setGlobalPosition( link2.getEndPointGlobal(), link1.getAngle()
				+ link2.getAngle() );
		link3.onTranslate();
	}

	/**
	 * Draws each of the robotic links
	 * @param g Graphics
	 */
	public void onDraw( Graphics g )
	{
		for ( PaintBrush p : this.brushes )
		{
			p.onDraw( g );
		}
		link1.onDraw( g );
		link2.onDraw( g );
		link3.onDraw( g );
	}
}
