package rs.api;

import api.EnumDefinition;
import net.runelite.mapping.Import;

public interface RSEnumDefinition extends EnumDefinition, RSDualNode
{
	@Import("keys")
	@Override
	int[] getKeys();

	@Import("intVals")
	@Override
	int[] getIntVals();

	@Import("stringVals")
	@Override
	String[] getStringVals();

	@Import("defaultInt")
	int getDefaultInt();

	@Import("defaultString")
	String getDefaultString();
}
