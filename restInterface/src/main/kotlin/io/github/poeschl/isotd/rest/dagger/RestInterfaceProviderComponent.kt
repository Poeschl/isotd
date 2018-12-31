package io.github.poeschl.isotd.rest.dagger

import com.google.gson.GsonBuilder
import dagger.Component
import dagger.Module
import dagger.Provides
import io.github.poeschl.isotd.rest.ShortcutController
import io.github.poeschl.isotd.shortcutprovider.dagger.ShortcutProviderComponent
import javax.inject.Singleton


@Singleton
@Component(
    modules = [ControllerModule::class]
)
interface RestInterfaceProviderComponent {

    companion object {
        fun init(): RestInterfaceProviderComponent {
            return DaggerRestInterfaceProviderComponent.builder()
                .controllerModule(ControllerModule())
                .build()
        }
    }

    var shortcutController: ShortcutController
}

@Module
internal class ControllerModule {

    @Provides
    @Singleton
    fun provideShortcutProvider(shortcutProviderComponent: ShortcutProviderComponent) =
        shortcutProviderComponent.shortcutProvider

    @Provides
    @Singleton
    fun provideShortcutProviderSystem() = ShortcutProviderComponent.init()

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().create()

}
