package io.github.poeschl.isotd.shortcutprovider

import io.github.poeschl.isotd.shortcutprovider.models.Shortcut
import org.apache.commons.csv.CSVFormat
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.StringReader

internal class ShortcutParserTest {

    private val format = CSVFormat.DEFAULT.withRecordSeparator('\n')

    private val shortcutParser = ShortcutParser(format)

    @Test
    internal fun parse() {
        //WHEN
        val testReader = StringReader("\"Group1\",\"Info\",\"Ctrl + 1\"\n\"Group2\",\"Info\",\"Ctrl + 2\"")

        //THEN
        val shortcuts = shortcutParser.parse(testReader)

        //VERIFY
        assertThat(shortcuts).containsExactly(
            Shortcut("Group1", "Info", "Ctrl + 1"),
            Shortcut("Group2", "Info", "Ctrl + 2")
        )
    }
}
