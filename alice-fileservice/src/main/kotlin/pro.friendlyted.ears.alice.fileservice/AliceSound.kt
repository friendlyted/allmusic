package pro.friendlyted.ears.alice.fileservice

import java.util.*

data class AliceSound (
    val id: String,
    val skillId: String,
    val size: Int?,
    val originalName: String,
    val createdAt: Date,
    val isProcessed: Boolean,
    val error: String?
)