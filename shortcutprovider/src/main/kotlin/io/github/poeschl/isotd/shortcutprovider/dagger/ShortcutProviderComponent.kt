package io.github.poeschl.isotd.shortcutprovider.dagger

import dagger.Component
import dagger.Module
import dagger.Provides
import io.github.poeschl.isotd.shortcutprovider.ShortcutParser
import io.github.poeschl.isotd.shortcutprovider.ShortcutProvider
import org.apache.commons.csv.CSVFormat
import javax.inject.Named
import javax.inject.Singleton
import kotlin.random.Random


@Singleton
@Component(
    modules = [ProviderModule::class]
)
interface ShortcutProviderComponent {

    companion object {
        fun init(): ShortcutProviderComponent {
            return DaggerShortcutProviderComponent.builder()
                .providerModule(ProviderModule())
                .build()
        }
    }

    var shortcutProvider: ShortcutProvider
}

@Module
internal class ProviderModule {

    @Provides
    @Singleton
    fun provideCsvParser() = CSVFormat.DEFAULT.withRecordSeparator('\n').withHeader()

    @Provides
    @Singleton
    @Named("keymap")
    fun provideKeyMapCsvPath() = ProviderModule::class.java.classLoader.getResource("idea-shortcuts.csv").path

    @Provides
    @Singleton
    fun provideShortcutParser(csvFormat: CSVFormat) = ShortcutParser(csvFormat)

    @Provides
    @Singleton
    fun provideRandom(): Random = Random
}
