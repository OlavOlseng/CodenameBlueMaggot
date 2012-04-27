package entity;

import java.util.ArrayList;
import java.util.Iterator;

public class PixelHitbox implements Iterable<FloatingPoint> {

	ArrayList<FloatingPoint> points;

	public PixelHitbox() {
		points = new ArrayList<FloatingPoint>();
	}

	public void addPoint(FloatingPoint point) {
		points.add(point);
	}

	public FloatingPoint getPoint(int index) {
		return points.get(index);
	}

	@Override
	public Iterator<FloatingPoint> iterator() {
		// TODO Auto-generated method stub

		return points.iterator();
	}
}
