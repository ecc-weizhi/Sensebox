package app.eccweizhi.sensebox.app.ui

import android.view.View
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import app.eccweizhi.sensebox.app.R
import app.eccweizhi.sensebox.app.ui.navigation.NavigationItemView
import app.eccweizhi.sensebox.app.ui.search.SearchBarView

class DrawerAndSearchCoordinator(
    private val drawerLayout: DrawerLayout
) {
    private var searchBarView: SearchBarView? = null
    private val homeNavigationItemView: NavigationItemView =
        drawerLayout.findViewById(R.id.homeNavItemView)
    private val settingsNavigationItemView: NavigationItemView =
        drawerLayout.findViewById(R.id.settingsNavItemView)
    private val aboutNavigationItemView: NavigationItemView =
        drawerLayout.findViewById(R.id.aboutNavItemView)
    private val searchBarViewListener: SearchBarView.Listener = object : SearchBarView.Listener {
        override fun onNavigationIconClick() {
            onClickNavigationIconView()
        }

        override fun onSearchBoxClick() {
            searchBarView?.isSearchBoxEditable = true
            searchBarView?.setHamburgerProgress(1f, true)
        }
    }

    init {
        drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerStateChanged(newState: Int) {

            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                searchBarView?.let {
                    if (!it.isSearchBoxEditable) {
                        it.setHamburgerProgress(slideOffset, false)
                    }
                }
            }

            override fun onDrawerClosed(drawerView: View) {

            }

            override fun onDrawerOpened(drawerView: View) {
            }
        })
    }

    fun attachSearchBarView(searchBarView: SearchBarView) {
        this.searchBarView = searchBarView
        searchBarView.setListener(searchBarViewListener)
    }

    fun detachSearchBarView() {
        searchBarView?.setListener(null)
        searchBarView = null
    }

    private fun onClickNavigationIconView() {
        searchBarView?.let {
            val isBackIcon =
                it.isSearchBoxEditable || drawerLayout.isDrawerOpen(GravityCompat.START)
            if (isBackIcon) {
                // currently showing back arrow
                it.setHamburgerProgress(0f, true)
                it.isSearchBoxEditable = false
                it.searchText = null
            } else {
                drawerLayout.openDrawer(GravityCompat.START, true)
            }
        }
    }

    fun lockCloseNavigationDrawer(lockClose: Boolean) {
        if (lockClose) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        } else {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        }
    }

    fun syncNavigationIcon() {
        // Sync up hamburger icon and drawer state
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            searchBarView?.setHamburgerProgress(1f, false)
        } else {
            searchBarView?.setHamburgerProgress(0f, false)
        }
    }

    private fun resetNavigationItemSelection() {
        homeNavigationItemView.navStateSelected = false
        settingsNavigationItemView.navStateSelected = false
        aboutNavigationItemView.navStateSelected = false
    }

    fun onNavigate(destinationId: Int) {
        when (destinationId) {
            R.id.homeFragment -> {
                lockCloseNavigationDrawer(false)
                resetNavigationItemSelection()
                homeNavigationItemView.navStateSelected = true
            }
            R.id.aboutFragment -> {
                lockCloseNavigationDrawer(true)
                resetNavigationItemSelection()
                aboutNavigationItemView.navStateSelected = true
            }
            R.id.settingsFragment -> {
                lockCloseNavigationDrawer(true)
                resetNavigationItemSelection()
                settingsNavigationItemView.navStateSelected = true
            }
            else -> {
                lockCloseNavigationDrawer(true)
                homeNavigationItemView.navStateSelected = true
            }
        }
    }
}