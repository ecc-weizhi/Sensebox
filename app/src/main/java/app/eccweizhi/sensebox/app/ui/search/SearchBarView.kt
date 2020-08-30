package app.eccweizhi.sensebox.app.ui.search

import android.animation.ObjectAnimator
import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.View.OnFocusChangeListener
import android.widget.*
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import app.eccweizhi.sensebox.app.R
import app.eccweizhi.sensebox.appcore.ext.hideKeyboard
import app.eccweizhi.sensebox.appcore.ext.showKeyboard

class SearchBarView : FrameLayout {

    private lateinit var navigationIconView: ImageButton
    private lateinit var searchViewSwitcher: ViewSwitcher
    private lateinit var searchTextView: TextView
    private lateinit var searchEditText: EditText

    private lateinit var hamburgerDrawable: DrawerArrowDrawable
    private var listener: Listener? = null

    private val clickListener = OnClickListener { v ->
        when (v.id) {
            R.id.navigationIconView -> listener?.onNavigationIconClick()
            R.id.searchViewSwitcher -> if (!isSearchBoxEditable) {
                listener?.onSearchBoxClick()
            }
        }
    }
    private val focusChangeListener = OnFocusChangeListener { v, hasFocus ->
        if (hasFocus) {
            v.showKeyboard()
        } else {
            v.hideKeyboard()
        }
    }

    var isSearchBoxEditable: Boolean
        get() = searchViewSwitcher.displayedChild == INDEX_SEARCH_EDIT_VIEW
        set(value) {
            if (isSearchBoxEditable != value) {
                // Original state is different
                searchViewSwitcher.showNext()
            }

            if (value) {
                searchEditText.requestFocus()
            }
        }

    var searchText: CharSequence?
        get() = searchEditText.text
        set(text) = searchEditText.setText(text)

    constructor(context: Context) : super(context) {
        init(context, null, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs, defStyleAttr, 0)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs, defStyleAttr, defStyleRes)
    }

    private fun init(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) {
        LayoutInflater.from(context).inflate(R.layout.layout_toolbar, this, true)

        navigationIconView = findViewById(R.id.navigationIconView)
        searchViewSwitcher = findViewById(R.id.searchViewSwitcher)
        searchTextView = findViewById(R.id.searchTextView)
        searchEditText = findViewById(R.id.searchEditText)

        hamburgerDrawable = DrawerArrowDrawable(context)
        navigationIconView.setImageDrawable(hamburgerDrawable)

        navigationIconView.setOnClickListener(clickListener)
        searchViewSwitcher.setOnClickListener(clickListener)
        searchEditText.onFocusChangeListener = focusChangeListener
    }

    fun addTextChangedListener(textWatcher: TextWatcher) {
        searchEditText.addTextChangedListener(textWatcher)
    }

    fun removeTextChangedListener(textWatcher: TextWatcher) {
        searchEditText.removeTextChangedListener(textWatcher)
    }

    /**
     * @param progress to set to between 0f and 1f. 0f is hamburger, 1f is back icon.
     * @param animate
     */
    fun setHamburgerProgress(progress: Float, animate: Boolean) {
        if (animate) {
            val animator = ObjectAnimator.ofFloat(hamburgerDrawable, "progress", progress)
            animator.duration = ANIMATION_DURATION_MS.toLong()
            animator.setAutoCancel(true)
            animator.start()
        } else {
            hamburgerDrawable.progress = progress
        }
    }

    fun setListener(listener: Listener?) {
        this.listener = listener
    }

    interface Listener {
        fun onNavigationIconClick()

        fun onSearchBoxClick()
    }

    companion object {
        private val ANIMATION_DURATION_MS = 300
        private val INDEX_SEARCH_TEXT_VIEW = 0
        private val INDEX_SEARCH_EDIT_VIEW = 1
    }
}
