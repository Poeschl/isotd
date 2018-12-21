package io.github.poeschl.isotd.shortcutprovider

import io.github.poeschl.isotd.shortcutprovider.models.Shortcut
import org.apache.commons.csv.CSVFormat
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File

internal class ShortCutParser {

    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(ShortCutParser::class.java)
    }

    internal fun parse(csvFile: File): List<Shortcut> {

        val format = CSVFormat.DEFAULT.withRecordSeparator('\n')
            .withHeader()
        val records = format.parse(csvFile.bufferedReader()).records
        val shortcuts = records.map { Shortcut(it[0], it[1], it[2]) }

        LOGGER.info("Parsed ${shortcuts.size} records")

        return shortcuts
    }
}
