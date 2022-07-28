package org.hyrical.kitpvp.mongo

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import com.mongodb.client.MongoClients
import io.github.nosequel.data.connection.mongo.MongoConnectionPool

class MongoURIConnection : MongoConnectionPool() {
    override var databaseName: String? = null

    var mongoURI: String? = null

    override fun getConnectionPool(): MongoClient {
        return mongoURI?.let { MongoClientURI(it) }?.let { MongoClient(it) }!!
    }
}