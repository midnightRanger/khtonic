package khtonic.khtonic.Entity.monsters;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.checkerframework.checker.units.qual.A;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class SoulEaterEntity extends Monster implements IAnimatable {

    protected static final AnimationBuilder IDLE_ANIM = new AnimationBuilder().addAnimation("animation.souleater.idle", ILoopType.EDefaultLoopTypes.LOOP);
    protected static final AnimationBuilder ATTACK_ANIM = new AnimationBuilder().addAnimation("animation.souleater.fight", ILoopType.EDefaultLoopTypes.PLAY_ONCE);


    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public SoulEaterEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier setAttributes()
    {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.00)
                .add(Attributes.ATTACK_DAMAGE, 3.0f)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(Attributes.ATTACK_KNOCKBACK, 1.0f)
                .add(Attributes.KNOCKBACK_RESISTANCE, 100f)
                .add(Attributes.MOVEMENT_SPEED, 0.3f).build();
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 2.0F, true));
        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)).setAlertOthers());
        this.goalSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));

    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {

        event.getController().setAnimation(IDLE_ANIM);

        return PlayState.CONTINUE;
    }

    private <E extends IAnimatable> PlayState attackPredicate(AnimationEvent<E> event) {

        if (this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(ATTACK_ANIM);
            this.swinging = false;
        }


        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
        data.addAnimationController(new AnimationController(this, "attackController", 0, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.SLIME_BLOCK_STEP);
    }

    protected SoundEvent getAmbientSound() { return SoundEvents.AMBIENT_NETHER_WASTES_LOOP; }

    protected SoundEvent getHurtSound(DamageSource source) { return SoundEvents.GHAST_HURT; }

    protected SoundEvent getDeathSound() { return SoundEvents.GHAST_DEATH; }

    protected float getSoundVolume() { return 0.2f; }
}
