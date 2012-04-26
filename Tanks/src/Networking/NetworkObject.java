package Networking;

public interface NetworkObject {
	
	public String getObject();
	public int getId();
	public void setId(int value);
	public void setSpeed(double dx,double dy);
	public void setLocation(double x,double y);
}
