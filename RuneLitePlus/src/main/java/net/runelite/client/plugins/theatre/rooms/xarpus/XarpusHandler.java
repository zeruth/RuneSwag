package net.runelite.client.plugins.theatre.rooms.xarpus;

import lombok.Getter;
import api.ChatMessageType;
import api.Client;
import api.GroundObject;
import api.NPC;
import api.NpcID;
import api.Perspective;
import api.Point;
import api.Varbits;
import api.coords.LocalPoint;
import api.events.GroundObjectSpawned;
import api.events.NpcDespawned;
import api.events.NpcSpawned;
import api.events.VarbitChanged;
import net.runelite.client.plugins.theatre.RoomHandler;
import net.runelite.client.plugins.theatre.TheatreConfig;
import net.runelite.client.plugins.theatre.TheatreConstant;
import net.runelite.client.plugins.theatre.TheatrePlugin;
import net.runelite.client.plugins.theatre.TheatreRoom;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class XarpusHandler extends RoomHandler
{

	private int previousTurn;

	private boolean staring;

	private final Map<GroundObject, Integer> exhumes = new HashMap<>();

	private int ticksUntilShoot = 8;

	@Getter
	private NPC npc;

	private long startTime = 0;
	private boolean up = false;

	@Getter
	private int exhumesCount;
	private boolean xarpusFlag;

	private XarpusCounter overlay = null;

	public XarpusHandler(Client client, TheatrePlugin plugin, TheatreConfig config)
	{
		super(client, plugin, config);
	}

	@Override
	public void onStart()
	{
		if (this.plugin.getRoom() == TheatreRoom.XARPUS)
			return;

		this.reset();
		this.plugin.setRoom(TheatreRoom.XARPUS);

		if (overlay == null)
		{
			overlay = new XarpusCounter(client, plugin, config, this);
			plugin.getOverlayManager().add(overlay);
		}

		System.out.println("Starting Xarpus Room");
	}

	@Override
	public void onStop()
	{
		this.reset();
		this.plugin.setRoom(TheatreRoom.UNKNOWN);

		if (overlay != null)
		{
			plugin.getOverlayManager().remove(overlay);
			overlay = null;
		}

		System.out.println("Stopping Xarpus Room");
	}

	public void reset()
	{
		exhumesCount = 0;
		xarpusFlag = false;

		npc = null;
		staring = false;
		ticksUntilShoot = 8;
		previousTurn = 0;
		this.startTime = 0;
		this.up = false;
		this.exhumes.clear();
	}

	public void render(Graphics2D graphics)
	{
		if (npc == null)
			return;

		if (npc.getId() == NpcID.XARPUS_8340) //&& !staring&& config.showXarpusTick())
		{
			if (!this.up)
			{
				this.up = true;
				long elapsedTime = System.currentTimeMillis() - this.startTime;
				long seconds = elapsedTime / 1000L;
	
				long minutes = seconds / 60L;
				seconds = seconds % 60;
	
				this.ticksUntilShoot = 8;
				if (config.extraTimers())
				this.client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Wave 'Xarpus - Recovery' completed! Duration: <col=ff0000>" + minutes + ":" + twoDigitString(seconds), null);
			}

			final String ticksLeftStr = String.valueOf(ticksUntilShoot);
			Point canvasPoint = npc.getCanvasTextLocation(graphics, ticksLeftStr, 130);
			renderTextLocation(graphics, ticksLeftStr, 12, Font.BOLD, Color.WHITE, canvasPoint);
		}

		if (npc.getId() == NpcID.XARPUS_8339 && config.showXarpusHeals())
		{
			for (GroundObject o : exhumes.keySet())
			{
				Polygon poly = o.getCanvasTilePoly();
				if (poly != null)
				{
					Color c = new Color(0, 255, 0, 130);
					graphics.setColor(c);
					graphics.setStroke(new BasicStroke(1));
					graphics.draw(poly);

					String count = Integer.toString(exhumes.get(o) + 1);
					LocalPoint lp = o.getLocalLocation();
					Point point = Perspective.getCanvasTextLocation(client, graphics, lp, count, 0);
					if (point != null)
					{
						renderTextLocation(graphics, count, 14, Font.BOLD, Color.WHITE, point);
					}
				}
			}
		}
	}

	public void onVarbitChanged(VarbitChanged event)
	{
		if (client.getVar(Varbits.MULTICOMBAT_AREA) == 1 || client.getVarbitValue(client.getVarps(), TheatreConstant.DOOR_VARP) == 2)
		{
			if (!xarpusFlag)
			{
				int players = client.getPlayers().size();

				if (players == 5)
				{
					exhumesCount = 18;
				}
				else if (players == 4)
				{
					exhumesCount = 15;
				}
				else if (players == 3)
				{
					exhumesCount = 12;
				}
				else if (players == 2)
				{
					exhumesCount = 9;
				}
				else
				{
					exhumesCount = 7;
				}

				xarpusFlag = true;
			}
		}
	}

	public void onNpcSpawned(NpcSpawned event)
	{
		NPC npc = event.getNpc();

		if (npc.getName() != null && npc.getName().equals("Xarpus"))
		{
			this.onStart();
			this.npc = npc;
		}
	}

	public void onNpcDespawned(NpcDespawned event)
	{
		NPC npc = event.getNpc();

		if (npc.getName() != null && npc.getName().equals("Xarpus"))
		{
			this.onStop();
		}
	}

	public void onGroundObjectSpawned(GroundObjectSpawned event)
	{
		if (plugin.getRoom() != TheatreRoom.XARPUS)
		{
			return;
		}

		GroundObject o = event.getGroundObject();
		if (o.getId() == TheatreConstant.GROUNDOBJECT_ID_EXHUMED)
		{
			if (this.startTime == 0)
			{
				this.startTime = System.currentTimeMillis() - 2000L;
			}

//			exhumes.put(o, 18);
			exhumes.put(o, 11);
		}
	}

	public void onGameTick()
	{
		if (plugin.getRoom() != TheatreRoom.XARPUS)
		{
			return;
		}

		for (GroundObject key : new ArrayList<>(exhumes.keySet()))
		{
			int i = exhumes.get(key) - 1;
			if (i >= 0)
			{
				exhumes.replace(key, i);
			}
			else
			{
				exhumes.remove(key);
				this.exhumesCount--;
			}
		}

		if (npc.getOverheadText() != null)
		{
			if (!staring)
			{
				staring = true;

				long elapsedTime = System.currentTimeMillis() - this.startTime;
				long seconds = elapsedTime / 1000L;

				long minutes = seconds / 60L;
				seconds = seconds % 60;
				if (config.extraTimers())
				this.client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Wave 'Xarpus - Acid' completed! Duration: <col=ff0000>" + minutes + ":" + twoDigitString(seconds), null);
			}

			ticksUntilShoot = 6;
		}

		ticksUntilShoot--;
		ticksUntilShoot = Math.max(0, ticksUntilShoot);
		if (ticksUntilShoot <= 0)
		{
			ticksUntilShoot = 4;
		}

		if (previousTurn != npc.getOrientation())
		{
			if (staring)
			{
				ticksUntilShoot = 8;
			} 
			else
			{
				ticksUntilShoot = 4;
			}

			previousTurn = npc.getOrientation();
		}
	}
}