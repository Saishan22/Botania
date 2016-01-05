package vazkii.botania.client.core.handler;

import com.google.common.collect.Maps;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.text.WordUtils;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.client.model.FloatingFlowerModel;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.integration.buildcraft.TriggerManaLevel;
import vazkii.botania.common.item.ItemSparkUpgrade;
import vazkii.botania.common.item.relic.ItemKingKey;

import java.util.Map;

public class RenderEventHandler {

    public static final RenderEventHandler INSTANCE = new RenderEventHandler();

    public TextureAtlasSprite
        alfPortalTex,
        lightRelayWorldIcon,
        lightRelayWorldIconRed,
        alchemyCatalystOverlay,
        enchanterOverlay,
        manaVoidOverlay,
        manaWater,
        terraPlateOverlay,
        corporeaWorldIcon,
        corporeaWorldIconMaster,
        corporeaIconStar,
        sparkWorldIcon,
        manaDetectorIcon,
        runeAltarTriggerIcon;

    public TextureAtlasSprite[] sparkUpgradeIcons;
    public TextureAtlasSprite[] kingKeyWeaponIcons;
    public Map<TriggerManaLevel.State, TextureAtlasSprite> manaLevelTriggerIcons = Maps.newEnumMap(TriggerManaLevel.State.class);

    // begin dank_memes todo 1.8 if I can figure out how to render arbitrary baked models then these will no longer be needed
    public TextureAtlasSprite tailIcon = null;
    public TextureAtlasSprite phiFlowerIcon = null;
    public TextureAtlasSprite goldfishIcon = null;
    public TextureAtlasSprite nerfBatIcon = null;
    // end dank_memes

    @SubscribeEvent
    public void onModelBake(ModelBakeEvent evt) {
        // BlockSpecialFlower
        // Ignore all vanilla rules, redirect all blockstates to blockstates/specialFlower.json#normal
        evt.modelManager.getBlockModelShapes().registerBlockWithStateMapper(ModBlocks.specialFlower, new DefaultStateMapper() {
            @Override
            public ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return new ModelResourceLocation("botania:specialFlower");
            }
        });

        // Floating flowers
        evt.modelRegistry.putObject(new ModelResourceLocation("botania:miniIsland", "normal"), FloatingFlowerModel.INSTANCE);
        evt.modelRegistry.putObject(new ModelResourceLocation("botania:miniIsland", "inventory"), FloatingFlowerModel.INSTANCE);
        evt.modelRegistry.putObject(new ModelResourceLocation("botania:floatingSpecialFlower", "normal"), FloatingFlowerModel.INSTANCE);
        evt.modelRegistry.putObject(new ModelResourceLocation("botania:floatingSpecialFlower", "inventory"), FloatingFlowerModel.INSTANCE);
    }

    @SubscribeEvent
    public void onTextureStitch(TextureStitchEvent.Pre evt) {
        System.out.println("CALLING TEXTURE STITCH");
        alfPortalTex = IconHelper.forName(evt.map, "alfheimPortalInside", "blocks");
        lightRelayWorldIcon = IconHelper.forName(evt.map, "lightRelay1", "blocks");
        lightRelayWorldIconRed = IconHelper.forName(evt.map, "lightRelay3", "blocks");
        alchemyCatalystOverlay = IconHelper.forName(evt.map, "alchemyCatalyst3", "blocks");
        enchanterOverlay = IconHelper.forName(evt.map, "enchanterOverlay", "blocks");
        manaVoidOverlay = IconHelper.forName(evt.map, "manaVoid1", "blocks");
        manaWater = IconHelper.forName(evt.map, "manaWater", "blocks");
        terraPlateOverlay = IconHelper.forName(evt.map, "terraPlateOverlay", "blocks");
        corporeaWorldIcon = IconHelper.forName(evt.map, "corporeaSpark1", "items");
        corporeaWorldIconMaster = IconHelper.forName(evt.map, "corporeaSpark3", "items");
        corporeaIconStar = IconHelper.forName(evt.map, "corporeaSparkStar", "items");
        sparkWorldIcon = IconHelper.forName(evt.map, "spark1", "items");

        sparkUpgradeIcons = new TextureAtlasSprite[ItemSparkUpgrade.VARIANTS];
        for(int i = 0; i < ItemSparkUpgrade.VARIANTS; i++) {
            sparkUpgradeIcons[i] = IconHelper.forName(evt.map, "sparkUpgradeL" + i, "items");
        }

        tailIcon = IconHelper.forName(evt.map, "tail", "items");
        phiFlowerIcon = IconHelper.forName(evt.map, "phiFlower", "items");
        goldfishIcon = IconHelper.forName(evt.map, "goldfish", "items");
        nerfBatIcon = IconHelper.forName(evt.map, "nerfBat", "items");

        kingKeyWeaponIcons = new TextureAtlasSprite[ItemKingKey.WEAPON_TYPES];
        for(int i = 0; i < ItemKingKey.WEAPON_TYPES; i++)
            kingKeyWeaponIcons[i] = IconHelper.forName(evt.map, "gateWeapon" + i, "items");

        manaDetectorIcon = IconHelper.forName(evt.map, "triggers/manaDetector", "items");
        runeAltarTriggerIcon = IconHelper.forName(evt.map, "triggers/runeAltarCanCraft", "items");

        for (TriggerManaLevel.State s : TriggerManaLevel.State.values()) {
            manaLevelTriggerIcons.put(s, IconHelper.forName(evt.map, "triggers/mana" + WordUtils.capitalizeFully(s.name()), "items"));
        }
    }

    private RenderEventHandler() {}
}