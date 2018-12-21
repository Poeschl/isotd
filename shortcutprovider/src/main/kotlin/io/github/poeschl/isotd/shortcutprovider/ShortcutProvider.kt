package io.github.poeschl.isotd.shortcutprovider

import io.github.poeschl.isotd.shortcutprovider.models.Shortcut
import java.io.File
import kotlin.random.Random

fun main(args: Array<String>) {
    println(ShortcutProvider.getRandomShortcut())
}

object ShortcutProvider {

    private val csvFile = ShortcutProvider::class.java.classLoader.getResource("idea-shortcuts.csv")
    private val csvParser = ShortCutParser()

    fun getRandomShortcut(): Shortcut {
        val shortcutList = csvParser.parse(File(csvFile.path))
        val randomIndex = Random.nextInt(shortcutList.size)

        return shortcutList[randomIndex]
    }
}
