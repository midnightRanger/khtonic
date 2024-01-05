package khtonic.khtonic.Entity;

import khtonic.khtonic.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public class CosmicCreatureEntity extends Animal {
    public short rotationFlipper;
    private boolean moveFlipper = false;

    public CosmicCreatureEntity(EntityType<? extends CosmicCreatureEntity> cosmic, Level level) {
        super(cosmic, level);
        this.maxUpStep = 1.0F;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new PanicGoal(this, 1.5D));
        this.goalSelector.addGoal(3, new BreedGoal(this, 0.8D));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, PolarBear.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(6, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(7, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.MOVEMENT_SPEED, 0.16D);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.level.isClientSide) {
            if (this.getX() != this.zo) {
                if (this.moveFlipper) {
                    this.rotationFlipper++;
                }
            }
        }
    }


    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public AgeableMob getBreedOffspring(@Nonnull ServerLevel serverLevel, @Nonnull AgeableMob ageableMob) {
        return EntityInit.COSMIC_CREATURE.get().create(this.level);
    }



    @Override
    protected float getStandingEyeHeight(@Nonnull Pose pose, @Nonnull EntityDimensions entityDimensions) {
        return this.isBaby() ? 0.5F : 0.9F;
    }
}
