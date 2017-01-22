package service

/**
 * @author Maxim Neverov
 */
interface KmsService {
    fun get(id: String): String
    fun insert(doc: String)
    fun delete(id: String): Long
    fun update(id: String, doc: String)
}