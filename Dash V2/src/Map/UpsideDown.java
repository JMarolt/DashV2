package Map;

public class UpsideDown extends Portal{

	public UpsideDown(int x, int y, int width, int height, PortalType pt) {
		super(x, y, width, height, pt);
		pt = PortalType.UPSIDEDOWN;
	}

}
