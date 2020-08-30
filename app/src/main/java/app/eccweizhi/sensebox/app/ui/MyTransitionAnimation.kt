package app.eccweizhi.sensebox.app.ui

import android.animation.Animator
import android.animation.ValueAnimator
import android.view.ViewGroup
import androidx.transition.Transition
import androidx.transition.TransitionValues
import app.eccweizhi.sensebox.appcore.UiUtil

class MyTransitionAnimation : Transition() {
    // Define a key for storing a property value in
    // TransitionValues.values with the syntax
    // package_name:transition_class:property_name to avoid collisions
    private val PROPNAME_WIDTH = "app.eccweizhi.sensebox.app.ui:MyTransitionAnimation:width"


    override fun captureStartValues(transitionValues: TransitionValues) {
        val view = transitionValues.view
        transitionValues.values[PROPNAME_WIDTH] = view.width - UiUtil.dpToPx(view.context, 32)
    }

    override fun captureEndValues(transitionValues: TransitionValues) {
        captureValues(transitionValues)
    }

    override fun createAnimator(
        sceneRoot: ViewGroup,
        startValues: TransitionValues?,
        endValues: TransitionValues?
    ): Animator? {
        if (startValues == null || endValues == null) {
            return null
        }

        val startWidth: Int = startValues.values[PROPNAME_WIDTH] as Int
        val endWidth: Int = endValues.values[PROPNAME_WIDTH] as Int

        val view = endValues.view

        val animator: ValueAnimator = ValueAnimator.ofInt(startWidth, endWidth)
        animator.addUpdateListener {
            val params = view.layoutParams
            params.width = it.animatedValue as Int
            view.layoutParams = params
        }

        return animator
    }

    // For the view in transitionValues.view, get the values you
    // want and put them in transitionValues.values
    private fun captureValues(transitionValues: TransitionValues) {
        // Get a reference to the view
        val view = transitionValues.view
        // Store its background property in the values map
        transitionValues.values[PROPNAME_WIDTH] = view.width
    }
}