package level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entity.FloatingPoint;

public class SpawnTracker {

	private Random rand = new Random();
	private List<FloatingPoint> spawnPoints;
	private int lastPoint = -1;

	public SpawnTracker() {
		spawnPoints = new ArrayList<FloatingPoint>();
	}

	public void addPoint(FloatingPoint point) {
		spawnPoints.add(point);
	}

	public FloatingPoint getPoint() {
		int index;
		do {
			index = rand.nextInt(spawnPoints.size());
		} while (index == lastPoint);
		lastPoint = index;
		return spawnPoints.get(index);
	}

	public int size() {
		return spawnPoints.size();
	}

}
