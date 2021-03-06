package mod.teamdraco.frozenup.item;

import mod.teamdraco.frozenup.init.FrozenUpItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DrinkHelper;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class MilkMugItem extends Item {
   public MilkMugItem(Item.Properties builder) {
      super(builder);
   }

   /**
    * Called when the player finishes using this Item (E.g. finishes eating.). Not called when the player stops using
    * the Item before the action is complete.
    */
   public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
	      if (!worldIn.isRemote) entityLiving.curePotionEffects(stack); // FORGE - move up so stack.shrink does not turn stack into air
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.BLINDNESS);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.ABSORPTION);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.BAD_OMEN);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.CONDUIT_POWER);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.DOLPHINS_GRACE);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.FIRE_RESISTANCE);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.GLOWING);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.HASTE);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.HEALTH_BOOST);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.HERO_OF_THE_VILLAGE);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.HUNGER);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.INSTANT_DAMAGE);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.INSTANT_HEALTH);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.INVISIBILITY);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.JUMP_BOOST);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.LEVITATION);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.LUCK);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.MINING_FATIGUE);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.NAUSEA);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.NIGHT_VISION);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.POISON);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.REGENERATION);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.RESISTANCE);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.SATURATION);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.SLOW_FALLING);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.SLOWNESS);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.SPEED);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.STRENGTH);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.UNLUCK);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.WATER_BREATHING);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.WEAKNESS);
	      if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.WITHER);
	      

	      if (entityLiving instanceof ServerPlayerEntity) {
	         ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)entityLiving;
	         CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
	         serverplayerentity.addStat(Stats.ITEM_USED.get(this));
	      }

	      if (entityLiving instanceof PlayerEntity && !((PlayerEntity)entityLiving).abilities.isCreativeMode) {
	         stack.shrink(1);
	      }

	      return stack.isEmpty() ? new ItemStack(FrozenUpItems.EMPTY_MUG.get()) : stack;
	   }

   /**
    * How long it takes to use or consume an item
    */
   public int getUseDuration(ItemStack stack) {
      return 32;
   }

   /**
    * returns the action that specifies what animation to play when the items is being used
    */
   public UseAction getUseAction(ItemStack stack) {
      return UseAction.DRINK;
   }

   /**
    * Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see
    * {@link #onItemUse}.
    */
   public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
      return DrinkHelper.startDrinking(worldIn, playerIn, handIn);
   }

   @Override
   public net.minecraftforge.common.capabilities.ICapabilityProvider initCapabilities(ItemStack stack, @javax.annotation.Nullable net.minecraft.nbt.CompoundNBT nbt) {
      return new net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper(stack);
   }
}
