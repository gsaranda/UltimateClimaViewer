package mx.com.lider.awesomeanimator

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator

/**
 * Created by gabriel on 19/04/18.
 */

 class SimpleAlfaAnimation {

    //Clase para hacer animaciones de transparencia mas facilmente
    constructor(v: View, targetAlpha: Float, duration: Long) {
        val firstalpha = (targetAlpha - 1) * -1
        val finalVisibility=if(targetAlpha>=1.0f)View.VISIBLE else View.GONE
        val objectAnimator = ObjectAnimator.ofFloat(v, "Alpha", firstalpha, targetAlpha)
        objectAnimator.duration = duration
        objectAnimator.addListener(object :Animator.AnimatorListener{
            override fun onAnimationRepeat(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                if(targetAlpha==0.0f) v.visibility=View.GONE
                 }

            override fun onAnimationCancel(p0: Animator?) {}

            override fun onAnimationStart(p0: Animator?) { if(targetAlpha==1.0f) v.visibility=View.VISIBLE}
        })

        objectAnimator.start()
    }

    constructor(v: View, targetAlpha: Float, duration: Long, Listener: Animator.AnimatorListener) {
        val firstalpha = (targetAlpha - 1) * -1
        val objectAnimator = ObjectAnimator.ofFloat(v, "Alpha", firstalpha, targetAlpha)
        objectAnimator.duration = duration
        objectAnimator.addListener(Listener)
        objectAnimator.start()
    }

}