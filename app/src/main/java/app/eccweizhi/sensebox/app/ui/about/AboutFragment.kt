package app.eccweizhi.sensebox.app.ui.about

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.eccweizhi.sensebox.app.R

class AboutFragment : Fragment() {

    private var listener: FragmentListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
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
        fun newInstance(): AboutFragment = AboutFragment()
    }

    interface FragmentListener {

    }
}