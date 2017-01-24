package kms.persistence

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters.eq
import org.bson.Document
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

/**
 * @author Max Neverov
 */
@Repository
class MongoStorageImpl
@Autowired constructor(@Value("\${data.mongodb.uri}") val uri: String,
                       @Value("\${data.mongodb.db_name}") val dbName: String,
                       @Value("\${data.mongodb.collection_name}") val collectionName: String) : KmsStorage {

    private val client: MongoClient = MongoClient(MongoClientURI(uri))
    private val db: MongoDatabase = client.getDatabase(dbName)
    private val docs: MongoCollection<Document> = db.getCollection(collectionName)

    override fun get(id: String): String? {
        return docs.find(eq("_id", id))?.first()?.toJson()
    }

    override fun insert(doc: String) {
        docs.insertOne(Document.parse(doc))
    }

    override fun delete(id: String): Long {
        return db.getCollection(collectionName).deleteOne(eq("_id", id)).deletedCount
    }

    override fun update(id: String, doc: String) {
        db.getCollection(collectionName).updateOne(eq("_id", id), Document("\$set", Document.parse(doc)))
    }
}
