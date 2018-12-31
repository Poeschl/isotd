package io.github.poeschl.isotd.rest

import com.google.gson.Gson
import io.github.poeschl.isotd.shortcutprovider.ShortcutProvider
import io.ktor.application.ApplicationCall
import io.ktor.http.ContentType
import io.ktor.response.respondText
import javax.inject.Inject

class ShortcutController @Inject constructor(
    private val shortcutProvider: ShortcutProvider,
    private val gson: Gson
) {

    suspend fun provideRandomShortcut(call: ApplicationCall) {
        val randomShortcut = shortcutProvider.getRandomShortcut()
        val jsonResponse = gson.toJson(randomShortcut)

        call.respondText(jsonResponse, ContentType.Application.Json)
    }
}
