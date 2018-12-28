package io.github.poeschl.isotd.cli

import io.github.poeschl.isotd.shortcutprovider.ShortcutProvider
import io.github.poeschl.isotd.shortcutprovider.dagger.ShortcutProviderComponent

fun main(args: Array<String>) {
    Application().run()
}

class Application {

    fun run() {
        val randomShortcut = getShortCutProvider().getRandomShortcut()

        println(
            "Random Shortcut from section ${randomShortcut.section}: \n" +
                    "\t${randomShortcut.description}: ${randomShortcut.keys}"
        )
    }


    private fun getShortCutProvider(): ShortcutProvider {
        val component = ShortcutProviderComponent.init()
        return component.shortcutProvider
    }
}
