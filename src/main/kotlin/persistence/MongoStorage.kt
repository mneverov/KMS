package persistence

/**
 * @author Max Neverov
 * @since
 */
interface MongoStorage {

    fun get(id: String): String?
    fun insert(doc: String)
    fun delete(id: String): Long
    fun update(id: String, doc: String)
}