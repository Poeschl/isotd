package io.github.poeschl.isotd.shortcutprovider

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.github.poeschl.isotd.shortcutprovider.dagger.ProviderModule
import io.github.poeschl.isotd.shortcutprovider.models.Shortcut
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class ShortcutProviderTest {

    @Test
    fun getRandomShortcut() {
        //WHEN
        val mockShortCutParser: ShortcutParser = mock()
        val shortCutList = listOf(Shortcut("test", "testinfo", "A"))
        val path = ProviderModule::class.java.classLoader.getResource("idea-shortcuts.csv").path
        val mockRandom: Random = mock()

        whenever(mockShortCutParser.parse(any())).thenReturn(shortCutList)
        whenever(mockRandom.nextInt(1)).thenReturn(0)

        val provider = ShortcutProvider(mockShortCutParser, path, mockRandom)

        //THEN
        val shortcut = provider.getRandomShortcut()

        //VERIFY
        assertThat(shortcut).isEqualTo(shortCutList[0])
    }
}
