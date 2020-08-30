package app.eccweizhi.sensebox.app.ui.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.eccweizhi.sensebox.app.R
import app.eccweizhi.sensebox.app.ui.MyTransitionAnimation
import app.eccweizhi.sensebox.appcore.ext.doOnApplyWindowInsets
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {

    private var listener: FragmentListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            MyTransitionAnimation()//AutoTransition()//TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        toolbar.doOnApplyWindowInsets { v, windowInsets, initialPadding ->
            // initialPadding contains the original padding values after inflation
            v.setPadding(
                v.paddingLeft,
                initialPadding.top + windowInsets.systemWindowInsetTop,
                v.paddingRight,
                v.paddingBottom
            )
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as FragmentListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement FragmentListener")
        }
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    companion object {
        @JvmStatic
        fun newInstance(): SettingsFragment = SettingsFragment()
    }

    interface FragmentListener {

    }
}