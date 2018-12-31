package io.github.poeschl.isotd.shortcutprovider

import io.github.poeschl.isotd.shortcutprovider.dagger.ProviderModule
import io.github.poeschl.isotd.shortcutprovider.models.Shortcut
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class ShortcutProviderTest {

    @Test
    fun getRandomShortcut() {
        //WHEN
        val mockShortCutParser: ShortcutParser = mockk()
        val shortCutList = listOf(Shortcut("test", "testinfo", "A"))
        val path = ProviderModule::class.java.classLoader.getResource("idea-shortcuts.csv")
        val mockRandom: Random = mockk()

        every { mockShortCutParser.parse(any()) } returns shortCutList
        every { mockRandom.nextInt(1) } returns 0

        val provider = ShortcutProvider(mockShortCutParser, path, mockRandom)

        //THEN
        val shortcut = provider.getRandomShortcut()

        //VERIFY
        assertThat(shortcut).isEqualTo(shortCutList[0])
    }
}
