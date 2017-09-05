package com.anythingintellect.mydeliveries.util;

import com.anythingintellect.mydeliveries.model.Delivery;
import com.anythingintellect.mydeliveries.model.Location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

public class MockData {
    private static List<Delivery> deliveries;

    public static void init() {
        deliveries = new ArrayList<>();
        try {
            JSONArray array = new JSONArray("[{\"description\":\"Deliver documents to Tracy\",\"imageUrl\":\"http://placekitten.com.s3.amazonaws.com/homepage-samples/200/287.jpg\",\"location\":{\"lat\":1.3520156,\"lng\":103.9638572,\"address\":\"Changi\"}},{\"description\":\"Deliver parcel to Vea\",\"imageUrl\":\"http://placekitten.com.s3.amazonaws.com/homepage-samples/200/286.jpg\",\"location\":{\"lat\":1.301805,\"lng\":103.8356084,\"address\":\"Orchard Road\"}},{\"description\":\"Deliver documents to Kris\",\"imageUrl\":\"http://placekitten.com.s3.amazonaws.com/homepage-samples/200/287.jpg\",\"location\":{\"lat\":1.3008849,\"lng\":103.8568099,\"address\":\"Haji Lane\"}},{\"description\":\"Deliver parcel to Mike\",\"imageUrl\":\"http://placekitten.com.s3.amazonaws.com/homepage-samples/200/286.jpg\",\"location\":{\"lat\":1.3297389,\"lng\":103.8345336,\"address\":\"Massive Infinity\"}},{\"description\":\"Deliver documents to Shao Yu\",\"imageUrl\":\"http://placekitten.com.s3.amazonaws.com/homepage-samples/200/287.jpg\",\"location\":{\"lat\":1.3129298,\"lng\":103.794746,\"address\":\"National University of Singapore\"}},{\"description\":\"Deliver parcel to Charmaine\",\"imageUrl\":\"http://placekitten.com.s3.amazonaws.com/homepage-samples/200/286.jpg\",\"location\":{\"lat\":1.3129298,\"lng\":103.7947463,\"address\":\"National Library\"}},{\"description\":\"Deliver documents to Dawn\",\"imageUrl\":\"http://placekitten.com.s3.amazonaws.com/homepage-samples/200/287.jpg\",\"location\":{\"lat\":1.2878605,\"lng\":103.819822,\"address\":\"Vivo City\"}},{\"description\":\"Deliver parcel to Rosa\",\"imageUrl\":\"http://placekitten.com.s3.amazonaws.com/homepage-samples/200/286.jpg\",\"location\":{\"lat\":1.2878605,\"lng\":103.819822,\"address\":\"Universal Studios\"}},{\"description\":\"Deliver documents to Arul\",\"imageUrl\":\"http://placekitten.com.s3.amazonaws.com/homepage-samples/200/287.jpg\",\"location\":{\"lat\":1.2888949,\"lng\":103.8505391,\"address\":\"Marina Bay Sands\"}},{\"description\":\"Deliver parcel to Kaarthick\",\"imageUrl\":\"http://placekitten.com.s3.amazonaws.com/homepage-samples/200/286.jpg\",\"location\":{\"lat\":1.3784608,\"lng\":103.8400916,\"address\":\"Yio Chu Kang Stadium\"}},{\"description\":\"Deliver documents to Jianli\",\"imageUrl\":\"http://placekitten.com.s3.amazonaws.com/homepage-samples/200/287.jpg\",\"location\":{\"lat\":1.3327289,\"lng\":103.7378718,\"address\":\"JCube\"}},{\"description\":\"Deliver parcel to Sindhu\",\"imageUrl\":\"http://placekitten.com.s3.amazonaws.com/homepage-samples/200/286.jpg\",\"location\":{\"lat\":1.289317,\"lng\":103.8598782,\"address\":\"Marina Barrage\"}},{\"description\":\"Deliver documents to Cheryl\",\"imageUrl\":\"http://placekitten.com.s3.amazonaws.com/homepage-samples/200/287.jpg\",\"location\":{\"lat\":1.3509268,\"lng\":103.9406276,\"address\":\"Tampines Mall\"}},{\"description\":\"Deliver parcel to Isaac\",\"imageUrl\":\"http://placekitten.com.s3.amazonaws.com/homepage-samples/200/286.jpg\",\"location\":{\"lat\":1.3721996,\"lng\":103.7835665,\"address\":\"Singapore Zoo\"}},{\"description\":\"Deliver documents to Jan\",\"imageUrl\":\"http://placekitten.com.s3.amazonaws.com/homepage-samples/200/287.jpg\",\"location\":{\"lat\":1.3744452,\"lng\":103.8415102,\"address\":\"Nanyang Polytechnic\"}},{\"description\":\"Deliver parcel to Thomas\",\"imageUrl\":\"http://placekitten.com.s3.amazonaws.com/homepage-samples/200/286.jpg\",\"location\":{\"lat\":1.3578271,\"lng\":103.8282834,\"address\":\"AMK Hub\"}},{\"description\":\"Deliver documents to Naylin\",\"imageUrl\":\"http://placekitten.com.s3.amazonaws.com/homepage-samples/200/287.jpg\",\"location\":{\"lat\":1.3385881,\"lng\":103.8357309,\"address\":\"HDB Hub\"}},{\"description\":\"Deliver parcel to Willie\",\"imageUrl\":\"http://placekitten.com.s3.amazonaws.com/homepage-samples/200/286.jpg\",\"location\":{\"lat\":1.2940475,\"lng\":103.8269024,\"address\":\"Suntec City\"}},{\"description\":\"Deliver documents to Vincent\",\"imageUrl\":\"http://placekitten.com.s3.amazonaws.com/homepage-samples/200/287.jpg\",\"location\":{\"lat\":1.3027957,\"lng\":103.868996,\"address\":\"National Stadium\"}},{\"description\":\"Deliver parcel to Wei Ling\",\"imageUrl\":\"http://placekitten.com.s3.amazonaws.com/homepage-samples/200/286.jpg\",\"location\":{\"lat\":1.3060122,\"lng\":103.9161238,\"address\":\"East Coast Food Village\"}}]");
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.optJSONObject(i);
                Delivery delivery = new Delivery();
                delivery.setId(i+1);
                delivery.setImageUrl(object.optString("imageUrl"));
                JSONObject locationObj = object.optJSONObject("location");
                Location location = new Location();
                location.setAddress(locationObj.optString("address"));
                location.setLat(locationObj.optDouble("lat"));
                location.setLng(locationObj.optDouble("lng"));
                delivery.setLocation(location);
                delivery.setDescription(object.optString("description"));
                deliveries.add(delivery);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static List<Delivery> getNDeliveries(int count) {
        return deliveries.subList(0, Math.min(count, deliveries.size() - 1));
    }
}
