package io.github.poeschl.isotd.shortcutprovider

import io.github.poeschl.isotd.shortcutprovider.dagger.ShortcutProviderComponent
import io.github.poeschl.isotd.shortcutprovider.models.Shortcut
import java.net.URL
import javax.inject.Inject
import javax.inject.Named
import kotlin.random.Random

fun main(args: Array<String>) {
    val component = ShortcutProviderComponent.init()
    val shortcutProvider = component.shortcutProvider

    println(shortcutProvider.getRandomShortcut())

}

class ShortcutProvider @Inject constructor(
    private val parser: ShortcutParser,
    @Named("keymap") private val keyMapURL: URL,
    private val random: Random
) {

    fun getRandomShortcut(): Shortcut {
        val shortcutList = parser.parse(keyMapURL.openStream().bufferedReader())
        val randomIndex = random.nextInt(shortcutList.size)

        return shortcutList[randomIndex]
    }
}
