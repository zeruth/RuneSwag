/*
 * Copyright (c) 2018, https://runelitepl.us
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.vorkath;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import javax.inject.Inject;
import api.Client;
import api.Perspective;
import api.Point;
import api.coords.LocalPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

public class VorkathOverlay extends Overlay
{
	private static final Color COLOR_ICON_BACKGROUND = new Color(0, 0, 0, 128);
	private static final Color COLOR_ICON_BORDER = new Color(0, 0, 0, 255);
	private static final Color COLOR_ICON_BORDER_FILL = new Color(219, 175, 0, 255);
	private static final int OVERLAY_ICON_DISTANCE = 30;
	private static final int OVERLAY_ICON_MARGIN = 1;

	private Client client;
	private VorkathPlugin plugin;

	@Inject
	public VorkathOverlay(Client client, VorkathPlugin plugin)
	{
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_SCENE);
		this.client = client;
		this.plugin = plugin;
	}

	private BufferedImage getIcon(Vorkath.AttackStyle attackStyle)
	{
		switch (attackStyle)
		{
			case MAGERANGE:
				return VorkathPlugin.MAGERANGE;
			case ICE:
				return VorkathPlugin.ICE;
			case ACID:
				return VorkathPlugin.ACID;
		}
		return null;
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (plugin.getVorkath() != null)
		{
			Vorkath vorkath = plugin.getVorkath();

			LocalPoint localLocation = vorkath.getNpc().getLocalLocation();
			if (localLocation != null)
			{
				Point point = Perspective.localToCanvas(client, localLocation, client.getPlane(), vorkath.getNpc().getLogicalHeight() + 16);
				if (point != null)
				{
					point = new Point(point.getX(), point.getY());

					BufferedImage icon = null;
					if (vorkath.getPhase() == 0)
					{
						icon = getIcon(Vorkath.AttackStyle.MAGERANGE);
					}
					else if (vorkath.getPhase() == 1)
					{
						icon = getIcon(Vorkath.AttackStyle.ACID);
					}
					else if (vorkath.getPhase() == 2)
					{
						icon = getIcon(Vorkath.AttackStyle.ICE);
					}

					int totalWidth = icon.getWidth() * OVERLAY_ICON_MARGIN;
					int bgPadding = 8;
					int currentPosX = 0;

					graphics.setStroke(new BasicStroke(2));
					graphics.setColor(COLOR_ICON_BACKGROUND);
					graphics.fillOval(
						point.getX() - totalWidth / 2 + currentPosX - bgPadding,
						point.getY() - icon.getHeight() / 2 - OVERLAY_ICON_DISTANCE - bgPadding,
						icon.getWidth() + bgPadding * 2,
						icon.getHeight() + bgPadding * 2);

					graphics.setColor(COLOR_ICON_BORDER);
					graphics.drawOval(
						point.getX() - totalWidth / 2 + currentPosX - bgPadding,
						point.getY() - icon.getHeight() / 2 - OVERLAY_ICON_DISTANCE - bgPadding,
						icon.getWidth() + bgPadding * 2,
						icon.getHeight() + bgPadding * 2);

					graphics.drawImage(
						icon,
						point.getX() - totalWidth / 2 + currentPosX,
						point.getY() - icon.getHeight() / 2 - OVERLAY_ICON_DISTANCE,
						null);

					graphics.setColor(COLOR_ICON_BORDER_FILL);
					Arc2D.Double arc = new Arc2D.Double(
						point.getX() - totalWidth / 2 + currentPosX - bgPadding,
						point.getY() - icon.getHeight() / 2 - OVERLAY_ICON_DISTANCE - bgPadding,
						icon.getWidth() + bgPadding * 2,
						icon.getHeight() + bgPadding * 2,
						90.0,
						-360.0 * (Vorkath.ATTACKS_PER_SWITCH -
							vorkath.getAttacksUntilSwitch()) / Vorkath.ATTACKS_PER_SWITCH,
						Arc2D.OPEN);
					graphics.draw(arc);
				}
			}
		}

		return null;
	}
}