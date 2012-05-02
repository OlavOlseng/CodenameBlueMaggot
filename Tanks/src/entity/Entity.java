//Hovedklasse for alt som puttes på brettet,
//skal bli abstrakt

package entity;

import java.text.DecimalFormat;
import java.util.Random;

import networking.NetworkObject;
import networking.NetworkObjectType;

import level.BasicLevel;

import baseGame.Rendering.Renderer;
import blueMaggot.GameState;

public abstract class Entity implements NetworkObject {

	private int id;
	private boolean doSend = false;
	private boolean doTick = true;
	private NetworkObjectType type;
	public DecimalFormat formater;
	protected boolean hitTable;
	public Entity(double x, double y, double xr, double yr, BasicLevel level) {
		
		formater = new DecimalFormat("#00000");
		
		this.level = level;
		setLocation(x, y);
		this.xr = xr;
		this.yr = yr;
		initNetworkValues();
		hitTable = true;
	}
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public boolean shouldTick(){
		return doTick;
	}
	@Override
	public void setSouldTick(boolean doTick){
		this.doTick = doTick;
	}
	@Override
	public abstract void initNetworkValues();

	@Override

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void setShouldBeSent(boolean shouldBesent){
		this.doSend= shouldBesent;
	}
	@Override
	public boolean shouldBeSent(){
		return doSend;
	}

	@Override
	public void setNetworkObjectType(NetworkObjectType type) {
		this.type = type;
	}

	@Override
	public NetworkObjectType getNetworkObjectType() {
		return type;

	}

	protected final Random rand = new Random();
	protected double x, y;
	protected double xr, yr;

	protected BasicLevel level;
	protected double angle = 0;
	protected double dx = 0, dy = 0;
	protected double frictionConstant = 0.05;
	protected double dt;
	public boolean removed = false;
	public double damageTaken = 1;


	@Override
	public void handleMessage(String[] message){
		
		//x
		double x = decodeToDouble(Integer.parseInt(message[4]));
		//y
		double y =	decodeToDouble(Integer.parseInt(message[5]));
		//dx
		
		setLocation(x , y);
		setSpeed(dx, dy);
	}

	@Override

	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setLocation(FloatingPoint point) {
		this.x = point.getX();
		this.y = point.getY();
	}

	public double[] getLocation() {
		double[] location = { x, y };
		return location;
	}

	public double getXr() {
		return xr;
	}

	public double getYr() {
		return yr;
	}

	@Override
	public void setSpeed(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public double getAngle() {
		return this.angle;
	}

	public void accelerate(double ddx, double ddy) {
		this.dx += ddx * dt;
		this.dy += ddy * dt;
	}

	public void gravitate() {
		accelerate(0, 0.1);
	};

	@Override
	public void move(double dt) {
		this.setLocation((this.x + this.dx * dt), (this.y + this.dy * dt));
	}

	protected  int encodeToDouble(double x){
		int num = (int)(x*100);
		return num;
	}
	protected double decodeToDouble(int x){
		return x/100.0;
	}
	@Override
	public String getObject() {

		
		if(type !=null)
		return "'" + getId()+ "'" +type + "'"  + removed + "'"+ encodeToDouble(x)+ "'" + encodeToDouble(y);
		else
		return "";
	}

	protected String to5DigitString(double x) {
		if(x>=0){
			return formater.format(x);
		}else{
			return"-" +formater.format(Math.abs(x)).substring(1);
		}
	}
	
	@Override
	public boolean isRemoved() {
		return removed;
	}

	public boolean intersectsEntity(Entity ent) {
		double[] p = ent.getLocation();
		double xLeft = p[0] - ent.xr;
		double xRight = p[0] + ent.xr;
		double yTop = p[1] - ent.yr;
		double yBot = p[1] + ent.yr;
		if (ent.hitTable && !(x + xr < xLeft || y + yr < yTop || x - xr > xRight || y - yr > yBot)) {
			return true;
		}
		return false;
	}

	public void takeDamage(double amount) {
	}

	public void tick(double dt) {
		this.dt = dt;
		move(dt);
		gravitate();
		if (x > GameState.getInstance().getWidth() + 100 || x < -100 || y > GameState.getInstance().getHeight() + 100 || y < -1000)
			remove();
	}

	public abstract void render(Renderer renderer);

	@Override
	public void remove() {
		this.removed = true;
	}
}
