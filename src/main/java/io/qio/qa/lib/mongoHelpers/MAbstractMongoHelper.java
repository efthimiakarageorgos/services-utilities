/**
 * © Qio Technologies Ltd. 2016. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF QIO TECHNOLOGIES LTD.
 */
package io.qio.qa.lib.mongoHelpers;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.qio.qa.lib.common.BaseHelper;
import org.apache.log4j.Logger;
import org.bson.Document;

import java.io.IOException;

//import com.mongodb.util.JSON;

public class MAbstractMongoHelper
{
    final static Logger logger = Logger.getRootLogger();

    public static void addObjectToMongoDbCollection(Object requestObject, String URI, String mongoDb, String collectionName) {
        try {
            MongoClient mongoClient = new MongoClient(new MongoClientURI(URI));
            MongoDatabase database = mongoClient.getDatabase(mongoDb);

            //DBObject bson = ( DBObject ) JSON.parse(BaseHelper.toJSONString(requestObject) );
            //logger.info(bson.toString());
            //Document doc = Document.parse(bson.toString());

            Document doc = Document.parse(BaseHelper.toJSONString(requestObject));

            MongoCollection collection = database.getCollection(collectionName);

            collection.insertOne(doc);
            mongoClient.close();
        } catch (RuntimeException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void addDocumentToMongoDbCollection(Document doc, String URI, String mongoDb, String collectionName) {
        //example of how to populate a document
        Document person = new Document("date", "02-JUN-2018")
                .append("time", "11:02")
                .append("vesselId", "5820870be4b082f136653eeee")
                .append("voyageNumberOnDate", 6)
                .append("journeyOrigin", "Soton")
                .append("journeyDestination", "Cowes")
                .append("speed", 11.4)
                .append("speedUOM", "knots")
                .append("heading", 131.0)
                .append("headingUOM", "degrees")
                .append("powerFwd", 2904)
                .append("powerAft", 3123)
                .append("powerTotal", 6027)
                .append("powerUOM", "kW")
                .append("fuelConsumptionFwd", 4.3)
                .append("fuelConsumptionAft", 4.8)
                .append("fuelConsumptionTotal", 9.1)
                .append("fuelConsumptionUOM", "l/m")
                .append("voithFwdAA", -94.0)
                .append("voithFwdPS", -98.8)
                .append("forthAftAA", -5.4)
                .append("forthAftPS", 2.1)
                .append("voithforthUOM", "%")
                .append("windDirection", 98)
                .append("windDirectionUOM", "degrees")
                .append("windSpeed", 1)
                .append("windSpeedUOM", "knots")
                .append("latitude", 37.4224764)
                .append("longitude", -122.0842499);

        logger.info(person.toJson().toString());

        try {
            MongoClient mongoClient = new MongoClient(new MongoClientURI(URI));
            MongoDatabase database = mongoClient.getDatabase(mongoDb);

            MongoCollection collection = database.getCollection(collectionName);
            collection.insertOne(doc);
            mongoClient.close();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
