package io.github.poeschl.isotd.rest

import com.google.gson.Gson
import io.github.poeschl.isotd.shortcutprovider.ShortcutProvider
import io.github.poeschl.isotd.shortcutprovider.models.Shortcut
import io.ktor.application.ApplicationCall
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class ShortcutControllerTest {

    @Test
    internal fun provideRandomShortcut() = runBlocking {
        //WHEN
        mockkStatic("io.ktor.response.ApplicationResponseFunctionsKt")

        val mockShortcutProvider: ShortcutProvider = mockk()
        val gson = Gson()
        val mockCall: ApplicationCall = mockk(relaxed = true)
        val shortcut = Shortcut("1", "2", "1 2 3")
        val expectedJson = "{\"section\":\"1\",\"description\":\"2\",\"keys\":\"1 2 3\"}"

        val testController = ShortcutController(mockShortcutProvider, gson)

        every { mockShortcutProvider.getRandomShortcut() } returns shortcut

        //THEN
        testController.provideRandomShortcut(mockCall)

        //VERIFY
        coVerify {
            mockCall.respondText(
                text = expectedJson,
                contentType = ContentType.Application.Json
            )
        }
    }
}
