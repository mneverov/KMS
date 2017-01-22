package service

import org.springframework.stereotype.Service
import persistence.KmsStorage

/**
 * @author Maxim Neverov
 */
@Service
class KmsServiceImpl(private val storage: KmsStorage) : KmsService {

    override fun get(id: String)
            = storage.get(id) ?: "Document with id=[$id] is not found"

    override fun insert(doc: String)
            = storage.insert(doc)

    override fun delete(id: String)
            = storage.delete(id)

    override fun update(id: String, doc: String)
            = storage.update(id, doc)

}

