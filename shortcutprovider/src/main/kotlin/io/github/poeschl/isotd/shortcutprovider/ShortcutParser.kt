package io.github.poeschl.isotd.shortcutprovider

import io.github.poeschl.isotd.shortcutprovider.models.Shortcut
import org.apache.commons.csv.CSVFormat
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.Reader

class ShortcutParser constructor(private val csvFormat: CSVFormat) {

    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(ShortcutParser::class.java)
    }

    internal fun parse(reader: Reader): List<Shortcut> {

        val records = csvFormat.parse(reader).records
        val shortcuts = records.map { Shortcut(it[0], it[1], it[2]) }

        LOGGER.debug("Parsed ${shortcuts.size} records")

        return shortcuts
    }
}
