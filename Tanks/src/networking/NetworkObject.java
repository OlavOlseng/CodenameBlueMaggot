package networking;

public interface NetworkObject {

	public String getObject();
	public int getId();
	public void setNetworkObjectType(NetworkObjectType type);
	public NetworkObjectType getNetworkObjectType();
	public void handleMessage(String[] message);
	public void initNetworkValues();	
	public void remove();
	public boolean isRemoved();
	public void setId(int value);
	public void setSpeed(double dx, double dy);
	public void setLocation(double x, double y);
	public void move(double dt);
	public void setShouldBeSent(boolean isClient);
	public boolean shouldBeSent();
	public boolean shouldTick();
	public void setSouldTick(boolean doTick);

}
