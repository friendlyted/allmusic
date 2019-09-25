package pro.friendlyted.ears.music.service

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import java.io.StringReader
import java.util.*
import kotlin.collections.HashMap

data class EntityModel(
        val required: List<String>?,
        val equals: List<String>?,
        val near: List<String>?,
        val nearIgnoreCase: List<String>?
)

class Entity(val name: String, val eqFunc: (String) -> Boolean) {

    companion object {
        private val GSON = Gson()
        private val emType = object : TypeToken<HashMap<String, EntityModel>>() {}.type

        private fun loadFromFile(filename: String): List<Entity> {
            val models: Map<String, EntityModel> = GSON.fromJson(InputStreamReader(javaClass.classLoader.getResourceAsStream(filename),"UTF8"), emType)
            return models.map { (k, v) -> createEntity(k, v) }
        }

        private fun createEntity(name: String, model: EntityModel): Entity {
            return Entity(name) { command ->
                if(model.required != null){
                    if(model.required.any { !command.contains(Regex(it)) }) {
                        return@Entity false
                    }
                }
                if (model.equals != null) {
                     model.equals.forEach {
                        if (it == command) {
                            return@Entity true
                        }
                    }
                }
                if (model.near != null) {
                    if (command.nearToAny(*model.near.toTypedArray())) {
                        return@Entity true
                    }
                }
                if (model.nearIgnoreCase != null) {
                    model.nearIgnoreCase.forEach {
                        val cleanCommand = command.replace(Regex("\\s"), "")
                        if (it.nearTo(cleanCommand)) {
                            return@Entity true
                        }
                    }
                }

                return@Entity false
            }
        }

        val entities = mapOf(
                "global" to loadFromFile("global.json"),
                "mode" to loadFromFile("mode.json"),
                "quest" to loadFromFile("quest.json"),
                "interval" to loadFromFile("interval.json"),
                "triad" to loadFromFile("triad.json"),
                "chord7" to loadFromFile("chord7.json")
        )
    }
}

