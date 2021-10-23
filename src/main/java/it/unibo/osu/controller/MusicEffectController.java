package it.unibo.osu.controller;

import it.unibo.osu.model.GamePoints;

/**
 * The Class MusicEffectController.
 */
public class MusicEffectController implements HitActionObserver {

    private MusicControllerImpl hitEffect;

    private MusicControllerImpl missEffect;

    /**
     * Instantiates a new music effect controller.
     *
     * @param hitEffect  the hit effect
     * @param missEffect the miss effect
     */
    public MusicEffectController(final String hitEffect,
            final String missEffect) {
        this.hitEffect = MusicControllerImplFactory.getEffectImpl(hitEffect);
        this.missEffect = MusicControllerImplFactory.getEffectImpl(missEffect);
    }

    /**
     * On notify.
     *
     * @param points the points
     */
    @Override
    public void onNotify(final GamePoints points) {
        if (points.equals(GamePoints.MISS)) {
            missEffect.onNotify();
        } else {
            hitEffect.onNotify();
        }
    }
}
