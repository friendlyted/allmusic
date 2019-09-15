package pro.friendlyted.ears.alice.fileservice

import com.google.gson.Gson
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpDelete
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpUriRequest
import org.apache.http.entity.mime.MultipartEntityBuilder
import org.apache.http.impl.client.HttpClientBuilder
import java.io.ByteArrayOutputStream
import java.io.File

class AliceFileService {

    var skillId: String? = null
    var oAuthToken: String? = null

    private val soundsUrl = { "https://dialogs.yandex.net/api/v1/skills/$skillId/sounds" }
    private val statusUrl = "https://dialogs.yandex.net/api/v1/status"
    private val soundUrl = { soundId: String -> "https://dialogs.yandex.net/api/v1/skills/$skillId/sounds/$soundId" }
    private val auth = { "OAuth $oAuthToken" }
    private val GSON = Gson()

    fun uploadSound(file: File): UploadSoundResponse {
        val request = HttpPost(soundsUrl())
        request.entity = MultipartEntityBuilder.create()
                .addBinaryBody("file", file)
                .build()

        return request(request, 201, UploadSoundResponse::class.java)
    }

    fun status(): StatusResponse {
        return request(HttpGet(statusUrl), 200, StatusResponse::class.java)
    }

    fun listSounds(): ListSoundsResponse {
        return request(HttpGet(soundsUrl()), 200, ListSoundsResponse::class.java)
    }

    fun getSound(id: String): GetSoundResponse {
        return request(HttpGet(soundUrl(id)), 200, GetSoundResponse::class.java)
    }

    fun deleteSound(id: String): DeleteResponse {
        return request(HttpDelete(soundUrl(id)), 200, DeleteResponse::class.java)
    }

    private fun <T> request(request: HttpUriRequest, successCode: Int, type: Class<T>): T {
        request.addHeader("Authorization", auth())
        request.addHeader("Connection", "close")
        HttpClientBuilder.create().build().use { client ->
            client.execute(request).use {
                if (it.statusLine.statusCode != successCode) {
                    throw Exception("HTTP error: ${it.statusLine}")
                }
                return parseResponse(it, type)
            }
        }
    }

    private fun <T> parseResponse(response: HttpResponse, type: Class<T>): T {
        val output = ByteArrayOutputStream()
        response.entity.writeTo(output)
        return GSON.fromJson(String(output.toByteArray()), type)
    }
}
