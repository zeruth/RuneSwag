package rs.api;

import api.DecorativeObject;
import net.runelite.mapping.Import;

public interface RSWallDecoration extends DecorativeObject
{
	@Import("tag")
	@Override
	long getHash();

	@Import("x")
	int getX();

	@Import("y")
	int getY();

	@Import("xOffset")
	int getXOffset();

	@Import("yOffset")
	int getYOffset();

	@Import("orientation")
	int getOrientation();

	@Import("entity1")
	@Override
	RSEntity getRenderable();

	@Import("entity2")
	@Override
	RSEntity getRenderable2();

	void setPlane(int plane);
}
