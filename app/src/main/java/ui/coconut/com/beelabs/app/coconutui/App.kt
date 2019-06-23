package ui.coconut.com.beelabs.app.coconutui

import app.beelabs.com.codebase.base.BaseApp
import app.beelabs.com.codebase.di.component.AppComponent
import app.beelabs.com.codebase.di.component.DaggerAppComponent

class App : BaseApp() {

    override fun onCreate() {
        super.onCreate()
        BaseApp.setupBuilder(DaggerAppComponent.builder(), this)
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    companion object {

        val appComponent: AppComponent
            get() = BaseApp.getComponent()
    }
}