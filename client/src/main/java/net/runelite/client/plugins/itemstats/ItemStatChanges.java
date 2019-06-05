/*
 * Copyright (c) 2016-2018, Adam <Adam@sigterm.info>
 * Copyright (c) 2018, Jordan Atwood <jordan.atwood423@gmail.com>
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
package net.runelite.client.plugins.itemstats;

import com.google.inject.Singleton;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import static api.ItemID.ADMIRAL_PIE;
import static api.ItemID.AGILITY_POTION1;
import static api.ItemID.AGILITY_POTION2;
import static api.ItemID.AGILITY_POTION3;
import static api.ItemID.AGILITY_POTION4;
import static api.ItemID.ANCHOVIES;
import static api.ItemID.ANCHOVY_PIZZA;
import static api.ItemID.ANGLERFISH;
import static api.ItemID.APPLE_PIE;
import static api.ItemID.ATTACK_POTION1;
import static api.ItemID.ATTACK_POTION2;
import static api.ItemID.ATTACK_POTION3;
import static api.ItemID.ATTACK_POTION4;
import static api.ItemID.AUTUMN_SQIRKJUICE;
import static api.ItemID.BAGUETTE;
import static api.ItemID.BAKED_POTATO;
import static api.ItemID.BANANA;
import static api.ItemID.BANDAGES;
import static api.ItemID.BASS;
import static api.ItemID.BASTION_POTION1;
import static api.ItemID.BASTION_POTION2;
import static api.ItemID.BASTION_POTION3;
import static api.ItemID.BASTION_POTION4;
import static api.ItemID.BATTLEMAGE_POTION1;
import static api.ItemID.BATTLEMAGE_POTION2;
import static api.ItemID.BATTLEMAGE_POTION3;
import static api.ItemID.BATTLEMAGE_POTION4;
import static api.ItemID.BAT_SHISH;
import static api.ItemID.BOTANICAL_PIE;
import static api.ItemID.BOTTLE_OF_WINE;
import static api.ItemID.BRAWK_FISH_3;
import static api.ItemID.BREAD;
import static api.ItemID.CABBAGE;
import static api.ItemID.CABBAGE_1967;
import static api.ItemID.CAKE;
import static api.ItemID.CAVE_EEL;
import static api.ItemID.CAVIAR;
import static api.ItemID.CHEESE;
import static api.ItemID.CHEESETOM_BATTA;
import static api.ItemID.CHILLI_CON_CARNE;
import static api.ItemID.CHILLI_POTATO;
import static api.ItemID.CHOCCHIP_CRUNCHIES;
import static api.ItemID.CHOCICE;
import static api.ItemID.CHOCOLATEY_MILK;
import static api.ItemID.CHOCOLATE_BAR;
import static api.ItemID.CHOCOLATE_BOMB;
import static api.ItemID.CHOCOLATE_CAKE;
import static api.ItemID.CHOCOLATE_SLICE;
import static api.ItemID.CHOC_SATURDAY;
import static api.ItemID.CHOPPED_ONION;
import static api.ItemID.CHOPPED_TOMATO;
import static api.ItemID.CHOPPED_TUNA;
import static api.ItemID.COATED_FROGS_LEGS;
import static api.ItemID.COD;
import static api.ItemID.COMBAT_POTION1;
import static api.ItemID.COMBAT_POTION2;
import static api.ItemID.COMBAT_POTION3;
import static api.ItemID.COMBAT_POTION4;
import static api.ItemID.COOKED_CHICKEN;
import static api.ItemID.COOKED_CHOMPY;
import static api.ItemID.COOKED_CRAB_MEAT;
import static api.ItemID.COOKED_FISHCAKE;
import static api.ItemID.COOKED_JUBBLY;
import static api.ItemID.COOKED_KARAMBWAN;
import static api.ItemID.COOKED_MEAT;
import static api.ItemID.COOKED_RABBIT;
import static api.ItemID.COOKED_SLIMY_EEL;
import static api.ItemID.COOKED_SWEETCORN;
import static api.ItemID.CURRY;
import static api.ItemID.DARK_CRAB;
import static api.ItemID.DEFENCE_POTION1;
import static api.ItemID.DEFENCE_POTION2;
import static api.ItemID.DEFENCE_POTION3;
import static api.ItemID.DEFENCE_POTION4;
import static api.ItemID.DRUNK_DRAGON;
import static api.ItemID.DWELLBERRIES;
import static api.ItemID.EASTER_EGG;
import static api.ItemID.EDIBLE_SEAWEED;
import static api.ItemID.EEL_SUSHI;
import static api.ItemID.EGG_AND_TOMATO;
import static api.ItemID.EGG_POTATO;
import static api.ItemID.ELDER_1;
import static api.ItemID.ELDER_1_20921;
import static api.ItemID.ELDER_2;
import static api.ItemID.ELDER_2_20922;
import static api.ItemID.ELDER_3;
import static api.ItemID.ELDER_3_20923;
import static api.ItemID.ELDER_4;
import static api.ItemID.ELDER_4_20924;
import static api.ItemID.ELDER_POTION_1;
import static api.ItemID.ELDER_POTION_2;
import static api.ItemID.ELDER_POTION_3;
import static api.ItemID.ELDER_POTION_4;
import static api.ItemID.ENERGY_POTION1;
import static api.ItemID.ENERGY_POTION2;
import static api.ItemID.ENERGY_POTION3;
import static api.ItemID.ENERGY_POTION4;
import static api.ItemID.FAT_SNAIL_MEAT;
import static api.ItemID.FIELD_RATION;
import static api.ItemID.FILLETS;
import static api.ItemID.FINGERS;
import static api.ItemID.FISHING_POTION1;
import static api.ItemID.FISHING_POTION2;
import static api.ItemID.FISHING_POTION3;
import static api.ItemID.FISHING_POTION4;
import static api.ItemID.FISH_PIE;
import static api.ItemID.FRIED_MUSHROOMS;
import static api.ItemID.FRIED_ONIONS;
import static api.ItemID.FROGBURGER;
import static api.ItemID.FROGSPAWN_GUMBO;
import static api.ItemID.FROG_SPAWN;
import static api.ItemID.FRUIT_BATTA;
import static api.ItemID.FRUIT_BLAST;
import static api.ItemID.GARDEN_PIE;
import static api.ItemID.GIANT_CARP;
import static api.ItemID.GIRAL_BAT_2;
import static api.ItemID.GOUT_TUBER;
import static api.ItemID.GREEN_GLOOP_SOUP;
import static api.ItemID.GRUBS__LA_MODE;
import static api.ItemID.GUANIC_BAT_0;
import static api.ItemID.GUTHIX_REST1;
import static api.ItemID.GUTHIX_REST2;
import static api.ItemID.GUTHIX_REST3;
import static api.ItemID.GUTHIX_REST4;
import static api.ItemID.HALF_AN_ADMIRAL_PIE;
import static api.ItemID.HALF_AN_APPLE_PIE;
import static api.ItemID.HALF_A_BOTANICAL_PIE;
import static api.ItemID.HALF_A_FISH_PIE;
import static api.ItemID.HALF_A_GARDEN_PIE;
import static api.ItemID.HALF_A_MEAT_PIE;
import static api.ItemID.HALF_A_MUSHROOM_PIE;
import static api.ItemID.HALF_A_REDBERRY_PIE;
import static api.ItemID.HALF_A_SUMMER_PIE;
import static api.ItemID.HALF_A_WILD_PIE;
import static api.ItemID.HERRING;
import static api.ItemID.HUNTER_POTION1;
import static api.ItemID.HUNTER_POTION2;
import static api.ItemID.HUNTER_POTION3;
import static api.ItemID.HUNTER_POTION4;
import static api.ItemID.IMBUED_HEART;
import static api.ItemID.JANGERBERRIES;
import static api.ItemID.JUG_OF_WINE;
import static api.ItemID.KODAI_1;
import static api.ItemID.KODAI_1_20945;
import static api.ItemID.KODAI_2;
import static api.ItemID.KODAI_2_20946;
import static api.ItemID.KODAI_3;
import static api.ItemID.KODAI_3_20947;
import static api.ItemID.KODAI_4;
import static api.ItemID.KODAI_4_20948;
import static api.ItemID.KODAI_POTION_1;
import static api.ItemID.KODAI_POTION_2;
import static api.ItemID.KODAI_POTION_3;
import static api.ItemID.KODAI_POTION_4;
import static api.ItemID.KRYKET_BAT_4;
import static api.ItemID.KYREN_FISH_6;
import static api.ItemID.LAVA_EEL;
import static api.ItemID.LECKISH_FISH_2;
import static api.ItemID.LEMON;
import static api.ItemID.LEMON_CHUNKS;
import static api.ItemID.LEMON_SLICES;
import static api.ItemID.LIME;
import static api.ItemID.LIME_CHUNKS;
import static api.ItemID.LIME_SLICES;
import static api.ItemID.LOACH;
import static api.ItemID.LOBSTER;
import static api.ItemID.MACKEREL;
import static api.ItemID.MAGIC_ESSENCE1;
import static api.ItemID.MAGIC_ESSENCE2;
import static api.ItemID.MAGIC_ESSENCE3;
import static api.ItemID.MAGIC_ESSENCE4;
import static api.ItemID.MAGIC_POTION1;
import static api.ItemID.MAGIC_POTION2;
import static api.ItemID.MAGIC_POTION3;
import static api.ItemID.MAGIC_POTION4;
import static api.ItemID.MANTA_RAY;
import static api.ItemID.MEAT_PIE;
import static api.ItemID.MEAT_PIZZA;
import static api.ItemID.MINT_CAKE;
import static api.ItemID.MONKFISH;
import static api.ItemID.MOONLIGHT_MEAD;
import static api.ItemID.MURNG_BAT_5;
import static api.ItemID.MUSHROOMS;
import static api.ItemID.MUSHROOM_PIE;
import static api.ItemID.MUSHROOM_POTATO;
import static api.ItemID.MUSHROOM__ONION;
import static api.ItemID.MYCIL_FISH_4;
import static api.ItemID.ONION;
import static api.ItemID.ORANGE;
import static api.ItemID.ORANGE_CHUNKS;
import static api.ItemID.ORANGE_SLICES;
import static api.ItemID.OVERLOAD_1;
import static api.ItemID.OVERLOAD_1_20985;
import static api.ItemID.OVERLOAD_1_20989;
import static api.ItemID.OVERLOAD_1_20993;
import static api.ItemID.OVERLOAD_2;
import static api.ItemID.OVERLOAD_2_20986;
import static api.ItemID.OVERLOAD_2_20990;
import static api.ItemID.OVERLOAD_2_20994;
import static api.ItemID.OVERLOAD_3;
import static api.ItemID.OVERLOAD_3_20987;
import static api.ItemID.OVERLOAD_3_20991;
import static api.ItemID.OVERLOAD_3_20995;
import static api.ItemID.OVERLOAD_4;
import static api.ItemID.OVERLOAD_4_20988;
import static api.ItemID.OVERLOAD_4_20992;
import static api.ItemID.OVERLOAD_4_20996;
import static api.ItemID.PAPAYA_FRUIT;
import static api.ItemID.PEACH;
import static api.ItemID.PHLUXIA_BAT_3;
import static api.ItemID.PIKE;
import static api.ItemID.PINEAPPLE_CHUNKS;
import static api.ItemID.PINEAPPLE_PIZZA;
import static api.ItemID.PINEAPPLE_PUNCH;
import static api.ItemID.PINEAPPLE_RING;
import static api.ItemID.PLAIN_PIZZA;
import static api.ItemID.POISON_KARAMBWAN;
import static api.ItemID.POTATO;
import static api.ItemID.POTATO_WITH_BUTTER;
import static api.ItemID.POTATO_WITH_CHEESE;
import static api.ItemID.POT_OF_CREAM;
import static api.ItemID.PRAEL_BAT_1;
import static api.ItemID.PRAYER_POTION1;
import static api.ItemID.PRAYER_POTION2;
import static api.ItemID.PRAYER_POTION3;
import static api.ItemID.PRAYER_POTION4;
import static api.ItemID.PREMADE_CHOC_BOMB;
import static api.ItemID.PREMADE_CHOC_SDY;
import static api.ItemID.PREMADE_CH_CRUNCH;
import static api.ItemID.PREMADE_CT_BATTA;
import static api.ItemID.PREMADE_DR_DRAGON;
import static api.ItemID.PREMADE_FRT_BATTA;
import static api.ItemID.PREMADE_FR_BLAST;
import static api.ItemID.PREMADE_P_PUNCH;
import static api.ItemID.PREMADE_SGG;
import static api.ItemID.PREMADE_SY_CRUNCH;
import static api.ItemID.PREMADE_TD_BATTA;
import static api.ItemID.PREMADE_TD_CRUNCH;
import static api.ItemID.PREMADE_TTL;
import static api.ItemID.PREMADE_VEG_BALL;
import static api.ItemID.PREMADE_VEG_BATTA;
import static api.ItemID.PREMADE_WIZ_BLZD;
import static api.ItemID.PREMADE_WM_BATTA;
import static api.ItemID.PREMADE_WM_CRUN;
import static api.ItemID.PREMADE_WORM_HOLE;
import static api.ItemID.PSYKK_BAT_6;
import static api.ItemID.PUMPKIN;
import static api.ItemID.PURPLE_SWEETS_10476;
import static api.ItemID.PYSK_FISH_0;
import static api.ItemID.RAINBOW_FISH;
import static api.ItemID.RANGING_POTION1;
import static api.ItemID.RANGING_POTION2;
import static api.ItemID.RANGING_POTION3;
import static api.ItemID.RANGING_POTION4;
import static api.ItemID.REDBERRY_PIE;
import static api.ItemID.RESTORE_POTION1;
import static api.ItemID.RESTORE_POTION2;
import static api.ItemID.RESTORE_POTION3;
import static api.ItemID.RESTORE_POTION4;
import static api.ItemID.REVITALISATION_1_20957;
import static api.ItemID.REVITALISATION_2_20958;
import static api.ItemID.REVITALISATION_3_20959;
import static api.ItemID.REVITALISATION_4_20960;
import static api.ItemID.ROAST_BEAST_MEAT;
import static api.ItemID.ROAST_BIRD_MEAT;
import static api.ItemID.ROAST_FROG;
import static api.ItemID.ROAST_RABBIT;
import static api.ItemID.ROE;
import static api.ItemID.ROLL;
import static api.ItemID.ROQED_FISH_5;
import static api.ItemID.SALMON;
import static api.ItemID.SANFEW_SERUM1;
import static api.ItemID.SANFEW_SERUM2;
import static api.ItemID.SANFEW_SERUM3;
import static api.ItemID.SANFEW_SERUM4;
import static api.ItemID.SARADOMIN_BREW1;
import static api.ItemID.SARADOMIN_BREW2;
import static api.ItemID.SARADOMIN_BREW3;
import static api.ItemID.SARADOMIN_BREW4;
import static api.ItemID.SARDINE;
import static api.ItemID.SEA_TURTLE;
import static api.ItemID.SHARK;
import static api.ItemID.SHORT_GREEN_GUY;
import static api.ItemID.SHRIMPS;
import static api.ItemID.SLICED_BANANA;
import static api.ItemID.SLICE_OF_CAKE;
import static api.ItemID.SPICY_CRUNCHIES;
import static api.ItemID.SPICY_SAUCE;
import static api.ItemID.SPICY_STEW;
import static api.ItemID.SPINACH_ROLL;
import static api.ItemID.SPRING_SQIRKJUICE;
import static api.ItemID.SQUARE_SANDWICH;
import static api.ItemID.STAMINA_POTION1;
import static api.ItemID.STAMINA_POTION2;
import static api.ItemID.STAMINA_POTION3;
import static api.ItemID.STAMINA_POTION4;
import static api.ItemID.STEW;
import static api.ItemID.STRANGE_FRUIT;
import static api.ItemID.STRAWBERRY;
import static api.ItemID.STRENGTH_POTION1;
import static api.ItemID.STRENGTH_POTION2;
import static api.ItemID.STRENGTH_POTION3;
import static api.ItemID.STRENGTH_POTION4;
import static api.ItemID.STUFFED_SNAKE;
import static api.ItemID.SUMMER_PIE;
import static api.ItemID.SUMMER_SQIRKJUICE;
import static api.ItemID.SUPER_ATTACK1;
import static api.ItemID.SUPER_ATTACK2;
import static api.ItemID.SUPER_ATTACK3;
import static api.ItemID.SUPER_ATTACK4;
import static api.ItemID.SUPER_COMBAT_POTION1;
import static api.ItemID.SUPER_COMBAT_POTION2;
import static api.ItemID.SUPER_COMBAT_POTION3;
import static api.ItemID.SUPER_COMBAT_POTION4;
import static api.ItemID.SUPER_DEFENCE1;
import static api.ItemID.SUPER_DEFENCE2;
import static api.ItemID.SUPER_DEFENCE3;
import static api.ItemID.SUPER_DEFENCE4;
import static api.ItemID.SUPER_ENERGY1;
import static api.ItemID.SUPER_ENERGY2;
import static api.ItemID.SUPER_ENERGY3;
import static api.ItemID.SUPER_ENERGY4;
import static api.ItemID.SUPER_MAGIC_POTION_1;
import static api.ItemID.SUPER_MAGIC_POTION_2;
import static api.ItemID.SUPER_MAGIC_POTION_3;
import static api.ItemID.SUPER_MAGIC_POTION_4;
import static api.ItemID.SUPER_RANGING_1;
import static api.ItemID.SUPER_RANGING_2;
import static api.ItemID.SUPER_RANGING_3;
import static api.ItemID.SUPER_RANGING_4;
import static api.ItemID.SUPER_RESTORE1;
import static api.ItemID.SUPER_RESTORE2;
import static api.ItemID.SUPER_RESTORE3;
import static api.ItemID.SUPER_RESTORE4;
import static api.ItemID.SUPER_STRENGTH1;
import static api.ItemID.SUPER_STRENGTH2;
import static api.ItemID.SUPER_STRENGTH3;
import static api.ItemID.SUPER_STRENGTH4;
import static api.ItemID.SUPHI_FISH_1;
import static api.ItemID.SWEETCORN_7088;
import static api.ItemID.SWORDFISH;
import static api.ItemID.TANGLED_TOADS_LEGS;
import static api.ItemID.THIN_SNAIL_MEAT;
import static api.ItemID.TOAD_BATTA;
import static api.ItemID.TOAD_CRUNCHIES;
import static api.ItemID.TOMATO;
import static api.ItemID.TRIANGLE_SANDWICH;
import static api.ItemID.TROUT;
import static api.ItemID.TUNA;
import static api.ItemID.TUNA_AND_CORN;
import static api.ItemID.TUNA_POTATO;
import static api.ItemID.TWISTED_1;
import static api.ItemID.TWISTED_1_20933;
import static api.ItemID.TWISTED_2;
import static api.ItemID.TWISTED_2_20934;
import static api.ItemID.TWISTED_3;
import static api.ItemID.TWISTED_3_20935;
import static api.ItemID.TWISTED_4;
import static api.ItemID.TWISTED_4_20936;
import static api.ItemID.TWISTED_POTION_1;
import static api.ItemID.TWISTED_POTION_2;
import static api.ItemID.TWISTED_POTION_3;
import static api.ItemID.TWISTED_POTION_4;
import static api.ItemID.UGTHANKI_KEBAB;
import static api.ItemID.UGTHANKI_KEBAB_1885;
import static api.ItemID.VEGETABLE_BATTA;
import static api.ItemID.VEG_BALL;
import static api.ItemID.WATERMELON_SLICE;
import static api.ItemID.WHITE_TREE_FRUIT;
import static api.ItemID.WILD_PIE;
import static api.ItemID.WINTER_SQIRKJUICE;
import static api.ItemID.WIZARD_BLIZZARD;
import static api.ItemID.WORM_BATTA;
import static api.ItemID.WORM_CRUNCHIES;
import static api.ItemID.WORM_HOLE;
import static api.ItemID.XERICS_AID_1_20981;
import static api.ItemID.XERICS_AID_2_20982;
import static api.ItemID.XERICS_AID_3_20983;
import static api.ItemID.XERICS_AID_4_20984;
import static api.ItemID.ZAMORAK_BREW1;
import static api.ItemID.ZAMORAK_BREW2;
import static api.ItemID.ZAMORAK_BREW3;
import static api.ItemID.ZAMORAK_BREW4;
import static api.ItemID._12_ANCHOVY_PIZZA;
import static api.ItemID._12_MEAT_PIZZA;
import static api.ItemID._12_PINEAPPLE_PIZZA;
import static api.ItemID._12_PLAIN_PIZZA;
import static api.ItemID._23_CAKE;
import static api.ItemID._23_CHOCOLATE_CAKE;
import static net.runelite.client.plugins.itemstats.Builders.boost;
import static net.runelite.client.plugins.itemstats.Builders.combo;
import static net.runelite.client.plugins.itemstats.Builders.dec;
import static net.runelite.client.plugins.itemstats.Builders.food;
import static net.runelite.client.plugins.itemstats.Builders.heal;
import static net.runelite.client.plugins.itemstats.Builders.perc;
import static net.runelite.client.plugins.itemstats.Builders.range;
import net.runelite.client.plugins.itemstats.food.Anglerfish;
import net.runelite.client.plugins.itemstats.potions.PrayerPotion;
import net.runelite.client.plugins.itemstats.potions.SaradominBrew;
import net.runelite.client.plugins.itemstats.potions.SuperRestore;
import net.runelite.client.plugins.itemstats.special.CastleWarsBandage;
import net.runelite.client.plugins.itemstats.special.SpicyStew;
import static net.runelite.client.plugins.itemstats.stats.Stats.AGILITY;
import static net.runelite.client.plugins.itemstats.stats.Stats.ATTACK;
import static net.runelite.client.plugins.itemstats.stats.Stats.CRAFTING;
import static net.runelite.client.plugins.itemstats.stats.Stats.DEFENCE;
import static net.runelite.client.plugins.itemstats.stats.Stats.FARMING;
import static net.runelite.client.plugins.itemstats.stats.Stats.FISHING;
import static net.runelite.client.plugins.itemstats.stats.Stats.HERBLORE;
import static net.runelite.client.plugins.itemstats.stats.Stats.HITPOINTS;
import static net.runelite.client.plugins.itemstats.stats.Stats.HUNTER;
import static net.runelite.client.plugins.itemstats.stats.Stats.MAGIC;
import static net.runelite.client.plugins.itemstats.stats.Stats.PRAYER;
import static net.runelite.client.plugins.itemstats.stats.Stats.RANGED;
import static net.runelite.client.plugins.itemstats.stats.Stats.RUN_ENERGY;
import static net.runelite.client.plugins.itemstats.stats.Stats.SLAYER;
import static net.runelite.client.plugins.itemstats.stats.Stats.STRENGTH;
import static net.runelite.client.plugins.itemstats.stats.Stats.THIEVING;

@Singleton
@Slf4j
public class ItemStatChanges
{
	ItemStatChanges()
	{
		init();
	}

	private void init()
	{
		add(food(-5), POISON_KARAMBWAN);
		add(food(1), POTATO, ONION, CABBAGE, POT_OF_CREAM, CHOPPED_ONION, ANCHOVIES);
		add(food(2), TOMATO, CHOPPED_TOMATO, BANANA, SLICED_BANANA, ORANGE, ORANGE_SLICES, ORANGE_CHUNKS,
			PINEAPPLE_RING, PINEAPPLE_CHUNKS, SPICY_SAUCE, CHEESE, SPINACH_ROLL, LEMON, LEMON_CHUNKS, LEMON_SLICES,
			LIME, LIME_CHUNKS, LIME_SLICES, DWELLBERRIES);
		add(food(3), SHRIMPS, COOKED_MEAT, COOKED_CHICKEN, ROE, CHOCOLATE_BAR);
		add(food(4), SARDINE, CAKE, _23_CAKE, SLICE_OF_CAKE, CHOCOLATEY_MILK, BAKED_POTATO, EDIBLE_SEAWEED, MOONLIGHT_MEAD);
		add(food(5), BREAD, HERRING, CHOCOLATE_CAKE, _23_CHOCOLATE_CAKE, CHOCOLATE_SLICE, COOKED_RABBIT, CHILLI_CON_CARNE,
			FRIED_MUSHROOMS, FRIED_ONIONS, REDBERRY_PIE, HALF_A_REDBERRY_PIE, CAVIAR, PYSK_FISH_0);
		add(food(6), CHOCICE, MACKEREL, MEAT_PIE, HALF_A_MEAT_PIE, GUANIC_BAT_0, ROAST_BIRD_MEAT,
			SQUARE_SANDWICH, ROLL, BAGUETTE, TRIANGLE_SANDWICH, GIANT_CARP);
		add(food(7), TROUT, COD, PLAIN_PIZZA, _12_PLAIN_PIZZA, APPLE_PIE, HALF_AN_APPLE_PIE, ROAST_RABBIT,
			PREMADE_CH_CRUNCH, CHOCCHIP_CRUNCHIES, PREMADE_SY_CRUNCH, SPICY_CRUNCHIES);
		add(food(8), PIKE, ROAST_BEAST_MEAT, MEAT_PIZZA, _12_MEAT_PIZZA, PREMADE_WM_CRUN, WORM_CRUNCHIES, PREMADE_TD_CRUNCH,
			TOAD_CRUNCHIES, EGG_AND_TOMATO, PRAEL_BAT_1, PEACH, SUPHI_FISH_1);
		add(food(9), PREMADE_P_PUNCH, PINEAPPLE_PUNCH, PREMADE_FR_BLAST, FRUIT_BLAST, SALMON, ANCHOVY_PIZZA,
			_12_ANCHOVY_PIZZA);
		add(food(10), TUNA, COOKED_CRAB_MEAT, CHOPPED_TUNA, COOKED_CHOMPY, FIELD_RATION);
		add(food(11), RAINBOW_FISH, STEW, PINEAPPLE_PIZZA, _12_PINEAPPLE_PIZZA, COOKED_FISHCAKE,
			PREMADE_VEG_BATTA, VEGETABLE_BATTA, PREMADE_WM_BATTA, WORM_BATTA, PREMADE_TD_BATTA, TOAD_BATTA, PREMADE_CT_BATTA,
			CHEESETOM_BATTA, PREMADE_FRT_BATTA, FRUIT_BATTA, MUSHROOM__ONION, GIRAL_BAT_2, LAVA_EEL, LECKISH_FISH_2);
		add(food(12), LOBSTER, PREMADE_WORM_HOLE, WORM_HOLE, PREMADE_VEG_BALL, VEG_BALL);
		add(food(13), BASS, TUNA_AND_CORN);
		add(food(14), POTATO_WITH_BUTTER, CHILLI_POTATO, SWORDFISH, PHLUXIA_BAT_3, PUMPKIN, EASTER_EGG, BRAWK_FISH_3);
		add(food(15), PREMADE_TTL, TANGLED_TOADS_LEGS, PREMADE_CHOC_BOMB, CHOCOLATE_BOMB, COOKED_JUBBLY);
		add(food(16), MONKFISH, POTATO_WITH_CHEESE, EGG_POTATO);
		add(food(17), MYCIL_FISH_4, KRYKET_BAT_4);
		add(food(18), COOKED_KARAMBWAN);
		add(food(19), CURRY, UGTHANKI_KEBAB, UGTHANKI_KEBAB_1885);
		add(food(20), MUSHROOM_POTATO, SHARK, ROQED_FISH_5, MURNG_BAT_5, STUFFED_SNAKE);
		add(food(21), SEA_TURTLE);
		add(food(22), MANTA_RAY, DARK_CRAB, TUNA_POTATO);
		add(food(23), KYREN_FISH_6, PSYKK_BAT_6);
		add(new Anglerfish(), ANGLERFISH);
		add(food(maxHP -> (int) Math.ceil(maxHP * .06)), STRAWBERRY);
		add(food(maxHP -> (int) Math.ceil(maxHP * .05)), WATERMELON_SLICE);
		add(food(perc(.1, 1)), COOKED_SWEETCORN, SWEETCORN_7088 /* Bowl of cooked sweetcorn */);
		add(combo(food(1), boost(DEFENCE, perc(.02, 1))), CABBAGE_1967 /* Draynor Manor */);
		add(combo(2, food(8), heal(RUN_ENERGY, 5)), PAPAYA_FRUIT);
		add(range(food(5), food(7)), THIN_SNAIL_MEAT);
		add(range(food(7), food(9)), FAT_SNAIL_MEAT);

		// Dorgeshuun Cuisine
		add(food(2), BAT_SHISH, COATED_FROGS_LEGS, FILLETS, FINGERS, FROGBURGER, FROGSPAWN_GUMBO, GREEN_GLOOP_SOUP,
			GRUBS__LA_MODE, MUSHROOMS, ROAST_FROG);
		add(food(3), LOACH);
		add(range(food(3), food(6)), FROG_SPAWN);
		add(range(food(6), food(10)), COOKED_SLIMY_EEL);
		add(range(food(8), food(12)), CAVE_EEL);
		add(food(10), EEL_SUSHI);

		// Alcoholic Beverages
		add(combo(food(11), dec(ATTACK, 2)), JUG_OF_WINE);
		add(combo(food(14), dec(ATTACK, 3)), BOTTLE_OF_WINE);
		add(combo(2, food(5), boost(STRENGTH, 6), heal(ATTACK, -4)), PREMADE_WIZ_BLZD, WIZARD_BLIZZARD);
		add(combo(2, food(5), boost(STRENGTH, 4), heal(ATTACK, -3)), PREMADE_SGG, SHORT_GREEN_GUY);
		add(combo(2, food(5), boost(STRENGTH, 7), heal(ATTACK, -4)), PREMADE_DR_DRAGON, DRUNK_DRAGON);
		add(combo(2, food(5), boost(STRENGTH, 7), heal(ATTACK, -4)), PREMADE_CHOC_SDY, CHOC_SATURDAY);

		// Sq'irk Juice
		add(heal(RUN_ENERGY, 5), WINTER_SQIRKJUICE);
		add(combo(heal(RUN_ENERGY, 10), boost(THIEVING, 1)), SPRING_SQIRKJUICE);
		add(combo(heal(RUN_ENERGY, 15), boost(THIEVING, 2)), AUTUMN_SQIRKJUICE);
		add(combo(heal(RUN_ENERGY, 20), boost(THIEVING, 3)), SUMMER_SQIRKJUICE);

		// Combat potions
		add(boost(ATTACK, perc(.10, 3)), ATTACK_POTION1, ATTACK_POTION2, ATTACK_POTION3, ATTACK_POTION4);
		add(boost(STRENGTH, perc(.10, 3)), STRENGTH_POTION1, STRENGTH_POTION2, STRENGTH_POTION3, STRENGTH_POTION4);
		add(boost(DEFENCE, perc(.10, 3)), DEFENCE_POTION1, DEFENCE_POTION2, DEFENCE_POTION3, DEFENCE_POTION4);
		add(boost(MAGIC, 4), MAGIC_POTION1, MAGIC_POTION2, MAGIC_POTION3, MAGIC_POTION4);
		add(boost(RANGED, perc(.10, 4)), RANGING_POTION1, RANGING_POTION2, RANGING_POTION3, RANGING_POTION4);
		add(combo(2, boost(ATTACK, perc(.10, 3)), boost(STRENGTH, perc(.10, 3))), COMBAT_POTION1, COMBAT_POTION2, COMBAT_POTION3, COMBAT_POTION4);
		add(boost(ATTACK, perc(.15, 5)), SUPER_ATTACK1, SUPER_ATTACK2, SUPER_ATTACK3, SUPER_ATTACK4);
		add(boost(STRENGTH, perc(.15, 5)), SUPER_STRENGTH1, SUPER_STRENGTH2, SUPER_STRENGTH3, SUPER_STRENGTH4);
		add(boost(DEFENCE, perc(.15, 5)), SUPER_DEFENCE1, SUPER_DEFENCE2, SUPER_DEFENCE3, SUPER_DEFENCE4);
		add(boost(MAGIC, 3), MAGIC_ESSENCE1, MAGIC_ESSENCE2, MAGIC_ESSENCE3, MAGIC_ESSENCE4);
		add(combo(3, boost(ATTACK, perc(.15, 5)), boost(STRENGTH, perc(.15, 5)), boost(DEFENCE, perc(.15, 5))), SUPER_COMBAT_POTION1, SUPER_COMBAT_POTION2, SUPER_COMBAT_POTION3, SUPER_COMBAT_POTION4);
		add(combo(3, boost(ATTACK, perc(.20, 2)), boost(STRENGTH, perc(.12, 2)), heal(PRAYER, perc(.10, 0)), heal(DEFENCE, perc(.10, -2)), new BoostedStatBoost(HITPOINTS, false, perc(-.12, 0))), ZAMORAK_BREW1, ZAMORAK_BREW2, ZAMORAK_BREW3, ZAMORAK_BREW4);
		add(new SaradominBrew(0.15, 0.2, 0.1, 2, 2), SARADOMIN_BREW1, SARADOMIN_BREW2, SARADOMIN_BREW3, SARADOMIN_BREW4);
		add(boost(RANGED, perc(.15, 5)), SUPER_RANGING_1, SUPER_RANGING_2, SUPER_RANGING_3, SUPER_RANGING_4);
		add(boost(MAGIC, perc(.15, 5)), SUPER_MAGIC_POTION_1, SUPER_MAGIC_POTION_2, SUPER_MAGIC_POTION_3, SUPER_MAGIC_POTION_4);
		add(combo(2, boost(RANGED, perc(0.1, 4)), boost(DEFENCE, perc(0.15, 5))), BASTION_POTION1, BASTION_POTION2, BASTION_POTION3, BASTION_POTION4);
		add(combo(2, boost(MAGIC, 4), boost(DEFENCE, perc(0.15, 5))), BATTLEMAGE_POTION1, BATTLEMAGE_POTION2, BATTLEMAGE_POTION3, BATTLEMAGE_POTION4);

		// Regular overload (NMZ)
		add(combo(5, boost(ATTACK, perc(.15, 5)), boost(STRENGTH, perc(.15, 5)), boost(DEFENCE, perc(.15, 5)), boost(RANGED, perc(.15, 5)), boost(MAGIC, perc(.15, 5)), heal(HITPOINTS, -50)), OVERLOAD_1, OVERLOAD_2, OVERLOAD_3, OVERLOAD_4);

		// Bandages (Castle Wars)
		add(new CastleWarsBandage(), BANDAGES);

		// Recovery potions
		add(combo(5, heal(ATTACK, perc(.30, 10)), heal(STRENGTH, perc(.30, 10)), heal(DEFENCE, perc(.30, 10)), heal(RANGED, perc(.30, 10)), heal(MAGIC, perc(.30, 10))), RESTORE_POTION1, RESTORE_POTION2, RESTORE_POTION3, RESTORE_POTION4);
		add(heal(RUN_ENERGY, 10), ENERGY_POTION1, ENERGY_POTION2, ENERGY_POTION3, ENERGY_POTION4);
		add(new PrayerPotion(7), PRAYER_POTION1, PRAYER_POTION2, PRAYER_POTION3, PRAYER_POTION4);
		add(heal(RUN_ENERGY, 20), SUPER_ENERGY1, SUPER_ENERGY2, SUPER_ENERGY3, SUPER_ENERGY4);
		add(new SuperRestore(.25, 8), SUPER_RESTORE1, SUPER_RESTORE2, SUPER_RESTORE3, SUPER_RESTORE4);
		add(new SuperRestore(.25, 9), SANFEW_SERUM1, SANFEW_SERUM2, SANFEW_SERUM3, SANFEW_SERUM4);
		add(heal(RUN_ENERGY, 20), STAMINA_POTION1, STAMINA_POTION2, STAMINA_POTION3, STAMINA_POTION4);

		// Raids potions (+)
		add(combo(5, boost(ATTACK, perc(.16, 6)), boost(STRENGTH, perc(.16, 6)), boost(DEFENCE, perc(.16, 6)), boost(RANGED, perc(.16, 6)), boost(MAGIC, perc(.16, 6)), heal(HITPOINTS, -50)), OVERLOAD_1_20993, OVERLOAD_2_20994, OVERLOAD_3_20995, OVERLOAD_4_20996);
		add(combo(3, boost(ATTACK, perc(.16, 6)), boost(STRENGTH, perc(.16, 6)), boost(DEFENCE, perc(.16, 6))), ELDER_1_20921, ELDER_2_20922, ELDER_3_20923, ELDER_4_20924);
		add(combo(2, boost(RANGED, perc(.16, 6)), boost(DEFENCE, perc(.16, 6))), TWISTED_1_20933, TWISTED_2_20934, TWISTED_3_20935, TWISTED_4_20936);
		add(combo(2, boost(MAGIC, perc(.16, 6)), boost(DEFENCE, perc(.16, 6))), KODAI_1_20945, KODAI_2_20946, KODAI_3_20947, KODAI_4_20948);
		add(new SuperRestore(.30, 11), REVITALISATION_1_20957, REVITALISATION_2_20958, REVITALISATION_3_20959, REVITALISATION_4_20960);
		add(new SaradominBrew(0.15, 0.2, 0.1, 5, 4), XERICS_AID_1_20981, XERICS_AID_2_20982, XERICS_AID_3_20983, XERICS_AID_4_20984);

		// Raids potions
		add(combo(5, boost(ATTACK, perc(.13, 5)), boost(STRENGTH, perc(.13, 5)), boost(DEFENCE, perc(.13, 5)), boost(RANGED, perc(.13, 5)), boost(MAGIC, perc(.13, 5)), heal(HITPOINTS, -50)), OVERLOAD_1_20989, OVERLOAD_2_20990, OVERLOAD_3_20991, OVERLOAD_4_20992);
		add(combo(3, boost(ATTACK, perc(.13, 5)), boost(STRENGTH, perc(.13, 5)), boost(DEFENCE, perc(.13, 5))), ELDER_POTION_1, ELDER_POTION_2, ELDER_POTION_3, ELDER_POTION_4);
		add(combo(2, boost(RANGED, perc(.13, 5)), boost(DEFENCE, perc(.13, 5))), TWISTED_POTION_1, TWISTED_POTION_2, TWISTED_POTION_3, TWISTED_POTION_4);
		add(combo(2, boost(MAGIC, perc(.13, 5)), boost(DEFENCE, perc(.13, 5))), KODAI_POTION_1, KODAI_POTION_2, KODAI_POTION_3, KODAI_POTION_4);

		// Raids potions (-)
		add(combo(5, boost(ATTACK, perc(.10, 4)), boost(STRENGTH, perc(.10, 4)), boost(DEFENCE, perc(.10, 4)), boost(RANGED, perc(.10, 4)), boost(MAGIC, perc(.10, 4)), heal(HITPOINTS, -50)), OVERLOAD_1_20985, OVERLOAD_2_20986, OVERLOAD_3_20987, OVERLOAD_4_20988);
		add(combo(3, boost(ATTACK, perc(.10, 4)), boost(STRENGTH, perc(.10, 4)), boost(DEFENCE, perc(.10, 4))), ELDER_1, ELDER_2, ELDER_3, ELDER_4);
		add(combo(3, boost(RANGED, perc(.10, 4)), boost(DEFENCE, perc(.10, 4))), TWISTED_1, TWISTED_2, TWISTED_3, TWISTED_4);
		add(combo(3, boost(MAGIC, perc(.10, 4)), boost(DEFENCE, perc(.10, 4))), KODAI_1, KODAI_2, KODAI_3, KODAI_4);

		// Skill potions
		add(boost(AGILITY, 3), AGILITY_POTION1, AGILITY_POTION2, AGILITY_POTION3, AGILITY_POTION4);
		add(boost(FISHING, 3), FISHING_POTION1, FISHING_POTION2, FISHING_POTION3, FISHING_POTION4);
		add(boost(HUNTER, 3), HUNTER_POTION1, HUNTER_POTION2, HUNTER_POTION3, HUNTER_POTION4);
		add(combo(2, boost(HITPOINTS, 5), heal(RUN_ENERGY, 5)), GUTHIX_REST1, GUTHIX_REST2, GUTHIX_REST3, GUTHIX_REST4);

		// Misc/run energy
		add(heal(RUN_ENERGY, 10), WHITE_TREE_FRUIT);
		add(heal(RUN_ENERGY, 30), STRANGE_FRUIT);
		add(heal(RUN_ENERGY, 50), MINT_CAKE);
		add(combo(food(12), heal(RUN_ENERGY, 50)), GOUT_TUBER);

		// Pies
		add(combo(2, heal(HITPOINTS, 6), boost(FARMING, 3)), GARDEN_PIE, HALF_A_GARDEN_PIE);
		add(combo(2, heal(HITPOINTS, 6), boost(FISHING, 3)), FISH_PIE, HALF_A_FISH_PIE);
		add(combo(2, heal(HITPOINTS, 7), boost(HERBLORE, 4)), BOTANICAL_PIE, HALF_A_BOTANICAL_PIE);
		add(combo(2, heal(HITPOINTS, 8), boost(CRAFTING, 4)), MUSHROOM_PIE, HALF_A_MUSHROOM_PIE);
		add(combo(2, heal(HITPOINTS, 8), boost(FISHING, 5)), ADMIRAL_PIE, HALF_AN_ADMIRAL_PIE);
		add(combo(2, heal(HITPOINTS, 11), boost(SLAYER, 5), boost(RANGED, 4)), WILD_PIE, HALF_A_WILD_PIE);
		add(combo(2, heal(HITPOINTS, 11), boost(AGILITY, 5), heal(RUN_ENERGY, 10)), SUMMER_PIE, HALF_A_SUMMER_PIE);

		// Other
		add(combo(range(food(1), food(3)), heal(RUN_ENERGY, 10)), PURPLE_SWEETS_10476);
		add(new SpicyStew(), SPICY_STEW);
		add(boost(MAGIC, perc(.10, 1)), IMBUED_HEART);
		add(combo(boost(ATTACK, 2), boost(STRENGTH, 1), heal(DEFENCE, -1)), JANGERBERRIES);

		log.debug("{} items; {} behaviours loaded", effects.size(), new HashSet<>(effects.values()).size());
	}

	private final Map<Integer, Effect> effects = new HashMap<>();

	private void add(Effect effect, int... items)
	{
		assert items.length > 0;
		for (int item : items)
		{
			Effect prev = effects.put(item, effect);
			assert prev == null : "Item already added";
		}
	}

	public Effect get(int id)
	{
		return effects.get(id);
	}
}
